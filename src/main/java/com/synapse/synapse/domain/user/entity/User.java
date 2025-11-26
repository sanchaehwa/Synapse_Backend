package com.synapse.synapse.domain.user.entity;

import java.util.ArrayList;
import java.util.List;

import com.synapse.synapse.domain.kiosk.order.entity.Order;
import com.synapse.synapse.domain.kiosk.order.entity.OrderItem;
import com.synapse.synapse.domain.user.model.SortUser;
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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserType userType = UserType.USER;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SortUser sortUser;

	//QR 생성 App 설치시 자동 생성되는 UUID
	@Column(unique = true)
	private String deviceUUID;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> orderItems = new ArrayList<>();

	//복원용 - 전화번호 인증 선택사항
	@Column(unique = true)
	private String phoneNumber;

	@Builder.Default
	@Column
	private boolean isPhoneVerified = false;

	@Builder.Default
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Order> orders = new ArrayList<>();

	@Builder
	public User(String deviceUUID, String phoneNumber, boolean isPhoneVerified, List<Order> orders) {
		this.userType = UserType.USER;
		this.sortUser = SortUser.QUERCODE_USER;
		this.deviceUUID = deviceUUID;
		this.phoneNumber = phoneNumber;
		this.isPhoneVerified = isPhoneVerified;
		this.orders = orders != null ? orders : new ArrayList<>(); //주문 내역이 비어 있는 경우 빈 리스트 반환

	}

	//주문 update
	public void updateOrders(List<Order> orders) {
		this.orders = orders != null ? orders : new ArrayList<>();
	}

	//QrUser 생성
	public static User createQrUser(String deviceUUID, String phoneNumber, boolean isPhoneVerified,
		List<Order> orders) {
		User user = new User(deviceUUID, phoneNumber, isPhoneVerified, new ArrayList<>());
		user.userType = UserType.USER;
		user.sortUser = SortUser.QUERCODE_USER;
		return user;
	}

	// Normal User 생성
	public static User createNormalUser(String deviceUUID, List<Order> orders) {
		User user = new User(deviceUUID, null, false, new ArrayList<>());
		user.userType = UserType.USER;
		user.sortUser = SortUser.NORMAL_USER;
		return user;
	}
}


