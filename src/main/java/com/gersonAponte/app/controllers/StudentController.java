package com.gersonAponte.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gersonAponte.app.domain.Student;
import com.gersonAponte.app.services.StudentService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.gerson.app.domain.Student}.
 */
@RestController
@RequestMapping("/api")
public class StudentController {
	private final Logger log = LoggerFactory.getLogger(StudentController.class);

	private static final String ENTITY_NAME = "student";

	@Value("${application.name}")
	private String applicationName;

	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	
}
