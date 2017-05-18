package com.tianma.model.exception;

/**
 * Created by zuowenxia on 2017/4/1.
 */
public class Error {
    int retCode;
    String msg;

    public Error(int retCode, String msg) {
        this.retCode = retCode;
        this.msg = msg;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Error{" +
                "retCode=" + retCode +
                ", msg='" + msg + '\'' +
                '}';
    }
}
