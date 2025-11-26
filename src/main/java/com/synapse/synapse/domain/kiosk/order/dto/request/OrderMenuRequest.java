package com.synapse.synapse.domain.kiosk.order.dto.request;

import com.synapse.synapse.domain.kiosk.order.model.OrderType;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사용자 메뉴 선택 요청")
public record OrderMenuRequest(
	@Schema(description = "주문 유형", example = "TAKE_OUT")
	OrderType orderType,
	@Schema(description = "메뉴 이름", example = "초밥")
	String menuName,
	@Schema(description = "수량", example = "3")
	Integer quantity
) {
}

