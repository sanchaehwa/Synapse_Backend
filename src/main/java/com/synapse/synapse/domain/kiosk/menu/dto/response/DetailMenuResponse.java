package com.synapse.synapse.domain.kiosk.menu.dto.response;

import java.util.ArrayList;
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
public class DetailMenuResponse {

	private Long id;
	private String categoryName;

	@Builder.Default
	private List<OptionMenuDto> menuOptions = new ArrayList<>();
}
