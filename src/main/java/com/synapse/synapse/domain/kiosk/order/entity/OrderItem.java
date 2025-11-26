package com.synapse.synapse.domain.kiosk.order.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.synapse.synapse.domain.admin.kiosk_management.menu.entity.KioskMenu;
import com.synapse.synapse.domain.user.entity.User;

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
@Table(name = "order_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "kiosk_menu_id")
	private KioskMenu kioskMenu;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	// @ManyToOne(fetch = FetchType.LAZY, optional = false)
	// @JoinColumn(name = "qrcode_menu_id")
	// private QrcodeMenu qrcodeMenu;

	// @Enumerated(EnumType.STRING)
	// @Column(nullable = false, length = 20)
	// private PlatformType platformType;

	@Column(nullable = false)
	private Integer quantity;

	@Column(nullable = false)
	private BigDecimal totalPrice;
	
	@OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private List<OrderItemOption> orderItemOptions = new ArrayList<>();

	@Builder
	public OrderItem(KioskMenu kioskMenu, Integer quantity,
		BigDecimal totalPrice, List<OrderItemOption> orderItemOptions) {
		this.kioskMenu = kioskMenu;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.orderItemOptions = orderItemOptions;
	}

	public static OrderItem create(User user, KioskMenu kioskMenu, Integer quantity,
		BigDecimal totalPrice, List<OrderItemOption> orderItemOptions) {

		OrderItem orderItem = new OrderItem(kioskMenu, quantity, totalPrice, orderItemOptions);
		orderItem.user = user;
		return orderItem;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
