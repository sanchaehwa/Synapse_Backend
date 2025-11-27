package com.synapse.synapse.domain.qrcode.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.synapse.synapse.domain.kiosk.menu.dto.response.DetailMenuResponse;
import com.synapse.synapse.domain.kiosk.menu.dto.response.KioskMenuResponse;
import com.synapse.synapse.domain.qrcode.service.QrcodeService;
import com.synapse.synapse.global.api.ApiTemplate;
import com.synapse.synapse.global.exception.ErrorMessage;
import com.synapse.synapse.global.exception.SuccessMessage;
import com.synapse.synapse.global.model.BadRequestException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/qrcode")
public class QrcodeController {

	private final QrcodeService qrcodeService;

	//메뉴 조회
	@GetMapping("/menu")
	public ApiTemplate<KioskMenuResponse> getAllQrcodeMenus(
		@RequestParam String storeName,
		@RequestParam Long categoryId
	) {
		KioskMenuResponse getMenu = qrcodeService.loadQrCodeMenus(storeName, categoryId);
		return ApiTemplate.ok(SuccessMessage.WK_DATA_RETRIEVED, getMenu);
	}

	//옵션 조회
	@GetMapping("/options")
	public ApiTemplate<List<DetailMenuResponse>> getOptions(
		@RequestParam Long menuId
	) {
		List<DetailMenuResponse> getOption = qrcodeService.loadMenuOptions(menuId);
		return ApiTemplate.ok(SuccessMessage.WK_DATA_RETRIEVED, getOption);
	}

	//메뉴 선택

	@PostMapping("/generate")
	public ResponseEntity<byte[]> qrFromFile(@RequestParam("file") MultipartFile file) {
		try {
			byte[] qrBytes = qrcodeService.generateQrFromFile(file);
			return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_PNG)
				.body(qrBytes);
		} catch (Exception e) {
			throw new BadRequestException(ErrorMessage.GENERATE_QR_CODE_FAILED);
		}
	}
}
