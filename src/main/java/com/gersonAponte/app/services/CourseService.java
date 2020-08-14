package com.gersonAponte.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gersonAponte.app.domain.Course;

/**
 * Service Interface for managing {@link Course}.
 */
public interface CourseService {

    /**
     * Save a course.
     *
     * @param course the entity to save.
     * @return the persisted entity.
     */
    Course save(Course course);

    /**
     * Get all the courses.
     *
     * @return the list of entities.
     */
    List<Course> findAll();


    /**
     * Get the "id" course.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Course> findOne(Long id);

    /**
     * Delete the "id" course.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
    
    /**
     * @param pageable
     * @return Page all Courses, paged by 5
     */
    Page<Course> findAllCoursesPage(Pageable pageable);
}
