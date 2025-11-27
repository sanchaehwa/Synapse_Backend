package com.synapse.synapse.domain.kiosk.menu.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "전체 메뉴 카테고리 조회 데이터")
public record LoadKioskMenuCategory(

	@Schema(description = "카테고리 번호", example = "1")
	Long id,

	@Schema(description = "카테고리 이름", example = "고기류")
	String CategoryName
) {
}
