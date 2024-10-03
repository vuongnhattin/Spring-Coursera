package com.tin.springcoursera.controller;

import com.tin.springcoursera.dto.request.JoinCourseRequest;
import com.tin.springcoursera.entity.Member;
import com.tin.springcoursera.service.MemberService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("api")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("me/courses")
    public Member joinCourse(@RequestBody @Valid JoinCourseRequest request, Principal principal) {
        return memberService.joinCourse(request, principal.getName());
    }
}