package com.gersonAponte.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gersonAponte.app.exceptions.GlobalAppException;
import com.gersonAponte.app.jsons.CourseRest;

@Service
public interface CourseService {

	/**
	 * @param courseId
	 * @return A Course find by id
	 * @throws GlobalAppException
	 */
	public CourseRest getCourseById(Long courseId) throws GlobalAppException;

	/**
	 * @return all Courses in BD
	 * @throws GlobalAppException
	 */
	public List<CourseRest> getCourses() throws GlobalAppException;

	/**
	 * @param courseRest
	 * @return String: Course created
	 * @throws GlobalAppException
	 */
	public CourseRest createCourse(CourseRest courseRest) throws GlobalAppException;
	
	/**
	 * @param id of Course
	 * @return 
	 * @throws GlobalAppException
	 */
	public String  deleteCourse(Long id)throws GlobalAppException;
	
	/**
	 * @param courseRest
	 * @return a edit course
	 * @throws GlobalAppException
	 */
	public CourseRest editCourse(CourseRest courseRest) throws GlobalAppException;
}
