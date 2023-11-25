package com.server.sopt.seminar.repository;

import com.server.sopt.seminar.domain.Member;
import com.server.sopt.seminar.domain.Post;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByMemberId(Long memberId);
//    List<Post> findAllByMember(Member member);

    default Post findByIdOrThrow(Long postId){
        return findById(postId)
                .orElseThrow(()-> new EntityNotFoundException("존재하는 게시글이 없습니다."));
    }

}
