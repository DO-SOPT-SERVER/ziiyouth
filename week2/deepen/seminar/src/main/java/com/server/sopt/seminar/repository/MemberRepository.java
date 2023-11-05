package com.server.sopt.seminar.repository;

import com.server.sopt.seminar.domain.Member;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    default Member findByIdOrThrow(Long memberId){
        return findById(memberId)
                .orElseThrow(()-> new EntityNotFoundException("존재하는 회원이 없습니다."));
    }
}
