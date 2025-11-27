package com.synapse.synapse.domain.admin.signup.model;

import lombok.Getter;

@Getter
public enum StoreType {
	GENERAL_RESTAURANT("일반 음식점"),
	CAFE("카페"),
	FAST_FOOD("패스트푸드"),
	SNACK_BAR("분식점"),
	BAKERY_DESSERT("베이커리/디저트");

	private final String displayName;

	StoreType(String displayName) {
		this.displayName = displayName;
	}

	//프론트 DisplayName 으로 값을 전달하면 -> 백엔드 매핑
	public static StoreType fromDisplayName(String displayName) {
		for (StoreType type : values()) {
			if (type.displayName.equals(displayName))
				return type;
		}
		throw new IllegalStateException("Unknown store type: " + displayName); //NotFound 로 예외 처리
	}

}
