package com.tin.springcoursera.service;

import com.tin.springcoursera.dto.request.JoinCourseRequest;
import com.tin.springcoursera.dto.response.UserCourseResponse;
import com.tin.springcoursera.entity.Member;
import com.tin.springcoursera.entity.Users;
import com.tin.springcoursera.exception.AppException;
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
    private final UserService userService;

    public boolean isMember(String userId, int courseId) {
        Optional<Member> member = memberRepository.findMemberByUsernameAndCourseId(userId, courseId);
        return member.isPresent();
    }

    public boolean isAdmin(String userId, int courseId) {
        Optional<Member> member = memberRepository.findMemberByUsernameAndCourseId(userId, courseId);
        return member.map(Member::isAdmin).orElse(false);
    }

    public Member joinCourse(JoinCourseRequest request, String username) {
        Member member = Member.builder()
                .username(username)
                .courseId(request.getCourseId())
                .admin(false)
                .build();

        return memberRepository.save(member);
    }

    public List<Member> getMembersByCourseId(int courseId) {
        return memberRepository.findMembersByCourseId(courseId);
    }

    public Member getMemberByUsernameAndCourseId(String username, int courseId) {
        return memberRepository.findMemberByUsernameAndCourseId(username, courseId).orElseThrow(() -> new AppException(404, MEMBER_NOT_FOUND));
    }

    public void setMemberToAdmin(String userId, int courseId) {
        Member member = getMemberByUsernameAndCourseId(userId, courseId);
        member.setAdmin(true);
        memberRepository.save(member);
    }

    public List<UserCourseResponse> getUserCourse(int courseId) {
        List<Member> members = getMembersByCourseId(courseId);
        Users currentUser = userService.getCurrentUser();
        return members.stream().map(member -> UserCourseResponse.builder()
                    .user(userService.findByUsername(member.getUsername()))
                    .admin(member.isAdmin())
                    .build())
                .filter(member -> !member.getUser().getUsername().equals(currentUser.getUsername()))
                .toList();
    }

    public Member changeRole(String username, int courseId) {
        Member member = getMemberByUsernameAndCourseId(username, courseId);
        member.setAdmin(!member.isAdmin());
        memberRepository.save(member);
        return member;
    }
}
