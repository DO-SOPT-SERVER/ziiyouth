package com.server.sopt.seminar.controller;

import com.server.sopt.seminar.request.MemberCreateRequest;
import com.server.sopt.seminar.response.MemberGetResponse;
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

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberGetResponse> getMemberProfileV1(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok(memberService.getMemberByIdV1(memberId));
    }

    @GetMapping(value = "/{memberId}/v2", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberGetResponse> getMemberProfileV2(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getMemberByIdV2(memberId));
    }

    @GetMapping
    public ResponseEntity<List<MemberGetResponse>> getMembersProfile(){
        return ResponseEntity.ok(memberService.getMembers());
    }

    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody MemberCreateRequest request){
        URI location = URI.create("api/member/" + memberService.create(request));
        return ResponseEntity.created(location).build();
    }

}
