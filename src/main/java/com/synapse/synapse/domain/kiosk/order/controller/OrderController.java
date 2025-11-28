package com.synapse.synapse.domain.kiosk.order.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synapse.synapse.domain.kiosk.order.dto.request.OrderMenuRequest;
import com.synapse.synapse.domain.kiosk.order.service.OrderMenuService;
import com.synapse.synapse.global.api.ApiTemplate;
import com.synapse.synapse.global.exception.SuccessMessage;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/kiosk/order")
@RequiredArgsConstructor
public class OrderController {

	private final OrderMenuService orderMenuService;

	//메뉴 선택
	@PostMapping("/menu/select")
	public ApiTemplate<?> choseMenu(
		@RequestParam Long storeId,
		@Valid @RequestBody OrderMenuRequest orderMenuRequest
	) {
		orderMenuService.choseMenu(orderMenuRequest, storeId);
		return ApiTemplate.ok(SuccessMessage.MENU_CHOSE_SUCCESS);
	}
	
}
