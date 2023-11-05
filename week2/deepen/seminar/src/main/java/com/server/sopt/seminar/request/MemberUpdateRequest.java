package com.server.sopt.seminar.request;

import com.server.sopt.seminar.domain.SOPT;

public record MemberUpdateRequest(
        String name,
        String nickname,
        int age,
        SOPT sopt
) {
}
