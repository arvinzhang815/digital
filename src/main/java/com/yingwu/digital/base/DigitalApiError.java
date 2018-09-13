package com.yingwu.digital.base;

public class DigitalApiError {

    private String errCode;

    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public DigitalApiError() {
    }

    @Override
    public String toString() {
        return String.format("(%s,%s)",errCode, errMsg);
    }
}
