package com.synapse.synapse.domain.kiosk.menu.dto.response;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "메뉴 옵션 조회 데이터")
public record OptionMenuDto(

	@Schema(description = "옵션 이름", example = "양상추")
	String optionName,

	@Schema(description = "옵션 가격", example = "500")
	BigDecimal price
) {
}

