package com.server.sopt.seminar.request.member;

import com.server.sopt.seminar.domain.Part;

public record MemberSoptUpdateRequest(
        int generation,
        Part part
) {
}
