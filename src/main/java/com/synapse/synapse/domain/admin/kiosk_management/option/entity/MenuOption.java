package com.synapse.synapse.domain.admin.kiosk_management.option.entity;

import com.synapse.synapse.global.domain.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "option_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OptionItem extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // optionItemId

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "option_category_id", nullable = false)
	private OptionCategory optionCategory;

	@Column(nullable = false, length = 50)
	private String optionItemName;

	@Column(nullable = false)
	private Integer price;
}
