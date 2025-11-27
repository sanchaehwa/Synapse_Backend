package com.synapse.synapse.domain.kiosk.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synapse.synapse.domain.kiosk.order.entity.OrderItemOption;

public interface OrderItemOptionRepository extends JpaRepository<OrderItemOption, Long> {
}
