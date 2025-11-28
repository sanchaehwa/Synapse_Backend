package com.synapse.synapse.domain.qrcode.dto.response;

import lombok.Builder;

@Builder
public record QrcodeResponse(
	String uuid,
	String txtFilePath,
	String qrCodeBase64
) {
}
