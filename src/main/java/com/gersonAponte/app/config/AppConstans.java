package com.gersonAponte.app.config;

public final class AppConstans {

	public static final int MIN_LENGTH_RUT=7;
	
	// Courses
	public static final int MAX_LENG_NAME = 250;
	public static final int MAX_LENG_CODE = 4;
	public static final String COURSE_NAME = "COURSE_NAME";
	public static final String COURSE_CODE = "COURSE_CODE";
	public static final String COURSE_ALL_READY_EXIST_WITH_THIS_CODE = "COURSE_ALL_READY_EXIST_WITH_THIS_CODE";
	public static final String COURSE_NOT_FOUND = "COURSE_NOT_FOUND";
	public static final String ID_COURSE_REQUIRED = "ID_COURSE_REQUIRED";
	public static final String COURSE_DELETED = "COURSE_DELETED";
	public static final String COURSE_CREATED = "COURSE_CREATED";

	// Students
	public static final int MAX_LENGHT_RUT = 12;
	public static final int MIN_LENGHT_RUT = 6;
	public static final int MAX_LENGHT_NAME = 250;
	public static final int MIN_LENGHT_NAME = 2;
	public static final int MAX_LENGHT_LAST_NAME = 12;
	public static final int MIN_LENGHT_LAST_NAME = 2;
	public static final int MAX_LENGHT_AGE = 3;
	public static final int MIN_LENGHT_AGE = 1;
	public static final String STUDENT_NAME = "STUDENT_NAME";
	public static final String STUDENT_RUT = "STUDENT_RUT";
	public static final String STUDENT_LAST_NAME = "STUDENT_LAST_NAME";
	public static final String STUDENT_AGE = "STUDENT_AGE";
	public static final String STUDENT_COURSE = "STUDENT_COURSE";
	public static final String STUDENT_ALL_READY_EXIST_WITH_THIS_RUT = "STUDENT_ALL_READY_EXIST_WITH_THIS_RUT";
	public static final String STUDENT_NOT_FOUND = "STUDENT_NOT_FOUND";

	// Student In Course
	public static final String NOT_FOUND = "NOT_FOUND";
	public static final String COURSE_ALL_READY_EXIST = "COURSE_ALL_READY_EXIST";
	public static final String STUDENT_ALL_READY_EXIST = "STUDENT_ALL_READY_EXIST";
	public static final String COURSE_REQUIRED = "COURSE_REQUIRED";
	public static final String STUDENT_REQUIRED = "STUDENT_REQUIRED";
	public static final String DELETE_SUCCES="DELETE_SUCCES";
	

	// Status
	public static final String ERROR_400 = "400";
	public static final String ERROR_404 = "404";
	public static final String ERROR_500 = "500";
	public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
	public static final int PAGINADOBY = 5;

	public AppConstans() {
	};

}
