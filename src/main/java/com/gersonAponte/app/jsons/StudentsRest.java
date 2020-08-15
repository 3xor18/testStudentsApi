package com.gersonAponte.app.jsons;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentsRest {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("rut")
	private String rut;

	@JsonProperty("name")
	private String name;

	@JsonProperty("lastname")
	private String lastname;

	@JsonProperty("age")
	private Integer age;

	@JsonProperty("course")
	private CourseRest course;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public CourseRest getCourse() {
		return course;
	}

	public void setCourse(CourseRest course) {
		this.course = course;
	}

}
