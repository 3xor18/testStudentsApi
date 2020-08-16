package com.gersonAponte.app.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gersonAponte.app.exceptions.CourseException;
import com.gersonAponte.app.exceptions.InternalServerErrorException;
import com.gersonAponte.app.exceptions.NotFoundException;
import com.gersonAponte.app.exceptions.StudentException;
import com.gersonAponte.app.jsons.StudentInCourseRest;
import com.gersonAponte.app.services.StudentInCourseService;

@RestController
@CrossOrigin(origins = "http://localhost:9090")
@RequestMapping("/api" + "/v1")
public class StudentInCourseController {

	private final Logger log = LoggerFactory.getLogger(StudentInCourseController.class);

	private final StudentInCourseService service;

	public StudentInCourseController(StudentInCourseService service) {
		this.service = service;
	}

	@RequestMapping(value = "studentincourse/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getById(@PathVariable Long id) {
		StudentInCourseRest entityRest;
		Map<String, Object> response = new HashMap<>();
		try {
			log.info("try to find a Student and course");
			entityRest = service.getOne(id);
		} catch (CourseException courseEx) {
			courseEx.printStackTrace();
			response.put("message", "ERROR_IN_REQUEST");
			response.put("error", courseEx.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		} catch (StudentException estudentEx) {
			estudentEx.printStackTrace();
			response.put("message", "ERROR_IN_REQUEST");
			response.put("error", estudentEx.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException notFound) {
			notFound.printStackTrace();
			response.put("message", "ERROR_IN_REQUEST");
			response.put("error", notFound.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		} catch (InternalServerErrorException internalError) {
			internalError.printStackTrace();
			response.put("message", "INTERNAL_SERVER_ERROR");
			response.put("error", internalError.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message", "INTERNAL_SERVER_ERROR");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<StudentInCourseRest>(entityRest, HttpStatus.OK);
	}

	@RequestMapping(value = "studentincourse", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAll() {
		Map<String, Object> response = new HashMap<>();
		List<StudentInCourseRest> entityRest = new ArrayList<>();
		try {
			entityRest = service.getAll();
		} catch (CourseException courseEx) {
			courseEx.printStackTrace();
			response.put("message", "ERROR_IN_REQUEST");
			response.put("error", courseEx.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		} catch (StudentException estudentEx) {
			estudentEx.printStackTrace();
			response.put("message", "ERROR_IN_REQUEST");
			response.put("error", estudentEx.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException notFound) {
			notFound.printStackTrace();
			response.put("message", "ERROR_IN_REQUEST");
			response.put("error", notFound.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		} catch (InternalServerErrorException internalError) {
			internalError.printStackTrace();
			response.put("message", "INTERNAL_SERVER_ERROR");
			response.put("error", internalError.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message", "INTERNAL_SERVER_ERROR");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<StudentInCourseRest>>(entityRest, HttpStatus.OK);
	}

	@RequestMapping(value = "studentincourse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody StudentInCourseRest entityRest) {
		Map<String, Object> response = new HashMap<>();
		StudentInCourseRest entityNew;
		try {
			entityNew = service.createOne(entityRest);
		} catch (CourseException courseEx) {
			courseEx.printStackTrace();
			response.put("message", "ERROR_IN_REQUEST");
			response.put("error", courseEx.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		} catch (StudentException estudentEx) {
			estudentEx.printStackTrace();
			response.put("message", "ERROR_IN_REQUEST");
			response.put("error", estudentEx.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException notFound) {
			notFound.printStackTrace();
			response.put("message", "ERROR_IN_REQUEST");
			response.put("error", notFound.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		} catch (InternalServerErrorException internalError) {
			internalError.printStackTrace();
			response.put("message", "INTERNAL_SERVER_ERROR");
			response.put("error", internalError.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message", "INTERNAL_SERVER_ERROR");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<StudentInCourseRest>(entityNew, HttpStatus.CREATED);
	}

	@RequestMapping(value = "studentincourse", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@RequestParam Long id) {
		Map<String, Object> response = new HashMap<>();
		String message = null;
		try {
			message = service.deleteById(id);
		} catch (CourseException courseEx) {
			courseEx.printStackTrace();
			response.put("message", "ERROR_IN_REQUEST");
			response.put("error", courseEx.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		} catch (StudentException estudentEx) {
			estudentEx.printStackTrace();
			response.put("message", "ERROR_IN_REQUEST");
			response.put("error", estudentEx.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException notFound) {
			notFound.printStackTrace();
			response.put("message", "ERROR_IN_REQUEST");
			response.put("error", notFound.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		} catch (InternalServerErrorException internalError) {
			internalError.printStackTrace();
			response.put("message", "INTERNAL_SERVER_ERROR");
			response.put("error", internalError.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message", "INTERNAL_SERVER_ERROR");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(message, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "studentincourse", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> editStudent(@RequestBody StudentInCourseRest entityRest) {
		Map<String, Object> response = new HashMap<>();
		StudentInCourseRest newEntity = null;
		try {
			newEntity = service.edit(entityRest);
		} catch (CourseException courseEx) {
			courseEx.printStackTrace();
			response.put("message", "ERROR_IN_REQUEST");
			response.put("error", courseEx.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		} catch (StudentException estudentEx) {
			estudentEx.printStackTrace();
			response.put("message", "ERROR_IN_REQUEST");
			response.put("error", estudentEx.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException notFound) {
			notFound.printStackTrace();
			response.put("message", "ERROR_IN_REQUEST");
			response.put("error", notFound.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		} catch (InternalServerErrorException internalError) {
			internalError.printStackTrace();
			response.put("message", "INTERNAL_SERVER_ERROR");
			response.put("error", internalError.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message", "INTERNAL_SERVER_ERROR");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<StudentInCourseRest>(newEntity, HttpStatus.OK);
	}

}
