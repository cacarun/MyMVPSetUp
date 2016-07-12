package com.android.mvp2.exception;

public class APIException extends Exception {

    private int code;

    // 用于展示的异常信息
    private String displayMessage;

    public APIException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;

    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public int getCode() {
        return code;
    }
}
