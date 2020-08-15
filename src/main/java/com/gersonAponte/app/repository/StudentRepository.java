package com.gersonAponte.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gersonAponte.app.domain.Course;
import com.gersonAponte.app.domain.Student;

/**
 * Spring Data repository for the Student entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query("select e from Student e where e.rut=?1")
	Optional<Student> findByRut(String rutStudent);

}
