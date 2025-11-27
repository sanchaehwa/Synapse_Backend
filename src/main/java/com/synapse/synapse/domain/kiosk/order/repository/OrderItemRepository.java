package com.synapse.synapse.domain.kiosk.order.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synapse.synapse.domain.kiosk.order.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	//deviceUUID로 주문 내역 조회(장바구니)
	List<OrderItem> findByUser_DeviceUUIDAndOrderIsNull(String deviceUUID);

	//가장 최근 주문 1개 조회
	Optional<OrderItem> findFirstByUser_DeviceUUIDOrderByIdDesc(String deviceUUID);
}
