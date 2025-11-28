package com.synapse.synapse.domain.qrcode.service;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.synapse.synapse.domain.admin.kiosk_management.menu.repository.KioskMenuCategoryRepository;
import com.synapse.synapse.domain.admin.kiosk_management.menu.repository.KioskMenuOptionRepository;
import com.synapse.synapse.domain.admin.kiosk_management.menu.repository.KioskMenuRepository;
import com.synapse.synapse.domain.admin.qrcode_management.repository.QrcodeRepository;
import com.synapse.synapse.domain.admin.signup.repository.AdminRepository;
import com.synapse.synapse.domain.kiosk.menu.dto.response.DetailMenuResponse;
import com.synapse.synapse.domain.kiosk.menu.dto.response.KioskMenuResponse;
import com.synapse.synapse.domain.kiosk.menu.dto.response.LoadKioskMenu;
import com.synapse.synapse.domain.kiosk.menu.dto.response.LoadKioskMenuCategory;
import com.synapse.synapse.domain.kiosk.menu.dto.response.OptionMenuDto;
import com.synapse.synapse.domain.kiosk.order.entity.OrderItem;
import com.synapse.synapse.domain.kiosk.order.repository.OrderItemRepository;
import com.synapse.synapse.domain.kiosk.order.service.OrderMenuService;
import com.synapse.synapse.domain.qrcode.dto.response.QrcodeResponse;
import com.synapse.synapse.global.exception.ErrorMessage;
import com.synapse.synapse.global.model.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class QrcodeService {

	private final QrcodeRepository qrcodeRepository;

	private final KioskMenuRepository kioskMenuRepository;
	private final KioskMenuCategoryRepository kioskMenuCategoryRepository;
	private final KioskMenuOptionRepository kioskMenuOptionRepository;
	private final AdminRepository adminRepository;
	private final OrderMenuService orderMenuService;
	private final OrderItemRepository orderItemRepository;

	@Transactional(readOnly = true)
	public KioskMenuResponse loadQrCodeMenus(String storeName, Long categoryId) {

		validateStoreAndCategory(storeName, categoryId);

		List<LoadKioskMenu> menus = kioskMenuRepository.findMenusByStoreName(storeName, categoryId)
			.stream()
			.map(menu -> new LoadKioskMenu(
				menu.getMenuName(),
				menu.getImageUrl(),
				menu.getCategory().getCategoryName(),
				menu.getPrice()
			))
			.toList();

		List<LoadKioskMenuCategory> categories = kioskMenuCategoryRepository.getAllCategoriesByStoreName(storeName)
			.stream()
			.map(cat -> new LoadKioskMenuCategory(
				cat.getId(),
				cat.getCategoryName()
			))
			.toList();

		return KioskMenuResponse.builder()
			.loadKioskMenus(menus)
			.loadKioskMenuCategories(categories)
			.build();
	}

	@Transactional(readOnly = true)
	public List<DetailMenuResponse> loadMenuOptions(Long menuId) {
		validateFindMenu(menuId);

		return kioskMenuOptionRepository.findAllByKioskMenuId(menuId)
			.stream()
			.map(oc -> DetailMenuResponse.builder()
				.id(oc.getId())
				.categoryName(oc.getName())
				.menuOptions(
					oc.getMenuOptions().stream()
						.map(oi -> new OptionMenuDto(
							oi.getOptionName(),
							oi.getPrice()
						))
						.collect(Collectors.toList())
				)
				.build()
			)
			.collect(Collectors.toList());
	}

	private void validateStoreAndCategory(String storeName, Long categoryId) {
		if (!adminRepository.existsByStoreName(storeName)) {
			throw new NotFoundException(ErrorMessage.NOT_FOUND_STORE);
		}

		if (!kioskMenuRepository.existsByCategoryId(categoryId)) {
			throw new NotFoundException(ErrorMessage.NOT_FOUND_CATEGORY);
		}
	}

	private void validateFindMenu(Long menuId) {
		if (!kioskMenuRepository.existsById(menuId)) {
			throw new NotFoundException(ErrorMessage.NOT_FOUND_MENU);
		}
	}
	/* ---------------- QR + TXT 생성 기능 ---------------- */

	public QrcodeResponse createTxtAndQRCode(String uuid) {

		// 1) 주문 조회
		List<OrderItem> orderItems = orderItemRepository.findByUser_DeviceUUIDAndOrderIsNull(uuid);
		if (orderItems.isEmpty()) {
			throw new NotFoundException(ErrorMessage.NOT_FOUND_ORDER);
		}

		try {
			// 2) 텍스트 파일 생성
			String txtPath = createTxtFile(uuid, orderItems);

			// 3) QR 생성 (파일 경로를 QR로)
			String qrBase64 = createQRCodeBase64(txtPath);

			return QrcodeResponse.builder()
				.uuid(uuid)
				.txtFilePath(txtPath)
				.qrCodeBase64(qrBase64)
				.build();

		} catch (Exception e) {
			throw new RuntimeException("QR 생성 실패: " + e.getMessage());
		}
	}

	/* ---------------- TXT 파일 생성 ---------------- */

	private String createTxtFile(String uuid, List<OrderItem> items) throws IOException {

		String fileName = "order_" + uuid + ".txt";
		Path filePath = Path.of("orders", fileName);

		// 디렉토리 없으면 생성
		Files.createDirectories(filePath.getParent());

		try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
			writer.write("=== 주문 내역 ===\n");
			writer.write("UUID: " + uuid + "\n\n");

			for (OrderItem item : items) {
				writer.write("메뉴: " + item.getKioskMenu().getMenuName() + "\n");
				writer.write("수량: " + item.getQuantity() + "\n");
				writer.write("가격: " + item.getTotalPrice() + "원\n");

				if (!item.getOrderItemOptions().isEmpty()) {
					writer.write("옵션:\n");
					item.getOrderItemOptions().forEach(opt -> {
						try {
							writer.write("  - " + opt.getMenuOption().getOptionName()
								+ " (" + opt.getMenuOption().getPrice() + "원)\n");
						} catch (IOException e) {
						}
					});
				}
				writer.write("\n");
			}
		}

		return filePath.toAbsolutePath().toString();
	}

	/* ---------------- QR Base64 생성 ---------------- */

	private String createQRCodeBase64(String content) throws Exception {

		int size = 300;

		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, size, size);

		BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(qrImage, "png", out);

		return Base64.getEncoder().encodeToString(out.toByteArray());
	}

}
