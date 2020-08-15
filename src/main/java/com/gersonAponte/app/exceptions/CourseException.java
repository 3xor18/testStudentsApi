package com.gersonAponte.app.exceptions;

import org.springframework.http.HttpStatus;

public class CourseException extends GlobalAppException {

	public CourseException(String code, String message) {
		super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
	}

	private static final long serialVersionUID = 1L;
}
