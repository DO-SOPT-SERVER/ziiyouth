package com.server.sopt.seminar.controller;

import com.server.sopt.seminar.request.member.MemberCreateRequest;
import com.server.sopt.seminar.request.member.MemberSoptUpdateRequest;
import com.server.sopt.seminar.request.member.MemberUpdateRequest;
import com.server.sopt.seminar.response.member.MemberGetResponse;
import com.server.sopt.seminar.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 특정 사용자 조회 API
     */
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberGetResponse> getMemberProfileV1(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok(memberService.getMemberByIdV1(memberId));
    }

    @GetMapping(value = "/{memberId}/v2", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberGetResponse> getMemberProfileV2(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getMemberByIdV2(memberId));
    }

    @GetMapping(value = "/{memberId}/v3", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberGetResponse> getMemberProfileV3(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getMemberByIdV3(memberId));
    }

    /**
     * 모든 사용자 조회 API
     */
    @GetMapping
    public ResponseEntity<List<MemberGetResponse>> getMembersProfile(){
        return ResponseEntity.ok(memberService.getMembers());
    }

    /**
     * 사용자 생성 API
     */
    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody MemberCreateRequest request){
        URI location = URI.create("api/member/" + memberService.createMember(request));
        return ResponseEntity.created(location).build();
    }

    /**
     * 사용자 모든 정보 수정 API
     */
    @PatchMapping("/{memberId}")
    public ResponseEntity<Void> updateMember(@PathVariable Long memberId, @RequestBody MemberUpdateRequest request){
        memberService.updateMember(memberId, request);
        return ResponseEntity.noContent().build();
    }

    /**
     * 사용자 SOPT 정보 수정 API
     */
    @PatchMapping("/{memberId}/v2")
    public ResponseEntity<Void> updateMemberSopt(@PathVariable Long memberId, @RequestBody MemberSoptUpdateRequest request){
        memberService.updateSopt(memberId, request);
        return ResponseEntity.noContent().build();
    }


    /**
     * 사용자 삭제 API
     */
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId){
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

}
