package com.tin.springcoursera.service;

import com.tin.springcoursera.dto.request.CourseRequest;
import com.tin.springcoursera.dto.response.CourseResponse;
import com.tin.springcoursera.dto.response.PageResponse;
import com.tin.springcoursera.entity.Course;
import com.tin.springcoursera.exception.AppException;
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
    private final MemberService memberService;

    public static final String COURSE_NOT_FOUND = "Khoá học không tồn tại";

    public PageResponse<CourseResponse> getCourses(String search, int page, int size, String order, String sortBy, String userId) {
        Pageable paging = PageRequest.of(page, size, Sort.Direction.fromString(order), sortBy);
        Page<Course> courses = courseRepository.getCourses(search, paging);
        Page<CourseResponse> responses = courses.map(course -> new CourseResponse(course, memberService.isMember(userId, course.getId()), memberService.isAdmin(userId, course.getId())));
        return PageResponse.from(responses);
    }

    public Course getCourseById(int courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new AppException(404, COURSE_NOT_FOUND));
    }

    public Course getCourse(Integer id) {
        return courseRepository.findById(id).orElseThrow(() -> new AppException(404, COURSE_NOT_FOUND));
    }

    @Transactional
    public Course createCourse(CourseRequest request) {
        Course course = Course.builder().
                name(request.getName()).
                description(request.getDescription()).
                price(request.getPrice()).
                build();
        return courseRepository.save(course);
    }

    @Transactional
    public Course updateCourse(Integer id, CourseRequest request) {
        if (!courseRepository.existsById(id)) {
            throw new AppException(404, COURSE_NOT_FOUND);
        }
        Course course = courseRepository.findById(id).orElseThrow(() -> new AppException(404, COURSE_NOT_FOUND));
        course.setName(request.getName());
        course.setDescription(request.getDescription());
        course.setIntroduction(request.getIntroduction());
        course.setPrice(request.getPrice());

        return courseRepository.save(course);
    }

    @Transactional
    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }
}
