package com.tin.springcoursera.service;

import com.tin.springcoursera.dto.request.JoinCourseRequest;
import com.tin.springcoursera.entity.Member;
import com.tin.springcoursera.exception.ResourceNotFoundException;
import com.tin.springcoursera.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService {
    private static final String MEMBER_NOT_FOUND = "Thành viên không tồn tại";
    private final MemberRepository memberRepository;

    public boolean isMember(String userId, int courseId) {
        Optional<Member> member = memberRepository.findMemberByUserIdAndCourseId(userId, courseId);
        return member.isPresent();
    }

    public Member joinCourse(JoinCourseRequest request, String userId) {
        Member member = Member.builder()
                .userId(userId)
                .courseId(request.getCourseId())
                .admin(false)
                .build();

        return memberRepository.save(member);
    }

    public List<Member> getMembersByCourseId(int courseId) {
        return memberRepository.findMembersByCourseId(courseId);
    }

    public Member getMemberByUserIdAndCourseId(String userId, int courseId) {
        return memberRepository.findMemberByUserIdAndCourseId(userId, courseId).orElseThrow(() -> new ResourceNotFoundException(MEMBER_NOT_FOUND));
    }
}
