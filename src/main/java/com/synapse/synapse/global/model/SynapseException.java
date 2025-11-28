package com.synapse.synapse.global.model;

import com.synapse.synapse.global.exception.ErrorMessage;

import lombok.Getter;

@Getter
public class SynapseException extends RuntimeException {

	private final ErrorMessage errorMessage;

	public SynapseException(ErrorMessage errorMessage) {
		super(errorMessage.getMessage());
		this.errorMessage = errorMessage;
	}

}
