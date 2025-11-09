package com.synapse.synapse.domain.admin.kiosk_management.menu.entity;

import com.synapse.synapse.domain.admin.Admin;
import com.synapse.synapse.domain.admin.kiosk_management.menu.model.ConditionCategory;
import com.synapse.synapse.domain.admin.kiosk_management.menu.registry.ConditionValueRegistry;
import com.synapse.synapse.global.domain.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "recommend_condition")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RecommendCondition extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ConditionCategory category;

	@Column(nullable = false)
	private String conditionValue;

	@Builder.Default
	@Column(nullable = false)
	private boolean isCustom = false;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "admin_id", nullable = false)
	private Admin admin;

	//속성값 생성
	public static RecommendCondition of(ConditionCategory category, String conditionValue) {
		boolean custom = !ConditionValueRegistry.getValues(category.name()).contains(conditionValue);
		if (custom) {
			ConditionValueRegistry.addValue(category.name(), conditionValue);
		}
		return RecommendCondition.builder()
			.category(category)
			.conditionValue(conditionValue)
			.isCustom(custom).build();
	}

	//속성값 수정
	public void updateValue(String newValue) {
		boolean custom = !ConditionValueRegistry.getValues(category.name()).contains(newValue);
		if (custom) {
			ConditionValueRegistry.addValue(category.name(), newValue);
		}
		this.conditionValue = newValue;
		this.isCustom = custom;
	}

}
