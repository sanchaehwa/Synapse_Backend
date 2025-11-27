package com.synapse.synapse.domain.kiosk.menu.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사용자의 메뉴 선택 요청 데이터")
public record ChoseMenuRequest(
	@Schema(description = "메뉴 이름", example = "치즈등갈비")
	String menuName

) {
}
