package com.synapse.synapse.domain.qrcode.dto.response;

import com.synapse.synapse.domain.admin.kiosk_management.menu.model.PlatformType;
import com.synapse.synapse.domain.admin.kiosk_management.option.entity.OptionCategory;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class OptionCategoryResponse {

	private Long categoryId;
	private String categoryName;
	private PlatformType platformType;

	public static OptionCategoryResponse fromEntity(OptionCategory optionCategory) {
		return OptionCategoryResponse.builder()
			.categoryId(optionCategory.getId())
			.categoryName(optionCategory.getName())
			.platformType(optionCategory.getPlatformType())
			.build();
	}

}
