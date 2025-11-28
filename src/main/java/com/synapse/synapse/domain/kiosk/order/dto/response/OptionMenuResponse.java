package com.synapse.synapse.domain.kiosk.order.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record OptionMenuResponse(
	@Schema(description = "메뉴 옵션 명", example = "얼음")
	String optionName
) {
}
