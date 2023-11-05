package com.server.sopt.seminar.service;

import com.server.sopt.seminar.domain.Member;
import com.server.sopt.seminar.repository.MemberRepository;
import com.server.sopt.seminar.request.MemberCreateRequest;
import com.server.sopt.seminar.response.MemberGetResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberGetResponse getMemberByIdV1(Long id) {
        Member member = memberRepository.findById(id).get();
        return MemberGetResponse.of(member);
    }

    public MemberGetResponse getMemberByIdV2(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("해당하는 회원이 없습니다."));
//        Member member = memberRepository.findIdOrThrow(id);
        return MemberGetResponse.of(member);
    }

    public List<MemberGetResponse> getMembers(){
        return memberRepository.findAll()
                .stream()
                .map(MemberGetResponse::of)
//                .map(member -> MemberGetResponse.of(member))
                .collect(Collectors.toList());
    }

//    @Transactional
//    public String create(MemberCreateRequest request){
//        Member member = Member.builder()
//                .name(request.getName())
//                .nickname(request.getNickname())
//                .age(request.getAge())
//                .sopt(request.getSopt())
//                .build();
//        return memberRepository.save(member).getId().toString();
//    }

    @Transactional
    public String create(MemberCreateRequest request){
        Member member = Member.builder()
                .name(request.name())
                .nickname(request.nickname())
                .age(request.age())
                .sopt(request.sopt())
                .build();
        return memberRepository.save(member).getId().toString();
    }

    // private 아래로 빼는 게 좋음
    private Member findById(Long memberId){
        return memberRepository.findById(memberId)
                .orElseThrow( () -> new EntityNotFoundException("해당하는 회원이 없습니다."));
    }

}
