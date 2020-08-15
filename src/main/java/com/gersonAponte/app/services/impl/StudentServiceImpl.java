package com.gersonAponte.app.services.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import com.gersonAponte.app.domain.Course;
import com.gersonAponte.app.domain.Student;
import com.gersonAponte.app.exceptions.GlobalAppException;
import com.gersonAponte.app.exceptions.InternalServerErrorException;
import com.gersonAponte.app.exceptions.NotFoundException;
import com.gersonAponte.app.jsons.StudentsRest;
import com.gersonAponte.app.repository.CourseRepository;
import com.gersonAponte.app.repository.StudentRepository;
import com.gersonAponte.app.services.StudentService;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Student}.
 */
@Service
public class StudentServiceImpl implements StudentService {

	private final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

	private final StudentRepository studentRepository;
	private final CourseRepository courseRepository;

	public static final ModelMapper modelMapper = new ModelMapper();

	// Constructor
	public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
		this.studentRepository = studentRepository;
		this.courseRepository = courseRepository;
	}

	public StudentsRest getStudent(Long studentId) throws GlobalAppException {
		return modelMapper.map(getStudenEntity(studentId), StudentsRest.class);
	}

	public Student createStudent(StudentsRest studentRest) throws GlobalAppException {
		final Course courseId = courseRepository.findById(studentRest.getCourse().getId())
				.orElseThrow(() -> new NotFoundException("COURSE_NOT_FOUND", "COURSE_NOT_FOUND"));

		final Student student = new Student();
		student.setName(studentRest.getName());
		student.setLastname(studentRest.getLastname());
		student.setCourse(courseId);
		// penditne
		student.setAge(studentRest.getAge());
		student.setRut(studentRest.getRut());

		try {
			studentRepository.save(student);
		} catch (final Exception e) {
			log.error("INTERNAL_SERVER_ERROR", e);
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		return student;
	}

	/**
	 * @param studenId
	 * @return convert of json to entity Student
	 * @throws GlobalAppException
	 */
	private Student getStudenEntity(Long studentId) throws GlobalAppException {
		return studentRepository.findById(studentId)
				.orElseThrow(() -> new NotFoundException("SNOT-404-1", "STUDENT_NOT_FOUND"));
	}
}
