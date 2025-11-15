package com.synapse.synapse.domain.kiosk.menu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synapse.synapse.domain.kiosk.menu.dto.response.DetailMenuResponse;
import com.synapse.synapse.domain.kiosk.menu.dto.response.KioskMenuResponse;
import com.synapse.synapse.domain.kiosk.menu.service.LoadMenuService;
import com.synapse.synapse.global.api.ApiTemplate;
import com.synapse.synapse.global.exception.SuccessMessage;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kiosk/menu")
public class KioskMenuController {

	private final LoadMenuService loadMenuService;

	@GetMapping
	public ApiTemplate<KioskMenuResponse> getMenu(
		@RequestParam String storeName,
		@RequestParam Long categoryId
	) {
		KioskMenuResponse getMenu = loadMenuService.loadKioskMenus(storeName, categoryId);
		return ApiTemplate.ok(SuccessMessage.WK_DATA_RETRIEVED, getMenu);
	}
	
	@GetMapping("/options")
	public ApiTemplate<List<DetailMenuResponse>> getOption(
		@RequestParam Long menuId
	) {
		List<DetailMenuResponse> getOption = loadMenuService.loadMenuOptions(menuId);
		return ApiTemplate.ok(SuccessMessage.WK_DATA_RETRIEVED, getOption);
	}
}
