package com.tin.springcoursera.controller;

import com.tin.springcoursera.dto.request.CourseRequest;
import com.tin.springcoursera.dto.response.PageResponse;
import com.tin.springcoursera.entity.Course;
import com.tin.springcoursera.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("courses")
    public PageResponse<Course> getCourses(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "id") String sortBy) {
        return courseService.getCourses(search, page, size, order, sortBy);
    }

    @GetMapping("courses/{id}")
    public Course getCourse(@PathVariable Integer id) {
        return courseService.getCourse(id);
    }

    @PostMapping("courses")
    public Course createCourse(@Valid @RequestBody CourseRequest courseRequest) {
        return courseService.createCourse(courseRequest);
    }

    @PutMapping("courses/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Course updateCourse(@PathVariable Integer id, @Valid @RequestBody CourseRequest courseRequest) {
        return courseService.updateCourse(id, courseRequest);
    }

    @DeleteMapping("courses/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
    }
}
