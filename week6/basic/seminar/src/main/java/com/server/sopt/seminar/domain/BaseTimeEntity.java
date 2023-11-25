package com.server.sopt.seminar.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AbstractMethodError.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createAt; // 생성 시각

    @LastModifiedDate
    private LocalDateTime updatedAt; // 수정 시각

    // 날짜를 표현할 때는 ~~At / ~~Date
}
