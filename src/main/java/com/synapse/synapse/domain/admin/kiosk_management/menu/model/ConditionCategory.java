package com.synapse.synapse.domain.admin.kiosk_management.menu.model;

import lombok.Getter;

@Getter
public enum ConditionCategory {
	MOOD("온도/느낌"),
	FAVOR("맛/풍미"),
	SITUATION("상황/목적");

	private final String displayName;

	ConditionCategory(String displayName) {
		this.displayName = displayName;
	}

	public static ConditionCategory fromDisplayName(String displayName) {
		for (ConditionCategory c : values()) {
			if (c.getDisplayName().equals(displayName))
				return c;
		}
		throw new IllegalStateException(": " + displayName); //NotFoundException 예외처리
	}
}
