package com.server.sopt.seminar.repository;

import com.server.sopt.seminar.domain.ServiceMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceMemberRepository extends JpaRepository<ServiceMember, Long> {
    Optional<ServiceMember> findByNickname(String nickanme);
}
