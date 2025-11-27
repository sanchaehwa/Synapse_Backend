package com.synapse.synapse.domain.admin;

import java.util.ArrayList;
import java.util.List;

import com.synapse.synapse.domain.admin.dashboard.entity.StoreInfo;
import com.synapse.synapse.domain.admin.kiosk_management.menu.entity.KioskMenu;
import com.synapse.synapse.domain.admin.qrcode_management.entity.QrcodeMenuCategory;
import com.synapse.synapse.domain.admin.signup.model.StoreType;
import com.synapse.synapse.domain.user.model.UserType;
import com.synapse.synapse.global.domain.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "admin")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Admin extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;  //storeId

	@Builder.Default
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 25)
	private UserType userType = UserType.ADMIN;

	@Column(nullable = false, length = 50)
	private String storeName;

	@Enumerated(EnumType.STRING)
	@Column
	private StoreType storeType;

	@Column(nullable = false, length = 100)
	@Email
	private String email;

	@Size(min = 8, max = 64, message = "비밀번호는 8자 이상 64자 이하이어야 합니다.")
	@Pattern(
		regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*()\\-_=+\\[\\]{};:'\",.<>/?]).+$",
		message = "비밀번호는 최소 1개의 숫자와 1개의 특수문자를 포함해야 합니다."
	)
	private String password;

	@Builder.Default
	@OneToMany(mappedBy = "admin", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<StoreInfo> stores = new ArrayList<>(); //매장 정보

	@Builder.Default
	@OneToMany(mappedBy = "admin", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<KioskMenu> kioskMenus = new ArrayList<>(); //메뉴

	@Builder.Default
	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<QrcodeMenuCategory> qrcodeCategories = new ArrayList<>();

}
