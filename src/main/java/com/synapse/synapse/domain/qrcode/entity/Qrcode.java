package com.synapse.synapse.domain.qrcode.entity;

import com.synapse.synapse.domain.user.entity.User;
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
@Table(name = "qrcode")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Qrcode extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "qr_code_url")
	private String qrCodeUrl;

	@Column(nullable = false)
	private String storeName;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Builder
	public Qrcode(String qrCodeUrl, String storeName, User user) {
		this.qrCodeUrl = qrCodeUrl;
		this.storeName = storeName;
		this.user = user;
	}

	public static Qrcode create(String qrCodeUrl, String storeName, User user) {
		return new Qrcode(qrCodeUrl, storeName, user);
	}

}
