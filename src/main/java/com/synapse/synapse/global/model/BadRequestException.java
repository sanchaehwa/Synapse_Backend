package com.synapse.synapse.global.model;

import com.synapse.synapse.global.exception.ErrorMessage;

public class BadRequestException extends SynapseException {
	public BadRequestException(final ErrorMessage errorMessage) {
		super(errorMessage);
	}
}
