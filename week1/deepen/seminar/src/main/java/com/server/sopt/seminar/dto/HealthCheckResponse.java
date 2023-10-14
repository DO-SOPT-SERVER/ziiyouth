package com.server.sopt.seminar.dto;

public class HealthCheckResponse {

    private static final String STATUS = "OK";
    private String status;

    public HealthCheckResponse() {
        this.status = STATUS;
    }

}
