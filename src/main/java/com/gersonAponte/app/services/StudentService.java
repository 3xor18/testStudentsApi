package com.gersonAponte.app.services;

import org.springframework.stereotype.Service;

import com.gersonAponte.app.domain.Student;
import com.gersonAponte.app.exceptions.GlobalAppException;
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
	 * @param studentRest
	 * @return String Course Created
	 * @throws GlobalAppException
	 */
	Student createStudent(StudentsRest studentRest) throws GlobalAppException;
}
