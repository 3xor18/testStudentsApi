package com.gersonAponte.app.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gersonAponte.app.config.AppConstans;
import com.gersonAponte.app.domain.Course;
import com.gersonAponte.app.exceptions.CourseException;
import com.gersonAponte.app.exceptions.GlobalAppException;
import com.gersonAponte.app.exceptions.InternalServerErrorException;
import com.gersonAponte.app.exceptions.NotFoundException;
import com.gersonAponte.app.jsons.CourseRest;
import com.gersonAponte.app.repository.CourseRepository;
import com.gersonAponte.app.services.CourseService;
import com.gersonAponte.app.utils.UtilsApp;

@Service
public class CourseServiceImpl implements CourseService {

	private final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);

	private final CourseRepository courseRepository;

	public static final ModelMapper modelMapper = new ModelMapper();

	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	/**
	 * Return a Course By Id
	 */
	public CourseRest getCourseById(Long courseId) throws GlobalAppException {
		return modelMapper.map(getCourseEntity(courseId), CourseRest.class);
	}

	/**
	 * Return a list of courses
	 */
	public List<CourseRest> getCourses() throws GlobalAppException {
		final List<Course> courseEntity = courseRepository.findAll();
		return courseEntity.stream().map(service -> modelMapper.map(service, CourseRest.class))
				.collect(Collectors.toList());
	}

	/**
	 * save a course
	 * 
	 * @throws CourseException
	 */
	public CourseRest createCourse(CourseRest courseRest) throws GlobalAppException {
		Course validCourse = validationNewCourse(courseRest);
		courseExistByCode(validCourse.getCode());
		final Course course = new Course();
		course.setCode(courseRest.getCode());
		course.setName(courseRest.getName());
		Course newCourse = null;
		try {
			newCourse = courseRepository.save(course);
		} catch (final Exception e) {
			log.error(AppConstans.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(AppConstans.ERROR_500, AppConstans.INTERNAL_SERVER_ERROR);
		}
		return modelMapper.map(newCourse, CourseRest.class);
	}

	// delete a course
	public String deleteCourse(Long id) throws GlobalAppException {
		Course courseExist = courseExist(id);
		try {
			courseRepository.deleteById(courseExist.getId());
		} catch (final Exception e) {
			log.error(AppConstans.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(AppConstans.ERROR_500, AppConstans.INTERNAL_SERVER_ERROR);
		}
		return AppConstans.COURSE_DELETED;
	}

	// Edit a Course
	public CourseRest editCourse(CourseRest courseRest) throws GlobalAppException {
		courseExistByCode(courseRest.getCode());
		Course validCourse = validationNewCourse(courseRest);
		Course courseEdit = null;
		try {
			courseEdit = courseRepository.save(validCourse);
		} catch (final Exception e) {
			log.error(AppConstans.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(AppConstans.ERROR_500, AppConstans.INTERNAL_SERVER_ERROR);
		}
		return modelMapper.map(courseEdit, CourseRest.class);
	}

	/////// Privates Class
	/**
	 * 
	 * @param courseId
	 * @return cast to json to Entity Course
	 * @throws GlobalAppException
	 */
	private Course getCourseEntity(Long courseId) throws GlobalAppException {
		return courseRepository.findById(courseId)
				.orElseThrow(() -> new NotFoundException(AppConstans.ERROR_404, AppConstans.COURSE_NOT_FOUND));
	}

	private boolean courseExistByCode(String codeCourse) throws GlobalAppException {
		Course course = courseRepository.findByCode(codeCourse).orElse(null);
		if (course != null) {
			throw new CourseException(AppConstans.ERROR_400, AppConstans.COURSE_ALL_READY_EXIST_WITH_THIS_CODE);
		}
		return false;
	}

	/**
	 * @param courseIn
	 * @return a course without id
	 * @throws GlobalAppException
	 */
	private Course validationNewCourse(CourseRest courseIn) throws GlobalAppException {
		String code = UtilsApp.validationNotNullAndMaxLengt(courseIn.getCode(), AppConstans.COURSE_CODE,
				AppConstans.MAX_LENG_CODE);
		String name = UtilsApp.validationNotNullAndMaxLengt(courseIn.getName(), AppConstans.COURSE_NAME,
				AppConstans.MAX_LENG_NAME);
		Course course = new Course();
		course.setCode(code);
		course.setName(name);
		return course;
	}

	/**
	 * @param idCourse
	 * @return Check a course exist in DB
	 * @throws GlobalAppException
	 */
	private Course courseExist(Long idCourse) throws GlobalAppException {
		return courseRepository.findById(idCourse)
				.orElseThrow(() -> new NotFoundException(AppConstans.ERROR_404, AppConstans.COURSE_NOT_FOUND));
	}

	/**
	 *Return Page of courses
	 */
	@Override
	public Page<CourseRest> findCoursePage(Pageable pageable) throws GlobalAppException {
		final Page<Course> courses = courseRepository.findAll(pageable);
		List<CourseRest> coursesList = courses.stream().map(service -> modelMapper.map(service, CourseRest.class))
				.collect(Collectors.toList());
		final Page<CourseRest> courseRes = new PageImpl<>(coursesList);
		return courseRes;
	}

}
