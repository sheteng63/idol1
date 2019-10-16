package com.tengs.idol.model.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String code;
    private String anickName;
    private String avatarUrl;
}
