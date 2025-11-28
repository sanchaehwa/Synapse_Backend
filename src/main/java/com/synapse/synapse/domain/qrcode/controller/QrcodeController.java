package com.synapse.synapse.domain.qrcode.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synapse.synapse.domain.kiosk.menu.dto.response.DetailMenuResponse;
import com.synapse.synapse.domain.kiosk.menu.dto.response.KioskMenuResponse;
import com.synapse.synapse.domain.kiosk.order.dto.request.OrderMenuRequest;
import com.synapse.synapse.domain.kiosk.order.dto.request.OrderOptionRequest;
import com.synapse.synapse.domain.kiosk.order.dto.response.CartResponse;
import com.synapse.synapse.domain.kiosk.order.service.OrderMenuService;
import com.synapse.synapse.domain.qrcode.dto.response.QrcodeResponse;
import com.synapse.synapse.domain.qrcode.service.QrcodeService;
import com.synapse.synapse.global.api.ApiTemplate;
import com.synapse.synapse.global.exception.SuccessMessage;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/qrcode")
public class QrcodeController {

	private final QrcodeService qrcodeService;
	private final OrderMenuService orderMenuService;

	//메뉴 조회
	@GetMapping("/menus")
	public ApiTemplate<KioskMenuResponse> getAllQrcodeMenus(
		@RequestParam String storeName,
		@RequestParam Long categoryId
	) {
		KioskMenuResponse getMenu = qrcodeService.loadQrCodeMenus(storeName, categoryId);
		return ApiTemplate.ok(SuccessMessage.WK_DATA_RETRIEVED, getMenu);
	}

	//옵션 조회
	@GetMapping("/options")
	public ApiTemplate<List<DetailMenuResponse>> getOptions(
		@RequestParam Long menuId
	) {
		List<DetailMenuResponse> getOption = qrcodeService.loadMenuOptions(menuId);
		return ApiTemplate.ok(SuccessMessage.WK_DATA_RETRIEVED, getOption);
	}

	//메뉴 선택
	@PostMapping("/order/menu")
	public ApiTemplate<?> choseOrderMenu(
		@RequestParam Long storeId,
		@Valid @RequestBody OrderMenuRequest orderMenuRequest
	) {
		orderMenuService.choseMenu(orderMenuRequest, storeId);
		return ApiTemplate.ok(SuccessMessage.WK_RESOURCE_CREATED);
	}

	//옵션 선택
	@PostMapping("/order/option")
	public ApiTemplate<?> choseOrderOption(
		@RequestParam Long categoryId,
		@RequestParam String menuName,
		@Valid @RequestBody OrderOptionRequest orderOptionRequest
	) {
		orderMenuService.choseOption(orderOptionRequest, categoryId, menuName);
		return ApiTemplate.ok(SuccessMessage.WK_RESOURCE_CREATED);
	}

	//장바구니 조회
	@GetMapping("/cart")
	public ApiTemplate<CartResponse> getCart(@RequestParam String uuid) {
		CartResponse cartResponse = orderMenuService.getCart(uuid);
		return ApiTemplate.ok(SuccessMessage.WK_DATA_RETRIEVED, cartResponse);
	}

	//장바구니 삭제
	@DeleteMapping("/cart")
	public ApiTemplate<?> deleteCart(@RequestParam String uuid,
		@RequestParam Long orderItemId) {
		orderMenuService.removeMenuItem(orderItemId, uuid);
		return ApiTemplate.ok(SuccessMessage.WK_RESOURCE_DELETED);
	}

	//qrcode 생성
	@GetMapping("/{uuid}")
	public ResponseEntity<QrcodeResponse> createOrderQr(@PathVariable String uuid) {
		QrcodeResponse response = qrcodeService.createTxtAndQRCode(uuid);
		return ResponseEntity.ok(response);
	}

}
