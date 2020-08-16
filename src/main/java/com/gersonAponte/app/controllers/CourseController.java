package com.gersonAponte.app.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.gersonAponte.app.config.AppConstans;
import com.gersonAponte.app.exceptions.CourseException;
import com.gersonAponte.app.exceptions.GlobalAppException;
import com.gersonAponte.app.exceptions.InternalServerErrorException;
import com.gersonAponte.app.exceptions.NotFoundException;
import com.gersonAponte.app.jsons.CourseRest;
import com.gersonAponte.app.services.CourseService;

/**
 * REST controller for managing {@link com.gerson.app.domain.Course}.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api" + "/v1")
public class CourseController {
	private final Logger log = LoggerFactory.getLogger(CourseController.class);

	@Value("${PAGINARBY}")
	private int paginarBy;

	@Autowired
	CourseService courseService;

	public CourseController() {
	}

	/**
	 * @param courseId
	 * @return a Course found By Id
	 * @throws GlobalAppException
	 */
	@RequestMapping(value = "course/{courseId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCourseById(@PathVariable Long courseId) {
		CourseRest courseResponse = null;
		Map<String, Object> response = new HashMap<>();
		try {
			log.info("try to find a course");
			courseResponse = courseService.getCourseById(courseId);
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
		return new ResponseEntity<CourseRest>(courseResponse, HttpStatus.OK);
	}

	/**
	 * @return all Courses in DB
	 * @throws GlobalAppException
	 */
	@RequestMapping(value = "courses", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCourses() {
		Map<String, Object> response = new HashMap<>();
		List<CourseRest> coursesRest = new ArrayList<>();
		try {
			coursesRest = courseService.getCourses();
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
		return new ResponseEntity<List<CourseRest>>(coursesRest, HttpStatus.OK);
	}

	/**
	 * @param courseRest
	 * @return String COURSE_CREATED
	 * @throws GlobalAppException
	 */
	@RequestMapping(value = "course", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createCourse(@RequestBody CourseRest courseRest) {
		Map<String, Object> response = new HashMap<>();
		CourseRest newCourse;
		try {
			newCourse = courseService.createCourse(courseRest);
		} catch (CourseException courseEx) {
			courseEx.printStackTrace();
			response.put("message", "ERROR_IN_REQUEST");
			response.put("error", courseEx.getMessage());
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
		return new ResponseEntity<CourseRest>(newCourse, HttpStatus.CREATED);
	}

	/**
	 * @param id
	 * @return String COURSE_DELETAED
	 * @throws GlobalAppException
	 */
	@RequestMapping(value = "course", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteCourse(@RequestParam Long id) {
		Map<String, Object> response = new HashMap<>();
		String message = null;
		try {
			message = courseService.deleteCourse(id);
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
	 * @param courseRest
	 * @return A Edit course
	 */
	@RequestMapping(value = "course", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> editCourse(@RequestBody CourseRest courseRest) {
		Map<String, Object> response = new HashMap<>();
		CourseRest newCourse = null;
		try {
			newCourse = courseService.editCourse(courseRest);
		} catch (CourseException courseEx) {
			courseEx.printStackTrace();
			response.put("message", "ERROR_IN_REQUEST");
			response.put("error", courseEx.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
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
		return new ResponseEntity<CourseRest>(newCourse, HttpStatus.OK);
	}

	/**
	 * @param page
	 * @return Page of Courses
	 */
	@RequestMapping(value = "courses/page/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCoursePage(@PathVariable Integer page) {
		Map<String, Object> response = new HashMap<>();
		Pageable pageable = PageRequest.of(page, AppConstans.PAGINADOBY);
		try {
			Page<CourseRest> courses = courseService.findCoursePage(pageable);
			return new ResponseEntity<Page<CourseRest>>(courses, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error", e.getCause());
			response.put("message", "COURSES_NOT_FOUND");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
	}

}
