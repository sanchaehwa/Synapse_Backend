package com.synapse.synapse.domain.kiosk.menu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synapse.synapse.domain.admin.kiosk_management.menu.repository.KioskMenuRepository;
import com.synapse.synapse.domain.admin.signup.repository.AdminRepository;
import com.synapse.synapse.domain.kiosk.menu.dto.response.KioskMenuResponse;
import com.synapse.synapse.domain.kiosk.menu.dto.response.LoadKioskMenu;
import com.synapse.synapse.domain.kiosk.menu.dto.response.LoadKioskMenuCategory;
import com.synapse.synapse.global.exception.ErrorMessage;
import com.synapse.synapse.global.model.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoadMenuService {

	private final KioskMenuRepository kioskMenuRepository;
	private final AdminRepository adminRepository;

	public KioskMenuResponse loadKioskMenus(String storeName, Long categoryId) {

		validateStoreAndCategory(storeName, categoryId);

		List<LoadKioskMenu> menus = kioskMenuRepository.findMenusByStoreName(storeName, categoryId)
			.stream()
			.map(menu -> new LoadKioskMenu(
				menu.getMenuName(),
				menu.getImageUrl(),
				menu.getCategory().getCategoryName(),
				menu.getPrice()
			))
			.toList();

		List<LoadKioskMenuCategory> categories = kioskMenuRepository.getAllCategoriesByStoreName(storeName)
			.stream()
			.map(cat -> new LoadKioskMenuCategory(
				cat.getId(),
				cat.getCategoryName()
			))
			.toList();

		return KioskMenuResponse.builder()
			.loadKioskMenus(menus)
			.loadKioskMenuCategories(categories)
			.build();
	}

	private void validateStoreAndCategory(String storeName, Long categoryId) {
		if (!adminRepository.existsByStoreName(storeName)) {
			throw new NotFoundException(ErrorMessage.NOT_FOUND_STORE);
		}

		if (!kioskMenuRepository.existsByCategoryId(categoryId)) {
			throw new NotFoundException(ErrorMessage.NOT_FOUND_CATEGORY);
		}
	}
}
