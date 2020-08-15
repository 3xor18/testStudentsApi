package com.gersonAponte.app.exceptions;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import com.gersonAponte.app.domain.ErrorDto;

public class StudentException extends GlobalAppException {

	private static final long serialVersionUID = 1L;

	public StudentException(String code, String message) {
		super(code, HttpStatus.NOT_FOUND.value(), message);
	}

	public StudentException(String code, String message, ErrorDto data) {
		super(code, HttpStatus.NOT_FOUND.value(), message, Arrays.asList(data));
	}
}
