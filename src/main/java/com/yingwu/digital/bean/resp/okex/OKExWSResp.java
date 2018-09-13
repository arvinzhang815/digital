package com.yingwu.digital.bean.resp.okex;

public class OKExWSResp extends OKExBaseResponse{

    public String result;

    public String errorcode;

    public String data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "OKExWSResp{" +
                ", result='" + result + '\'' +
                ", errorcode='" + errorcode + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
