package com.server.sopt.seminar.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {
    /**
     * 200 OK
     */
    OK(HttpStatus.OK, "OK");

    private final HttpStatus httpStatus;

    private final String status;

    public int getStatusCode() {
        return httpStatus.value();
    }
}