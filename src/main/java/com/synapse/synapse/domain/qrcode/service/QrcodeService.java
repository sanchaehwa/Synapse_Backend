package com.synapse.synapse.domain.qrcode.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synapse.synapse.domain.admin.qrcode_management.entity.QrcodeMenu;
import com.synapse.synapse.domain.admin.qrcode_management.repository.QrcodeRepository;
import com.synapse.synapse.domain.qrcode.dto.response.FindAllQrcodeMenu;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QrcodeService {

	private final QrcodeRepository qrcodeRepository;

	public List<FindAllQrcodeMenu> getQrMenusForStore(String storeName) {

		List<QrcodeMenu> menus = qrcodeRepository.findQrMenusByStoreName(storeName);

		return menus.stream()
			.map(FindAllQrcodeMenu::fromEntity)
			.toList();
	}

}
