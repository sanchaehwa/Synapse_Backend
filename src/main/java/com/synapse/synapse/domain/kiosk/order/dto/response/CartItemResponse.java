package com.synapse.synapse.domain.kiosk.order.dto.response;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public record CartItemResponse(
	@Schema(name = "주문 상품 ID", example = "1")
	Long orderItemId,
	@Schema(name = "주문 상품 이름", example = "아메리카노")
	String menuName,
	@Schema(name = "주문 상품 수량", example = "3")
	Integer quantity,
	@Schema(name = "총액", example = "25000")
	BigDecimal totalPrice,
	@Schema(name = "옵션", example = "")
	List<CartOptionResponse> options
) {
}
