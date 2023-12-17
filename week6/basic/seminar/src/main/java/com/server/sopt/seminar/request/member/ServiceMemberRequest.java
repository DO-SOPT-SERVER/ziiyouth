package com.server.sopt.seminar.request.member;

public record ServiceMemberRequest(
        String nickname,
        String password
) {
}