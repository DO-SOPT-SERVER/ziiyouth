package com.server.sopt.seminar.controller;

import com.server.sopt.seminar.dto.BaseResponse;
import com.server.sopt.seminar.dto.HealthCheckResponse;
import com.server.sopt.seminar.dto.SuccessCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthCheckController {

    @GetMapping("/v1")
    public Map<String, String> healthCheck(){
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        return response;
    }

    @GetMapping("/v2")
    public ResponseEntity<String> healthCheckV2(){
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/v3")
    public String healthCheckV3() {
        return "OK";
    }

    @GetMapping("/v4")
    public ResponseEntity<Map<String, String>> healthCheckV4(){
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/v5")
    public ResponseEntity<HealthCheckResponse> healthCheckV5(){
        return ResponseEntity.ok(new HealthCheckResponse());
    }

    @GetMapping("/v6")
    public BaseResponse healthCheckV6(){
        return BaseResponse.success();
    }

    @GetMapping("/v7")
    public ResponseEntity<BaseResponse> healthCheckV7(){
        return ResponseEntity.ok(BaseResponse.success());
    }

    @GetMapping("/v8")
    public ResponseEntity<BaseResponse> healthCheckV8() {
        return ResponseEntity.status(SuccessCode.OK.getStatusCode())
                .body(BaseResponse.success());
    }
}

