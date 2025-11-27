package com.synapse.synapse.domain.admin.qrcode_management.entity;

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

@Getter
@Entity
@Builder
@Table(name = "qrcode_menu")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class QrcodeMenu extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//adminId (해당 상품(메뉴)를 등록한 관리자 번호)
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "admin_id", nullable = false)
	private Admin admin;

	@Column(name = "menu_name", nullable = false, length = 50)
	private String menuName;

	@Column(name = "menu_description", nullable = false, length = 500)
	private String menuDescription;

	@Column(name = "img_url", nullable = false)
	private String imgUrl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "qr_menu_category_id", nullable = false)
	private QrcodeMenuCategory qrMenuCategory;

	@Column(name = "menu_price", nullable = false)
	private BigDecimal menuPrice;

	@OneToMany(mappedBy = "qrcodeMenu", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private List<OptionCategory> optionCategories = new ArrayList<>();

}
