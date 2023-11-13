package com.server.sopt.seminar.service;

import com.server.sopt.seminar.domain.Member;
import com.server.sopt.seminar.domain.SOPT;
import com.server.sopt.seminar.repository.MemberRepository;
import com.server.sopt.seminar.request.member.MemberCreateRequest;
import com.server.sopt.seminar.request.member.MemberSoptUpdateRequest;
import com.server.sopt.seminar.request.member.MemberUpdateRequest;
import com.server.sopt.seminar.response.member.MemberGetResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 특정 사용자 조회
     */
    public MemberGetResponse getMemberByIdV1(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        return MemberGetResponse.of(member);
    }

    public MemberGetResponse getMemberByIdV2(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow( () -> new EntityNotFoundException("해당하는 회원이 없습니다."));
        return MemberGetResponse.of(member);
    }

    public MemberGetResponse getMemberByIdV3(Long memberId) {
        return MemberGetResponse.of(memberRepository.findByIdOrThrow(memberId));
    }

    /**
     * 모든 사용자 조회
     */
    public List<MemberGetResponse> getMembers(){
        return memberRepository.findAll()
                .stream()
                .map(MemberGetResponse::of)
                .collect(Collectors.toList());
    }

    /**
     * 사용자 생성
     */
    @Transactional
    public String createMember(MemberCreateRequest request){
        Member member = Member.builder()
                .name(request.name())
                .nickname(request.nickname())
                .age(request.age())
                .sopt(request.sopt())
                .build();
        return memberRepository.save(member).getId().toString();
    }

    /**
     * 사용자 모든 정보 수정
     */
    @Transactional
    public void updateMember(Long memberId, MemberUpdateRequest request){
        Member member = memberRepository.findByIdOrThrow(memberId);
        member.updateMember(request.name(), request.nickname(), request.age(), request.sopt());
        memberRepository.save(member);
    }

    /**
     * 사용자 SOPT 정보 수정
     */
    @Transactional
    public void updateSopt(Long memberId, MemberSoptUpdateRequest request){
        Member member = memberRepository.findByIdOrThrow(memberId);
        member.updateSOPT(new SOPT(request.generation(), request.part()));
    }

    /**
     * 사용자 삭제
     */
    @Transactional
    public void deleteMember(Long memberId){
        Member member = memberRepository.findByIdOrThrow(memberId);
        memberRepository.delete(member);
    }

}
