package com.gersonAponte.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gersonAponte.app.exceptions.GlobalAppException;
import com.gersonAponte.app.exceptions.InternalServerErrorException;
import com.gersonAponte.app.exceptions.NotFoundException;
import com.gersonAponte.app.exceptions.StudentException;
import com.gersonAponte.app.jsons.StudentsRest;
import com.gersonAponte.app.services.StudentService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST controller for managing {@link com.gerson.app.domain.Student}.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api" + "/v1")
public class StudentController {
	private final Logger log = LoggerFactory.getLogger(StudentController.class);

	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	/**
	 * @param studentId
	 * @return A Student
	 */
	@GetMapping("students/{studentId}")
	public ResponseEntity<?> getStudentById(@PathVariable Long studentId) {
		StudentsRest studentRest = null;
		Map<String, Object> response = new HashMap<>();
		try {
			log.info("try to find a Student");
			studentRest = studentService.getStudent(studentId);
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
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message", "INTERNAL_SERVER_ERROR");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<StudentsRest>(studentRest, HttpStatus.OK);
	}

	/**
	 * @return all Students by list
	 */
	@GetMapping("students")
	public ResponseEntity<?> getStudents() {
		Map<String, Object> response = new HashMap<>();
		List<StudentsRest> studentsRest = new ArrayList<>();
		try {
			studentsRest = studentService.getAllStudents();
		} catch (StudentException estudentEx) {
			estudentEx.printStackTrace();
			response.put("message", "ERROR_IN_REQUEST");
			response.put("error", estudentEx.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		} catch (GlobalAppException globalE) {
			log.info("Global Exception");
			response.put("message", "ERROR_IN_REQUEST");
			response.put("error", globalE.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			response.put("message", "INTERNAL_SERVER_ERROR");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<StudentsRest>>(studentsRest, HttpStatus.OK);
	}

	/**
	 * @param studentRest
	 * @return a New Srudent
	 */
	@PostMapping("students")
	public ResponseEntity<?> createStudent(@RequestBody StudentsRest studentRest) {
		Map<String, Object> response = new HashMap<>();
		StudentsRest studentNew;
		try {
			studentNew = studentService.createStudent(studentRest);
		} catch (StudentException estudentEx) {
			estudentEx.printStackTrace();
			response.put("message", "ERROR_IN_REQUEST");
			response.put("error", estudentEx.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		} catch (InternalServerErrorException internalServer) {
			internalServer.printStackTrace();
			response.put("message", "INTERNAL_SERVER_ERROR");
			response.put("error", internalServer.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message", "INTERNAL_SERVER_ERROR");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<StudentsRest>(studentNew, HttpStatus.CREATED);
	}

	/**
	 * @param id
	 * @return String Success
	 */
	@DeleteMapping("students")
	public ResponseEntity<?> deleteStudent(@RequestParam Long id) {
		Map<String, Object> response = new HashMap<>();
		String message = null;
		try {
			message = studentService.deleteStudent(id);
		} catch (StudentException estudentEx) {
			estudentEx.printStackTrace();
			response.put("message", "ERROR_IN_REQUEST");
			response.put("error", estudentEx.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		} catch (NotFoundException notFound) {
			notFound.printStackTrace();
			response.put("message", "NOT_FOUND_EXCEPTION");
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

	/**
	 * @param studentRest
	 * @return a Student Edited
	 */
	@PutMapping("students")
	public ResponseEntity<?> editStudent(@RequestBody StudentsRest studentRest) {
		Map<String, Object> response = new HashMap<>();
		StudentsRest newStudent = null;
		try {
			newStudent = studentService.editStudent(studentRest);
		} catch (StudentException estudentEx) {
			estudentEx.printStackTrace();
			response.put("message", "ERROR_IN_REQUEST");
			response.put("error", estudentEx.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		} catch (InternalServerErrorException internalServer) {
			internalServer.printStackTrace();
			response.put("message", "INTERNAL_SERVER_ERROR");
			response.put("error", internalServer.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message", "INTERNAL_SERVER_ERROR");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<StudentsRest>(newStudent, HttpStatus.OK);
	}
}
