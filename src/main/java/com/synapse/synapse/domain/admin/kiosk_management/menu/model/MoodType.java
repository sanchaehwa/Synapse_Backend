package com.synapse.synapse.domain.admin.kiosk_management.menu.model;

import lombok.Getter;

@Getter
public enum MoodType {
	QUICK_MEAL("간편"),
	SNACK("간식"),
	CELEBRATION("기념일/축하"),
	FAMILY_MEAL("가족과 함께"),
	LATE_NIGHT("야식"),
	PICNIC("야외/피크닉");

	private final String displayName;

	MoodType(String displayName) {
		this.displayName = displayName;
	}

	public static MoodType fromDisplayName(String displayName) {
		for (MoodType m : values()) {
			if (m.displayName.equals(displayName))
				return m;
		}
		return null;
	}

}
