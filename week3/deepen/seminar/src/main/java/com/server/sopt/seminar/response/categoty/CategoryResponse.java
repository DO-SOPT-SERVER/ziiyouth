package com.server.sopt.seminar.response.categoty;

import com.server.sopt.seminar.domain.Category;

public record CategoryResponse(
        String name
) {
    public static CategoryResponse of(Category category) {
        return new CategoryResponse(category.getContent());
    }
}
