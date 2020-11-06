package com.xxxx.crm.exception;

public class NoCookieException extends Exception {
    private String msg;

    public NoCookieException() {
    }

    public NoCookieException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
