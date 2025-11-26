package com.synapse.synapse.domain.qrcode.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
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

	public byte[] generateQrFromFile(MultipartFile file) throws IOException, WriterException {
		String content = new String(file.getBytes()); // 파일 내용을 문자열로 변환
		int width = 200;
		int height = 200;

		BitMatrix matrix = new MultiFormatWriter()
			.encode(content, BarcodeFormat.QR_CODE, width, height);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(matrix, "PNG", out);

		return out.toByteArray();
	}

}
