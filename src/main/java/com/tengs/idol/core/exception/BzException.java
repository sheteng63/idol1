package com.tengs.idol.core.exception;

public class BzException extends Exception {
    private String code;
    private String msg;

    public BzException(String code, String msg) {
        this.code= code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
