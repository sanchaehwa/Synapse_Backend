package com.synapse.synapse.domain.kiosk.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synapse.synapse.domain.admin.kiosk_management.menu.entity.KioskMenu;
import com.synapse.synapse.domain.admin.kiosk_management.menu.repository.KioskMenuRepository;
import com.synapse.synapse.domain.kiosk.order.dto.request.OrderMenuRequest;
import com.synapse.synapse.domain.kiosk.order.entity.OrderItem;
import com.synapse.synapse.domain.kiosk.order.repository.OrderItemRepository;
import com.synapse.synapse.domain.kiosk.order.repository.OrderRepository;
import com.synapse.synapse.domain.user.entity.User;
import com.synapse.synapse.domain.user.repository.UserRepository;
import com.synapse.synapse.global.exception.ErrorMessage;
import com.synapse.synapse.global.model.BadRequestException;
import com.synapse.synapse.global.model.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderMenuService {

	private final OrderRepository orderRepository;
	private final KioskMenuRepository kioskMenuRepository;
	private final UserRepository userRepository;
	private final OrderItemRepository orderItemRepository;

	//메뉴 선택
	public void choseMenu(OrderMenuRequest orderMenuRequest, Long storeId) {

		//uuid 생성
		String deviceUUID = UUID.randomUUID().toString();
		User user = User.createNormalUser(deviceUUID, new ArrayList<>());
		userRepository.save(user);

		//해당 가게에 속해있는 메뉴인지 확인
		KioskMenu menu = kioskMenuRepository.findByStoreIdAndMenuName(storeId, orderMenuRequest.menuName())
			.orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_MENU));

		Integer inventory = menu.getInventory();
		Integer quantity = orderMenuRequest.quantity();
		//1차 검증
		if (inventory - quantity <= 0 && inventory <= 0 && !menu.isAvailable()) {
			throw new BadRequestException(ErrorMessage.QUANTITY_SHORTAGE);
		}

		menu.changeInventory(quantity);

		BigDecimal totalPrice = menu.getPrice().multiply(BigDecimal.valueOf(quantity));

		OrderItem orderItem = OrderItem.create(user, menu, quantity, totalPrice, new ArrayList<>());

		user.getOrderItems().add(orderItem);
		orderItemRepository.save(orderItem);

	}
	
}
