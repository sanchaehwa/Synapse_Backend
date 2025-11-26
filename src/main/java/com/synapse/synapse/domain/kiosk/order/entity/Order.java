package com.synapse.synapse.domain.kiosk.order.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.synapse.synapse.domain.kiosk.order.model.OrderType;
import com.synapse.synapse.domain.kiosk.order.model.Status;
import com.synapse.synapse.domain.user.entity.CreateQRUser;
import com.synapse.synapse.domain.user.entity.User;
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
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Order extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long storeId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "payment_id")
	private Payment payment;

	//한사용자 - 동시주문 가능
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "qr_user_id")
	private CreateQRUser qrUser;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private OrderType orderType;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status;

	@Column(nullable = false)
	private Integer queueNumber;

	@Column(nullable = false)
	private BigDecimal totalPrice;

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
	private List<OrderItem> orderItems = new ArrayList<>();

	@Builder
	public Order(Long storeId, Payment payment, User user, List<OrderItem> orderItems,
		OrderType orderType, Status status, Integer queueNumber) {
		this.storeId = storeId;
		this.payment = payment;
		this.user = user;
		this.orderItems = orderItems;
		this.orderType = orderType;
		this.status = status;
		this.queueNumber = queueNumber;
	}

	public static Order create(Long storeId, Payment payment, User user, List<OrderItem> orderItems,
		OrderType orderType, Status status, Integer queueNumber) {
		return new Order(storeId, payment, user, orderItems, orderType, status, queueNumber);
	}

	public void updateTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void changeStatus(Status status) {
		this.status = status;
	}

}
