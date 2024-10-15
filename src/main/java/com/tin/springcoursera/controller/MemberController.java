package com.tin.springcoursera.controller;

import com.tin.springcoursera.dto.request.JoinCourseRequest;
import com.tin.springcoursera.entity.Member;
import com.tin.springcoursera.service.MemberService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("me/members/{courseId}")
    public Member getMember(Principal principal, @PathVariable int courseId) {
        return memberService.getMemberByUserIdAndCourseId(principal.getName(), courseId);
    }

    @PutMapping("members/role")
    @PreAuthorize("@auth.isAdminOfCourse(#courseId)")
    public Member updateRole(@RequestParam int courseId, @RequestParam String userId) {
        return memberService.changeRole(userId, courseId);
    }
}
