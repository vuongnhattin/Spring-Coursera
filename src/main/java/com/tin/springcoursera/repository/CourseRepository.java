package com.tin.springcoursera.repository;

import com.tin.springcoursera.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query("""
            select c from Course c
            where :search is null
            or :search = ''
            or c.name like %:search%
            or c.description like %:search%
            """)
    Page<Course> getCourses(@Param("search") String search, Pageable pageable);


}
