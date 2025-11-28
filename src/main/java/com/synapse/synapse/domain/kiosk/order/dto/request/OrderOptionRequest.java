package com.synapse.synapse.domain.kiosk.order.dto.request;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사용자 옵션 선택 요청")
public record OrderOptionRequest(

	@Schema(description = "사용자 디바이스 UUID", example = "abc-123-uuid")
	String uuid,

	@Schema(description = "옵션 카테고리 이름", example = "")
	Long optionId,

	@Schema(description = "옵션 가격", example = "2000")
	BigDecimal price

) {
}

/**
 * option_category_name 받고
 * option_category_name 으로 category_id 조회하고
 * 그 category_id에 해당하는 데이터 중에 요청값? 이랑 맞으면 option으로 두고
 * option 계산해서 -> 총합 계산하고 -> 주문 완료 처리
 */
