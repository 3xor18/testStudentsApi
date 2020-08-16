package com.gersonAponte.app.jsons;

import com.fasterxml.jackson.annotation.JsonInclude;

import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentInCourseRest {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("course")
	private CourseRest course;

	@JsonProperty("student")
	private StudentsRest student;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CourseRest getCourse() {
		return course;
	}

	public void setCourse(CourseRest course) {
		this.course = course;
	}

	public StudentsRest getStudent() {
		return student;
	}

	public void setStudent(StudentsRest student) {
		this.student = student;
	}

}
