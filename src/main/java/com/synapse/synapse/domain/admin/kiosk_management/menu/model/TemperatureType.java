package com.synapse.synapse.domain.admin.kiosk_management.menu.model;

import lombok.Getter;

@Getter
public enum TemperatureType {
	HOT("뜨거움"),
	WARM("따뜻함"),
	COLD("차가움"),
	RICH("진함"),
	SMOOTH("부드러움");

	private final String displayName;

	TemperatureType(String displayName) {
		this.displayName = displayName;
	}

	public static TemperatureType fromDisplayName(String displayName) {
		for (TemperatureType t : values()) {
			if (t.displayName.equals(displayName))
				return t;
		}
		return null; //사용자 정의값
	}

}
