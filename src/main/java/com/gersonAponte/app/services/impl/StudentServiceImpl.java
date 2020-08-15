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
import com.gersonAponte.app.services.CourseService;
import com.gersonAponte.app.services.StudentService;
import com.gersonAponte.app.utils.UtilsApp;

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
	private final CourseServiceImpl courseService;

	public static final ModelMapper modelMapper = new ModelMapper();

	// Constructor
	public StudentServiceImpl(StudentRepository studentRepository, CourseServiceImpl courseService) {
		this.studentRepository = studentRepository;
		this.courseService = courseService;
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

	// Save a course
	@Override
	public StudentsRest createStudent(StudentsRest studentRest) throws GlobalAppException {
		Student studenValidated = validateNewStudent(studentRest);
		Student newStudent = studentRepository.save(studenValidated);
		return modelMapper.map(newStudent, StudentsRest.class);
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
		String rut = validateNotNullAndLenght(AppConstans.STUDENT_RUT, student.getRut(), AppConstans.MIN_LENGHT_RUT,
				AppConstans.MAX_LENGHT_RUT);
		String name = validateNotNullAndLenght(AppConstans.STUDENT_NAME, student.getName(), AppConstans.MIN_LENGHT_NAME,
				AppConstans.MAX_LENGHT_NAME);
		String lastName = validateNotNullAndLenght(AppConstans.STUDENT_LAST_NAME, student.getLastname(),
				AppConstans.MIN_LENGHT_LAST_NAME, AppConstans.MAX_LENGHT_LAST_NAME);
		Integer age = null;
		try {
			age = student.getAge();
			validateMinMaxLenght(AppConstans.STUDENT_AGE, age.toString(), AppConstans.MIN_LENGHT_AGE,
					AppConstans.MAX_LENGHT_AGE);
			if (age < 18) {
				throw new StudentException(AppConstans.ERROR_400, AppConstans.STUDENT_AGE + "_MUST_BE_>_18");
			}
		} catch (Exception e) {
			throw new StudentException(AppConstans.ERROR_400, " FORMAT_ERROR_" + AppConstans.STUDENT_AGE);
		}
		Course course = validateCourseNotNullAndExist(student.getCourse());

		Student studentOut = new Student();
		studentOut.setAge(age);
		studentOut.rut(UtilsApp.validarRut(rut));
		studentOut.name(name);
		studentOut.lastname(lastName);
		studentOut.setCourse(course);
		return studentOut;
	}

	// Validate a Course is Not null and exist
	private Course validateCourseNotNullAndExist(CourseRest course) throws GlobalAppException {
		if (course == null) {
			throw new CourseException(AppConstans.ERROR_400, "COURSE_REQUIRED");
		}
		return courseService.getCourseEntity(course.getId());
	}

	// validate not null & min_max lenght of value
	private String validateNotNullAndLenght(String nameVar, String valueVar, int minLeght, int maxLeght)
			throws GlobalAppException {
		if (valueVar == null) {
			throw new StudentException(AppConstans.ERROR_400, nameVar + "_IS_REQUIRED");
		}
		return validateMinMaxLenght(nameVar, valueVar, minLeght, maxLeght);
	}

	// validate min and max lenght
	private String validateMinMaxLenght(String nameVar, String valueVar, int minLenght, int maxLength)
			throws GlobalAppException {
		if (valueVar.length() < minLenght) {
			throw new StudentException(AppConstans.ERROR_400, nameVar + "_Must_Be_" + minLenght);
		}
		if (valueVar.length() < maxLength) {
			throw new StudentException(AppConstans.ERROR_400, nameVar + "_Must_Be_" + maxLength);
		}
		return valueVar;
	}

}
