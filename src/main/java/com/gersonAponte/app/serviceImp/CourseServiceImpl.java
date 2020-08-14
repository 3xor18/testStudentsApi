package com.gersonAponte.app.serviceImp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gersonAponte.app.domain.Course;
import com.gersonAponte.app.repository.CourseRepository;
import com.gersonAponte.app.services.CourseService;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Course}.
 */
@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	private final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);

	private final CourseRepository courseRepository;

	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	/**
	 * Save a course.
	 *
	 * @param course the entity to save.
	 * @return the persisted entity.
	 */
	@Override
	public Course save(Course course) {
		log.debug("Request to save Course : {}", course);
		return courseRepository.save(course);
	}

	/**
	 * Get all the courses.
	 *
	 * @return the list of entities.
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Course> findAll() {
		log.debug("Request to get all Courses");
		return courseRepository.findAll();
	}

	/**
	 * Get one course by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<Course> findOne(Long id) {
		log.debug("Request to get Course : {}", id);
		return courseRepository.findById(id);
	}

	/**
	 * Delete the course by id.
	 *
	 * @param id the id of the entity.
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete Course : {}", id);
		courseRepository.deleteById(id);
	}

	//Return All Courses Page by 5
	@Override
	public Page<Course> findAllCoursesPage(Pageable pageable) {
		return courseRepository.findAll(pageable);
	}
}
