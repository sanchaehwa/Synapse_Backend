package com.synapse.synapse.domain.kiosk.order.dto.response;

import java.util.List;

public record CartResponse(
	String uuid,
	List<CartItemResponse> items
) {
}
