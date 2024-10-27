package com.tin.springcoursera.repository;

import com.tin.springcoursera.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findMemberByUsernameAndCourseId(String username, int courseId);
    List<Member> findMembersByCourseId(int courseId);
}
