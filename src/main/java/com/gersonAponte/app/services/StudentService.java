package com.gersonAponte.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gersonAponte.app.domain.Student;
import com.gersonAponte.app.exceptions.GlobalAppException;
import com.gersonAponte.app.jsons.CourseRest;
import com.gersonAponte.app.jsons.StudentsRest;

@Service
public interface StudentService {

	/**
	 * @param studentId
	 * @return Student find by id
	 * @throws GlobalAppException
	 */
	StudentsRest getStudent(Long studentId) throws GlobalAppException;

	/**
	 * @return List of student in BD
	 * @throws GlobalAppException
	 */
	List<StudentsRest> getAllStudents() throws GlobalAppException;;

	/**
	 * @param studentRest
	 * @return a Created Student
	 * @throws GlobalAppException
	 */
	StudentsRest createStudent(StudentsRest studentRest) throws GlobalAppException;

	/**
	 * @param idStudent
	 * @return STUDENT_DELETED
	 * @throws GlobalAppException
	 */
	public String deleteStudent(Long idStudent) throws GlobalAppException;

	/**
	 * @param studentRest
	 * @return a Edit Student
	 * @throws GlobalAppException
	 */
	public StudentsRest editStudent(StudentsRest studentRest) throws GlobalAppException;

}
