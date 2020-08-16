package com.gersonAponte.app.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gersonAponte.app.config.AppConstans;
import com.gersonAponte.app.domain.Course;
import com.gersonAponte.app.domain.Student;
import com.gersonAponte.app.domain.StudentInCourse;
import com.gersonAponte.app.exceptions.CourseException;
import com.gersonAponte.app.exceptions.GlobalAppException;
import com.gersonAponte.app.exceptions.InternalServerErrorException;
import com.gersonAponte.app.exceptions.NotFoundException;
import com.gersonAponte.app.jsons.StudentInCourseRest;
import com.gersonAponte.app.repository.StudentInCourseRepository;
import com.gersonAponte.app.services.StudentInCourseService;

@Service
@Transactional
public class StudentInCourseImpl implements StudentInCourseService {

	private final Logger log = LoggerFactory.getLogger(StudentInCourseImpl.class);

	public static final ModelMapper modelMapper = new ModelMapper();

	private final StudentInCourseRepository repository;

	private final CourseServiceImpl courseServiceImp;

	private final StudentServiceImpl studentServiceImp;

	public StudentInCourseImpl(StudentInCourseRepository repository, CourseServiceImpl courseServiceImp,
			StudentServiceImpl studentServiceImp) {
		this.repository = repository;
		this.courseServiceImp = courseServiceImp;
		this.studentServiceImp = studentServiceImp;
	}

	public StudentInCourseRest getOne(Long id) throws GlobalAppException {
		return modelMapper.map(findEntity(id), StudentInCourseRest.class);
	}

	public List<StudentInCourseRest> getAll() throws GlobalAppException {
		final List<StudentInCourse> entity = repository.findAll();
		return entity.stream().map(service -> modelMapper.map(service, StudentInCourseRest.class))
				.collect(Collectors.toList());
	}

	public StudentInCourseRest createOne(StudentInCourseRest studentInCourseRest) throws GlobalAppException {
		// For a course
		Course course = courseServiceImp.courseExist(studentInCourseRest.getCourse().getId());
		// For a Student
		Student student = studentServiceImp.getStudentEntity(studentInCourseRest.getStudent().getId());
		StudentInCourse entity = existCourseAndStudent(course, student);
		if (entity != null) {
			throw new CourseException(AppConstans.ERROR_404, "STUDENT_IS_ALREADY_REGISTERED_IN_COURSE");
		}
		StudentInCourse entityBD = new StudentInCourse();
		entityBD.setCourse(course);
		entityBD.setStudent(student);

		StudentInCourse newEntity = null;
		try {
			newEntity = repository.save(entityBD);
		} catch (final Exception e) {
			log.error(AppConstans.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(AppConstans.ERROR_500, AppConstans.INTERNAL_SERVER_ERROR);
		}
		return modelMapper.map(newEntity, StudentInCourseRest.class);
	}

	public String deleteById(Long id) throws GlobalAppException {
		StudentInCourse entity = findEntity(id);
		try {
			repository.deleteById(entity.getId());
		} catch (final Exception e) {
			log.error(AppConstans.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(AppConstans.ERROR_500, AppConstans.INTERNAL_SERVER_ERROR);
		}
		return AppConstans.DELETE_SUCCES;
	}

	public StudentInCourseRest edit(StudentInCourseRest studentInCourseRest) throws GlobalAppException {
		StudentInCourse entity = findEntity(studentInCourseRest.getId());
		// For a course
		Course course = courseServiceImp.courseExist(studentInCourseRest.getCourse().getId());
		// For a Student
		Student student = studentServiceImp.getStudentEntity(studentInCourseRest.getStudent().getId());		
		StudentInCourse entityExist = existCourseAndStudent(course, student);
		if (entityExist != null) {
			throw new CourseException(AppConstans.ERROR_404, "STUDENT_IS_ALREADY_REGISTERED_IN_COURSE");
		}
		entity.setCourse(course);
		entity.setStudent(student);
		StudentInCourse newEntity = null;
		try {
			newEntity = repository.save(entity);
		} catch (final Exception e) {
			log.error(AppConstans.INTERNAL_SERVER_ERROR, e);
			throw new InternalServerErrorException(AppConstans.ERROR_500, AppConstans.INTERNAL_SERVER_ERROR);
		}
		return modelMapper.map(newEntity, StudentInCourseRest.class);
	}

	private StudentInCourse findEntity(Long id) throws GlobalAppException {
		return repository.findById(id)
				.orElseThrow(() -> new NotFoundException(AppConstans.ERROR_404, AppConstans.NOT_FOUND));
	}

	private StudentInCourse existCourseAndStudent(Course course, Student student) {
		return repository.findByCourseAndStudent(course, student).orElse(null);
	}

}
