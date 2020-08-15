package com.gersonAponte.app.exceptions;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import com.gersonAponte.app.domain.ErrorDto;

public class NotFoundException extends GlobalAppException {

	public NotFoundException(String code, String message) {
		super(code, HttpStatus.NOT_FOUND.value(), message);
	}

	public NotFoundException(String code, String message, ErrorDto data) {
		super(code, HttpStatus.NOT_FOUND.value(), message, Arrays.asList(data));
	}

	private static final long serialVersionUID = 1L;

}
