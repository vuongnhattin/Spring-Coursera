package com.tin.springcoursera.controller;

import com.tin.springcoursera.dto.request.CourseRequest;
import com.tin.springcoursera.dto.request.JoinCourseRequest;
import com.tin.springcoursera.dto.response.CourseResponse;
import com.tin.springcoursera.dto.response.ListResponse;
import com.tin.springcoursera.dto.response.PageResponse;
import com.tin.springcoursera.dto.response.UserCourseResponse;
import com.tin.springcoursera.entity.Course;
import com.tin.springcoursera.service.CourseService;
import com.tin.springcoursera.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final MemberService memberService;

    @GetMapping("me/courses")
    public PageResponse<CourseResponse> getCourses(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(defaultValue = "asc") String order,
            @RequestParam(defaultValue = "id") String sortBy,
            Principal principal) {
        return courseService.getCourses(search, page, size, order, sortBy, principal.getName());
    }

    @GetMapping("courses/{id}")
    @PreAuthorize("@auth.isMemberOfCourse(#id)")
    public Course getCourse(@PathVariable Integer id) {
        return courseService.getCourse(id);
    }

    @PostMapping("courses")
    @ResponseStatus(HttpStatus.CREATED)
    public Course createCourse(Principal principal, @Valid @RequestBody CourseRequest courseRequest) {
        Course course = courseService.createCourse(courseRequest);
        memberService.joinCourse(new JoinCourseRequest(course.getId()), principal.getName());
        memberService.setMemberToAdmin(principal.getName(), course.getId());
        return course;
    }

    @PutMapping("courses/{id}")
    @PreAuthorize("@auth.isAdminOfCourse(#id)")
    public Course updateCourse(@PathVariable Integer id, @Valid @RequestBody CourseRequest courseRequest) {
        return courseService.updateCourse(id, courseRequest);
    }

    @DeleteMapping("courses/{id}")
    @PreAuthorize("@auth.isAdminOfCourse(#id)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
    }

    @GetMapping("courses/{id}/members")
    @PreAuthorize("@auth.isAdminOfCourse(#id)")
    public ListResponse<UserCourseResponse> getMembers(@PathVariable int id) {
        return new ListResponse<>(memberService.getUserCourse(id));
    }
}
