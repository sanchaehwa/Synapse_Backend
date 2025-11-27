package com.synapse.synapse.domain.kiosk.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synapse.synapse.domain.admin.kiosk_management.menu.entity.KioskMenu;
import com.synapse.synapse.domain.admin.kiosk_management.menu.repository.KioskMenuRepository;
import com.synapse.synapse.domain.admin.kiosk_management.option.entity.MenuOption;
import com.synapse.synapse.domain.admin.kiosk_management.option.entity.OptionCategory;
import com.synapse.synapse.domain.kiosk.order.dto.request.OrderMenuRequest;
import com.synapse.synapse.domain.kiosk.order.dto.request.OrderOptionRequest;
import com.synapse.synapse.domain.kiosk.order.dto.response.CartItemResponse;
import com.synapse.synapse.domain.kiosk.order.dto.response.CartOptionResponse;
import com.synapse.synapse.domain.kiosk.order.dto.response.CartResponse;
import com.synapse.synapse.domain.kiosk.order.entity.OrderItem;
import com.synapse.synapse.domain.kiosk.order.entity.OrderItemOption;
import com.synapse.synapse.domain.kiosk.order.repository.MenuOptionRepository;
import com.synapse.synapse.domain.kiosk.order.repository.OptionCategoryRepository;
import com.synapse.synapse.domain.kiosk.order.repository.OrderItemOptionRepository;
import com.synapse.synapse.domain.kiosk.order.repository.OrderItemRepository;
import com.synapse.synapse.domain.kiosk.order.repository.OrderRepository;
import com.synapse.synapse.domain.user.entity.User;
import com.synapse.synapse.domain.user.repository.UserRepository;
import com.synapse.synapse.global.exception.ErrorMessage;
import com.synapse.synapse.global.model.BadRequestException;
import com.synapse.synapse.global.model.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderMenuService {

	private final OrderRepository orderRepository;
	private final KioskMenuRepository kioskMenuRepository;
	private final UserRepository userRepository;
	private final OrderItemRepository orderItemRepository;
	private final OptionCategoryRepository optionCategoryRepository;
	private final MenuOptionRepository menuOptionRepository;
	private final OrderItemOptionRepository orderItemOptionRepository;

	//메뉴 선택
	public void choseMenu(OrderMenuRequest orderMenuRequest, Long storeId) {

		//uuid 생성
		String deviceUUID = UUID.randomUUID().toString();
		User user = User.createNormalUser(deviceUUID, new ArrayList<>());
		userRepository.save(user);

		//해당 가게에 속해있는 메뉴인지 확인
		KioskMenu menu = kioskMenuRepository.findByStoreIdAndMenuName(storeId, orderMenuRequest.menuName())
			.orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_MENU));

		Integer inventory = menu.getInventory();
		Integer quantity = orderMenuRequest.quantity();

		//1차 검증
		if (inventory - quantity <= 0 && inventory <= 0 && !menu.isAvailable()) {
			throw new BadRequestException(ErrorMessage.QUANTITY_SHORTAGE);
		}

		menu.changeInventory(quantity);

		BigDecimal totalPrice = menu.getPrice().multiply(BigDecimal.valueOf(quantity));

		OrderItem orderItem = OrderItem.create(user, menu, quantity, totalPrice, new ArrayList<>());

		user.getOrderItems().add(orderItem);
		orderItemRepository.save(orderItem);

	}

	//옵션 선택
	public void choseOption(OrderOptionRequest orderOptionRequest, Long categoryId, String menuName) {

		OptionCategory category = optionCategoryRepository
			.findByIdAndKioskMenu_MenuName(categoryId, menuName)
			.orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_CATEGORY));

		MenuOption menuOption = menuOptionRepository.findById(orderOptionRequest.optionId())
			.orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_OPTION));

		if (!menuOption.getOptionCategory().getId().equals(categoryId)) {
			throw new BadRequestException(ErrorMessage.NOT_FOUND_OPTION);
		}

		OrderItem orderItem = orderItemRepository
			.findFirstByUser_DeviceUUIDOrderByIdDesc(orderOptionRequest.uuid())
			.orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_ORDER));

		OrderItemOption orderItemOption = OrderItemOption.create(orderItem, menuOption);
		orderItem.getOrderItemOptions().add(orderItemOption);
		orderItemOptionRepository.save(orderItemOption);

		BigDecimal totalPrice = orderItem.getTotalPrice().add(menuOption.getPrice());
		orderItem.updateTotalPrice(totalPrice);
		orderItemRepository.save(orderItem);
	}

	//옵션 삭제
	public void removeOption(String uuid, Long optionId) {

		OrderItem orderItem = orderItemRepository
			.findFirstByUser_DeviceUUIDOrderByIdDesc(uuid)
			.orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_ORDER));

		OrderItemOption target = orderItem.getOrderItemOptions().stream()
			.filter(opt -> opt.getMenuOption().getId().equals(optionId))
			.findFirst()
			.orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_OPTION));

		// 가격 차감
		BigDecimal newPrice = orderItem.getTotalPrice()
			.subtract(target.getMenuOption().getPrice());

		orderItem.updateTotalPrice(newPrice);

		orderItem.getOrderItemOptions().remove(target);
		orderItemOptionRepository.delete(target);
	}

	//장바구니 조회
	@Transactional(readOnly = true)
	public CartResponse getCart(String uuid) {

		List<OrderItem> cartItems = orderItemRepository.findByUser_DeviceUUIDAndOrderIsNull(uuid);

		List<CartItemResponse> responses = cartItems.stream()
			.map(item -> new CartItemResponse(
				item.getId(),
				item.getKioskMenu().getMenuName(),
				item.getQuantity(),
				item.getTotalPrice(),
				item.getOrderItemOptions().stream()
					.map(opt -> new CartOptionResponse(
						opt.getMenuOption().getId(),
						opt.getMenuOption().getOptionName(),
						opt.getMenuOption().getPrice()
					))
					.toList()
			))
			.toList();

		return new CartResponse(uuid, responses);
	}

	//장바구니 삭제
	public void removeMenuItem(Long orderItemId, String uuid) {

		OrderItem item = orderItemRepository
			.findById(orderItemId)
			.filter(oi -> oi.getUser().getDeviceUUID().equals(uuid) && oi.getOrder() == null)
			.orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_ORDER));

		orderItemRepository.delete(item);
	}

}
