package com.gersonAponte.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gersonAponte.app.exceptions.GlobalAppException;
import com.gersonAponte.app.jsons.StudentInCourseRest;

@Service
public interface StudentInCourseService {

	public StudentInCourseRest getOne(Long id) throws GlobalAppException;

	public List<StudentInCourseRest> getAll() throws GlobalAppException;

	public StudentInCourseRest createOne(StudentInCourseRest studentInCourseRest) throws GlobalAppException;

	public String deleteById(Long id) throws GlobalAppException;

	public StudentInCourseRest edit(StudentInCourseRest studentInCourseRest) throws GlobalAppException;
}
