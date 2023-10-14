package com.server.sopt.seminar.dto;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BaseResponse<T> {

    private final int code;

    private final String status;

    private final boolean success;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    // 데이터 반환하지 않을 때
    public static BaseResponse success() {
        return new BaseResponse<>(SuccessCode.OK.getStatusCode(), SuccessCode.OK.getStatus(), true);
    }

    // 데이터 반환할 때
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(SuccessCode.OK.getStatusCode(), SuccessCode.OK.getStatus(), true, data);
    }

}
