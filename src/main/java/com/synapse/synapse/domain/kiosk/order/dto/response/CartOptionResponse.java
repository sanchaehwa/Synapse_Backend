package com.synapse.synapse.domain.kiosk.order.dto.response;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

public record CartOptionResponse(
	@Schema(name = "옵션아이디", example = "1")
	Long optionId,
	@Schema(name = "옵션 이름", example = "샷추가")
	String optionName,
	@Schema(name = "옵션 가격", example = "500")
	BigDecimal price
) {
}
