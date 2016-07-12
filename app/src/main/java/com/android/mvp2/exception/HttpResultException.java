package com.android.mvp2.exception;

public class HttpResultException extends RuntimeException {
    private int code;
    private String msg;

    public HttpResultException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
