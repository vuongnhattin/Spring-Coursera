package com.tin.springcoursera.controller;

import com.tin.springcoursera.dto.request.CourseRequest;
import com.tin.springcoursera.dto.request.JoinCourseRequest;
import com.tin.springcoursera.dto.response.CourseResponse;
import com.tin.springcoursera.dto.response.PageResponse;
import com.tin.springcoursera.entity.ChatMessage;
import com.tin.springcoursera.entity.Course;
import com.tin.springcoursera.repository.ChatMessageRepository;
import com.tin.springcoursera.service.CourseService;
import com.tin.springcoursera.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final ChatMessageRepository chatMessageRepository;
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
    public Course updateCourse(@PathVariable Integer id, @Valid @RequestBody CourseRequest courseRequest) {
        return courseService.updateCourse(id, courseRequest);
    }

    @DeleteMapping("courses/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
    }

    @GetMapping("test")
    public List<ChatMessage> getChatMessages() {
        return chatMessageRepository.findAll();
    }
}
