package com.synapse.synapse.domain.user.entity;

import java.util.ArrayList;
import java.util.List;

import com.synapse.synapse.domain.kiosk.order.entity.Order;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "create_qr_user")
@NoArgsConstructor
@AllArgsConstructor
public class CreateQRUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//QR 생성 App 설치시 자동 생성되는 UUID
	@Column(unique = true, nullable = false)
	private String deviceUUID;

	//복원용 - 전화번호 인증 선택사항
	@Column(unique = true)
	private String phoneNumber;

	@Builder.Default
	@Column(nullable = false)
	private boolean isPhoneVerified = false;
	
	@Builder.Default
	@OneToMany(mappedBy = "qrUser", cascade = CascadeType.ALL)
	private List<Order> orders = new ArrayList<>();
}
