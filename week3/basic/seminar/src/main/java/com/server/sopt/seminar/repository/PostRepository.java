package com.server.sopt.seminar.repository;

import com.server.sopt.seminar.domain.Member;
import com.server.sopt.seminar.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByMemberId(Long memberId);
    List<Post> findAllByMember(Member member);

}
