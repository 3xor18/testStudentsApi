package com.gersonAponte.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gersonAponte.app.domain.Course;
import com.gersonAponte.app.services.CourseService;


/**
 * REST controller for managing {@link com.gerson.app.domain.Course}.
 */
@RestController
@RequestMapping("/api")
public class CourseController {
	private final Logger log = LoggerFactory.getLogger(CourseController.class);

	@Value("${PAGINARBY}")
	private int paginarBy;

	@Value("${application.name}")
	private String applicationName;

	private final CourseService courseService;

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	/**
	 * @return all Courses in DB, List Mode
	 */
	@GetMapping("/courses")
	public List<Course> getAllCourses() {
		log.debug("REST request to get all Courses");
		return courseService.findAll();
	}

	/**
	 * @param page
	 * @return Page of all courses for number of page. page by 5
	 */
	@GetMapping("/courses/page/{page}")
	public ResponseEntity<?> getAllCoursesPage(@PathVariable Integer page) {
		log.debug("REST request to get all course Page");
		Map<String, Object> response = new HashMap<>();
		Pageable pageable = PageRequest.of(page, paginarBy);
		try {
			Page<Course> courses = courseService.findAllCoursesPage(pageable);
			return new ResponseEntity<Page<Course>>(courses, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Error", e.getCause());
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * {@code GET  /courses/:id} : get the "id" course.
	 * 
	 * @param id the id of the course to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the course, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/courses/{id}")
	public ResponseEntity<?> getCourse(@PathVariable Long id) {
		log.debug("REST request to get one course by id");
		Map<String, Object> response = new HashMap<>();
		Optional<Course> course = null;
		try {
			course = courseService.findOne(id);
			if (course.isEmpty()) {
				log.error("Course does not exist");
				response.put("error", "Course does not exist");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Optional<Course>>(course, HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error("Error", e.getCause());
			response.put("message", "Internal Server Error");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @param course
	 * @return a new Course || error (has no id, internal server error)
	 */
	@PostMapping("/courses")
	public ResponseEntity<?> createCourse(@RequestBody Course course) {
		log.debug("REST request to save Course : {}", course);
		Map<String, Object> response = new HashMap<>();
		Course newCourse=null;
		try {
			newCourse=courseService.save(course);
			return new ResponseEntity<Course>(newCourse, HttpStatus.CREATED);
		} catch (Exception e) {
			log.error("Error", e.getCause());
			response.put("message", "Internal Server Error");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
