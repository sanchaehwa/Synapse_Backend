package com.synapse.synapse.global.dto;

import com.synapse.synapse.global.exception.ErrorMessage;

public record ErrorResponse(
	int status,
	String code,
	String message
) {
	public static ErrorResponse of(final ErrorMessage errorMessage) {
		return new ErrorResponse(
			errorMessage.getStatus(),
			errorMessage.getCode(),
			errorMessage.getMessage()
		);
	}

}
