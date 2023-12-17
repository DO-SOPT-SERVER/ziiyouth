package com.server.sopt.seminar.service;

import com.server.sopt.seminar.domain.ServiceMember;
import com.server.sopt.seminar.repository.ServiceMemberRepository;
import com.server.sopt.seminar.request.member.ServiceMemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ServiceMemberService {

    private final ServiceMemberRepository serviceMemberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String create(ServiceMemberRequest request) {
        ServiceMember serviceMember = ServiceMember.builder()
                .nickname(request.nickname())
                .password(passwordEncoder.encode(request.password()))
                .build();
        serviceMemberRepository.save(serviceMember);

        return serviceMember.getId().toString();
    }

    public void signIn(ServiceMemberRequest request) {
        ServiceMember serviceMember = serviceMemberRepository.findByNickname(request.nickname())
                .orElseThrow(() -> new RuntimeException("해당하는 회원이 없습니다."));
        if (!passwordEncoder.matches(request.password(), serviceMember.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
    }

}