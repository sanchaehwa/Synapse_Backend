package com.synapse.synapse.domain.kiosk.menu.dto.response;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "전체 메뉴 조회 데이터")
public record LoadKioskMenu(

	@Schema(description = "메뉴 이름", example = "치즈등갈비")
	String MenuName,

	@Schema(description = "메뉴 사진", example = "https://my-kiosk-bucket.s3.ap-northeast-2.amazonaws.com/menu/cheese-ribs.jpg")
	String imgUrl,

	@Schema(description = "메뉴 카테고리", example = "고기류")
	String categoryName,

	@Schema(description = "메뉴 가격", example = "20000")
	BigDecimal price

) {
}
