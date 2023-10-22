package com.server.sopt.seminar.repository;

import com.server.sopt.seminar.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

//    default Member findById
}
