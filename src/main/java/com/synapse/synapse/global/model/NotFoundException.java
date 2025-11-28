package com.synapse.synapse.global.model;

import com.synapse.synapse.global.exception.ErrorMessage;

public class NotFoundException extends SynapseException {
	public NotFoundException(final ErrorMessage errorMessage) {
		super(errorMessage);
	}
}
