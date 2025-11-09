package com.synapse.synapse.domain.admin.kiosk_management.menu.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.synapse.synapse.domain.admin.Admin;
import com.synapse.synapse.domain.admin.kiosk_management.option.entity.OptionCategory;
import com.synapse.synapse.global.domain.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "kiosk_menu")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class KioskMenu extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//adminId (해당 상품(메뉴)를 등록한 관리자 번호)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "admin_id", nullable = false)
	private Admin admin;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@Column(name = "menu_name", nullable = false, length = 50)
	private String menuName;

	@Column(nullable = false)
	private BigDecimal price;

	@Column(nullable = false, length = 1000)
	private String description;

	@Column(nullable = false, length = 200)
	private String imageUrl;

	@Builder.Default
	@Column(nullable = false)
	private boolean isAvailable = true; //메뉴 판매 가능 여부

	@Builder.Default
	@Column(nullable = false)
	private boolean isDeleted = false; //soft delete

	@OneToMany(mappedBy = "kioskMenu", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private List<OptionCategory> optionCategories = new ArrayList<>();

	//qrCodeUrl

	//AI 추천 프롬프트
	@Column(length = 1000)
	private String aiPromptForRecommendation;
}
