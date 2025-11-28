package com.synapse.synapse.domain.qrcode.dto.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.synapse.synapse.domain.admin.qrcode_management.entity.QrcodeMenu;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class FindAllQrcodeMenu {

	private Long menuId;
	private String menuName;
	private BigDecimal menuPrice;
	private String menuDescription;
	private String imgUrl;
	private List<OptionCategoryResponse> optionCategories;

	public static FindAllQrcodeMenu fromEntity(QrcodeMenu qrcodeMenu) {
		return FindAllQrcodeMenu.builder()
			.menuId(qrcodeMenu.getId())
			.menuName(qrcodeMenu.getMenuName())
			.menuPrice(qrcodeMenu.getMenuPrice())
			.menuDescription(qrcodeMenu.getMenuDescription())
			.imgUrl(qrcodeMenu.getImgUrl())
			.optionCategories(
				qrcodeMenu.getOptionCategories().stream()
					.map(OptionCategoryResponse::fromEntity)
					.collect(Collectors.toList())
			)
			.build();
	}
}
