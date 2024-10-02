package com.tin.springcoursera.service;

import com.tin.springcoursera.dto.request.JoinCourseRequest;
import com.tin.springcoursera.entity.Member;
import com.tin.springcoursera.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public boolean isMember(String userId, int courseId) {
        Optional<Member> member = memberRepository.findMemberByUserIdAndCourseId(userId, courseId);
        return member.isPresent();
    }

    public Member joinCourse(JoinCourseRequest request, String userId) {
        Member member = Member.builder()
                .userId(userId)
                .courseId(request.getCourseId())
                .build();

        return memberRepository.save(member);
    }

    public List<Member> getMembersByCourseId(int courseId) {
        return memberRepository.findMembersByCourseId(courseId);
    }
}
