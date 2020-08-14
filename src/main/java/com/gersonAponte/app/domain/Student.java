package com.gersonAponte.app.domain;
import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Student.
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "rut", nullable = false)
	private String rut;

	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull
	@Column(name = "lastname", nullable = false)
	private String lastname;

	@NotNull
	@Column(name = "age", nullable = false)
	private Integer age;

	@ManyToOne
	private Course course;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public Student rut(String rut) {
		this.rut = rut;
		return this;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getName() {
		return name;
	}

	public Student name(String name) {
		this.name = name;
		return this;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public Student lastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getAge() {
		return age;
	}

	public Student age(Integer age) {
		this.age = age;
		return this;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Course getCourse() {
		return course;
	}

	public Student course(Course course) {
		this.course = course;
		return this;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Student)) {
			return false;
		}
		return id != null && id.equals(((Student) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "Student{" + "id=" + getId() + ", rut='" + getRut() + "'" + ", name='" + getName() + "'" + ", lastname='"
				+ getLastname() + "'" + ", age=" + getAge() + "}";
	}
}
