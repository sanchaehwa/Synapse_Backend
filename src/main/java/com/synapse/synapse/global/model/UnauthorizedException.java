package com.synapse.synapse.global.model;

import com.synapse.synapse.global.exception.ErrorMessage;

public class UnauthorizedException extends SynapseException {

	public UnauthorizedException(final ErrorMessage errorMessage) {
		super(errorMessage);
	}

}
