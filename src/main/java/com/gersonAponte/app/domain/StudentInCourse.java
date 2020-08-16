package com.gersonAponte.app.domain;


import javax.persistence.*;


@Entity
@Table(name = "student_in_course")
public class StudentInCourse {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Course course;

	@ManyToOne
	private Student student;

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public StudentInCourse course(Course course) {
		this.course = course;
		return this;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public StudentInCourse student(Student student) {
		this.student = student;
		return this;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and
	// setters here, do not remove

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof StudentInCourse)) {
			return false;
		}
		return id != null && id.equals(((StudentInCourse) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "StudentInCourse{" + "id=" + getId() + ", registerDate='" + "'" + "}";
	}
}