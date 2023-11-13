package com.server.sopt.seminar.domain;


import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryId implements Serializable {

    private Short categoryId;

    public CategoryId(Short id) {
        this.categoryId = id;
    }
}
