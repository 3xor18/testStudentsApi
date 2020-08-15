package com.gersonAponte.app.services.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gersonAponte.app.config.AppConstans;
import com.gersonAponte.app.domain.Course;
import com.gersonAponte.app.domain.Student;
import com.gersonAponte.app.exceptions.CourseException;
import com.gersonAponte.app.exceptions.GlobalAppException;
import com.gersonAponte.app.exceptions.InternalServerErrorException;
import com.gersonAponte.app.exceptions.NotFoundException;
import com.gersonAponte.app.exceptions.StudentException;
import com.gersonAponte.app.jsons.CourseRest;
import com.gersonAponte.app.jsons.StudentsRest;
import com.gersonAponte.app.repository.CourseRepository;
import com.gersonAponte.app.repository.StudentRepository;
import com.gersonAponte.app.services.StudentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	// Fin a Student
	public StudentsRest getStudent(Long studentId) throws GlobalAppException {
		return modelMapper.map(getStudentEntity(studentId), StudentsRest.class);
	}

	// Find All Courses
	public List<StudentsRest> getAllStudents() throws GlobalAppException {
		final List<Student> studentsEntity = studentRepository.findAll();
		return studentsEntity.stream().map(service -> modelMapper.map(service, StudentsRest.class))
				.collect(Collectors.toList());
	}

	// Create a Course
	public Student createStudent(StudentsRest studentRest) throws GlobalAppException {
		studentExistByRut(studentRest.getRut());
		return null;
	}

	@Override
	public StudentsRest createStudent(StudentsRest studentRest) throws GlobalAppException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteStudent(Long idStudent) throws GlobalAppException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentsRest editStudent(StudentsRest studentRest) throws GlobalAppException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<StudentsRest> findStudentPage(Pageable pageable) throws GlobalAppException {
		// TODO Auto-generated method stub
		return null;
	}

	/// Private Class

	// find and cast a student
	private Student getStudentEntity(Long idStudent) throws GlobalAppException {
		return studentRepository.findById(idStudent)
				.orElseThrow(() -> new NotFoundException(AppConstans.ERROR_404, AppConstans.COURSE_NOT_FOUND));
	}

	// Validate for a student does in a course
	private boolean studentExistByRut(String rutStudent) throws GlobalAppException {
		Student student = studentRepository.findByRut(rutStudent).orElse(null);
		if (student != null) {
			throw new StudentException(AppConstans.ERROR_400, AppConstans.COURSE_ALL_READY_EXIST_WITH_THIS_CODE);
		}
		return false;
	}

	// Validate and Cast a new Student
	private Student validateNewStudent(StudentsRest student) throws GlobalAppException {
		String rut = student.getRut();
		String name = student.getName();
		String lastName = student.getLastname();
		try {
			Integer age = student.getAge();
		} catch (Exception e) {
			throw new StudentException(AppConstans.ERROR_400, " FORMAT_ERROR_" + AppConstans.STUDENT_AGE);
		}
	}

}
