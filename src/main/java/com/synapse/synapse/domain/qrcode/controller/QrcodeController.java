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

import com.synapse.synapse.domain.qrcode.dto.response.FindAllQrcodeMenu;
import com.synapse.synapse.domain.qrcode.service.QrcodeService;
import com.synapse.synapse.global.dto.SuccessResponse;
import com.synapse.synapse.global.exception.SuccessMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/qrcode")
public class QrcodeController {

	private final QrcodeService qrcodeService;

	@GetMapping("/menus")
	public ResponseEntity<SuccessResponse<FindAllQrcodeMenu>> getAllQrcodeMenus(
		@RequestParam(value = "stroeName") String storeName
	) {
		List<FindAllQrcodeMenu> findAllQrcodeMenus = qrcodeService.getQrMenusForStore(storeName);
		return ResponseEntity.ok(SuccessResponse.of(SuccessMessage.WK_DATA_RETRIEVED, findAllQrcodeMenus));
	}

	@PostMapping("/generate")
	public ResponseEntity<byte[]> qrFromFile(@RequestParam("file") MultipartFile file) {
		try {
			byte[] qrBytes = qrcodeService.generateQrFromFile(file);
			return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_PNG)
				.body(qrBytes);
		} catch (Exception e) {
			log.error("QR 코드 생성 실패", e);
			return ResponseEntity.status(500).build();
		}
	}
}
