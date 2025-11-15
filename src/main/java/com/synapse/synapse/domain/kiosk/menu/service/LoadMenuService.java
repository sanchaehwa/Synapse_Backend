package com.synapse.synapse.domain.kiosk.menu.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synapse.synapse.domain.admin.kiosk_management.menu.repository.KioskMenuCategoryRepository;
import com.synapse.synapse.domain.admin.kiosk_management.menu.repository.KioskMenuOptionRepository;
import com.synapse.synapse.domain.admin.kiosk_management.menu.repository.KioskMenuRepository;
import com.synapse.synapse.domain.admin.signup.repository.AdminRepository;
import com.synapse.synapse.domain.kiosk.menu.dto.response.DetailMenuResponse;
import com.synapse.synapse.domain.kiosk.menu.dto.response.KioskMenuResponse;
import com.synapse.synapse.domain.kiosk.menu.dto.response.LoadKioskMenu;
import com.synapse.synapse.domain.kiosk.menu.dto.response.LoadKioskMenuCategory;
import com.synapse.synapse.domain.kiosk.menu.dto.response.OptionMenuDto;
import com.synapse.synapse.domain.kiosk.order.service.OrderMenuService;
import com.synapse.synapse.global.exception.ErrorMessage;
import com.synapse.synapse.global.model.NotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoadMenuService {

	private final KioskMenuRepository kioskMenuRepository;
	private final KioskMenuCategoryRepository kioskMenuCategoryRepository;
	private final KioskMenuOptionRepository kioskMenuOptionRepository;
	private final AdminRepository adminRepository;
	private final OrderMenuService orderMenuService;

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

		List<LoadKioskMenuCategory> categories = kioskMenuCategoryRepository.getAllCategoriesByStoreName(storeName)
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

	public List<DetailMenuResponse> loadMenuOptions(Long menuId) {
		validateFindMenu(menuId);

		return kioskMenuOptionRepository.findAllByKioskMenuId(menuId)
			.stream()
			.map(oc -> DetailMenuResponse.builder()
				.id(oc.getId())
				.categoryName(oc.getName())
				.menuOptions(
					oc.getMenuOptions().stream()
						.map(oi -> new OptionMenuDto(
							oi.getOptionName(),
							oi.getPrice()
						))
						.collect(Collectors.toList())
				)
				.build()
			)
			.collect(Collectors.toList());
	}

	private void validateStoreAndCategory(String storeName, Long categoryId) {
		if (!adminRepository.existsByStoreName(storeName)) {
			throw new NotFoundException(ErrorMessage.NOT_FOUND_STORE);
		}

		if (!kioskMenuRepository.existsByCategoryId(categoryId)) {
			throw new NotFoundException(ErrorMessage.NOT_FOUND_CATEGORY);
		}
	}

	private void validateFindMenu(Long menuId) {
		if (!kioskMenuRepository.existsById(menuId)) {
			throw new NotFoundException(ErrorMessage.NOT_FOUND_MENU);
		}
	}

}
