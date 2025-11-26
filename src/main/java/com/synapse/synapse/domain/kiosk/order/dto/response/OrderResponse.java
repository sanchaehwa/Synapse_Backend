package com.synapse.synapse.domain.kiosk.order.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사용자 주문 내역")
public record OrderResponse(

	@Schema(description = "주문 메뉴 명", example = "샌드위치")
	String menuName,
	@Schema(description = "주문 수량", example = "2")
	Integer quantity
) {
}
