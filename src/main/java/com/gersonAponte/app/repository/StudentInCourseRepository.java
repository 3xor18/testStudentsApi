package com.gersonAponte.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gersonAponte.app.domain.Course;
import com.gersonAponte.app.domain.Student;
import com.gersonAponte.app.domain.StudentInCourse;

@SuppressWarnings("unused")
@Repository
public interface StudentInCourseRepository extends JpaRepository<StudentInCourse, Long> {

	@Query("select s from StudentInCourse s where s.course=?1")
	Optional<StudentInCourse> findByCourse(Course curse);

	@Query("select s from StudentInCourse s where s.student=?1")
	Optional<StudentInCourse> findByStudent(Student student);

	@Query("select s from StudentInCourse s where s.course=?1 AND s.student=?2")
	Optional<StudentInCourse> findByCourseAndStudent(Course course, Student student);
}
