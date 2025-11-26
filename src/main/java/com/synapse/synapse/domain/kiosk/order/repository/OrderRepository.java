package com.synapse.synapse.domain.kiosk.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synapse.synapse.domain.kiosk.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
