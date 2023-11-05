package com.server.sopt.seminar.request.member;

import com.server.sopt.seminar.domain.SOPT;
import lombok.Data;

public record MemberCreateRequest (
        String name,
        String nickname,
        int age,
        SOPT sopt
) {

}
