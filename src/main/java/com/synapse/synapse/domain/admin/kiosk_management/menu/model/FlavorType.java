package com.synapse.synapse.domain.admin.kiosk_management.menu.model;

import lombok.Getter;

@Getter
public enum FlavorType {
	SWEET("달콤"),
	SALTY("짭짤"),
	SOUR("신맛"),
	BITTER("쓴맛"),
	UMAMI("감칠맛"),
	SPICY("매운맛"),
	RICH("진한맛"),
	LIGHT("담백한맛");

	private final String displayName;

	FlavorType(String displayName) {
		this.displayName = displayName;
	}

	public static FlavorType fromDisplayName(String displayName) {
		for (FlavorType t : values()) {
			if (t.displayName.equals(displayName))
				return t;
		}
		return null; // 사용자 정의값
	}
}
