package com.synapse.synapse.global.model;

import com.synapse.synapse.global.exception.ErrorMessage;

public class ForbiddenException extends SynapseException {
	public ForbiddenException(final ErrorMessage errorMessage) {
		super(errorMessage);
	}
}
