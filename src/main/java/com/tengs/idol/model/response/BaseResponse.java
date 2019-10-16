package com.tengs.idol.model.response;

import com.tengs.idol.core.constant.BzConstant;
import lombok.Data;

@Data
public class BaseResponse {
    private String code;
    private String errmsg;
    private Object data;

    public BaseResponse() {
        this.code = BzConstant.QUERY_OK;
    }
}
