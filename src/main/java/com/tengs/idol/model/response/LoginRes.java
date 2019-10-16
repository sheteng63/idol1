package com.tengs.idol.model.response;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.tengs.idol.core.constant.BzConstant;
import lombok.Data;

@Data
public class LoginRes extends BaseResponse {
    private String token;
    public LoginRes() {
        setCode(BzConstant.QUERY_OK);
    }
}
