package com.tin.springcoursera.service;

import com.tin.springcoursera.dto.request.CourseRequest;
import com.tin.springcoursera.dto.response.PageResponse;
import com.tin.springcoursera.entity.Course;
import com.tin.springcoursera.exception.ResourceNotFoundException;
import com.tin.springcoursera.repository.CourseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public static final String COURSE_NOT_FOUND = "Khoá học không tồn tại";

    public PageResponse<Course> getCourses(String search, int page, int size, String order, String sortBy) {
        Pageable paging = PageRequest.of(page, size, Sort.Direction.fromString(order), sortBy);
        Page<Course> courses = courseRepository.getCourses(search, paging);
        return PageResponse.from(courses);
    }

    public Course getCourse(Integer id) {
        return courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(COURSE_NOT_FOUND));
    }

    @Transactional
    public Course createCourse(CourseRequest request) {
        Course course = Course.builder().
                name(request.getName()).
                description(request.getDescription()).
                build();
        return courseRepository.save(course);
    }

    @Transactional
    public Course updateCourse(Integer id, CourseRequest request) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException(COURSE_NOT_FOUND);
        }
        Course course = courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(COURSE_NOT_FOUND));
        course.setName(request.getName());
        course.setDescription(request.getDescription());

        return courseRepository.save(course);
    }

    @Transactional
    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }
}
