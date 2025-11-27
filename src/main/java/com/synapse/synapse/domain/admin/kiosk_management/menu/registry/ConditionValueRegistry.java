package com.synapse.synapse.domain.admin.kiosk_management.menu.registry;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.synapse.synapse.domain.admin.kiosk_management.menu.model.FlavorType;
import com.synapse.synapse.domain.admin.kiosk_management.menu.model.MoodType;
import com.synapse.synapse.domain.admin.kiosk_management.menu.model.TemperatureType;

public class ConditionValueRegistry {

	private static final Map<String, Set<String>> values = new HashMap<>();

	static {
		values.put("MOOD", new HashSet<>());
		for (MoodType m : MoodType.values())
			values.get("MOOD").add(m.getDisplayName());
		values.put("FLAVOR", new HashSet<>());
		for (FlavorType f : FlavorType.values())
			values.get("FLAVOR").add(f.getDisplayName());
		values.put("TEMPERATURE", new HashSet<>());
		for (TemperatureType t : TemperatureType.values())
			values.get("TEMPERATURE").add(t.getDisplayName());
	}

	public static void addValue(String condition, String value) {
		values.computeIfAbsent(condition, k -> new HashSet<>()).add(value);
	}

	public static Set<String> getValues(String condition) {
		return Collections.unmodifiableSet(values.getOrDefault(condition, Collections.emptySet()));
	}
}
