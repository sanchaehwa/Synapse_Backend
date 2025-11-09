package com.synapse.synapse.domain.kiosk.menu.dto.response;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class KioskMenuResponse {
	@Builder.Default
	private List<LoadKioskMenuCategory> loadKioskMenuCategories = List.of();

	@Builder.Default
	private List<LoadKioskMenu> loadKioskMenus = List.of();
}
