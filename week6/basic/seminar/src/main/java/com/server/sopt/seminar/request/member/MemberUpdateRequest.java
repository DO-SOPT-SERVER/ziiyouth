package com.server.sopt.seminar.request.member;

import com.server.sopt.seminar.domain.SOPT;

public record MemberUpdateRequest(
        String name,
        String nickname,
        int age,
        SOPT sopt
) {
}
