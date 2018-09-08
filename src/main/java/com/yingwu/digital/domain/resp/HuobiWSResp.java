package com.yingwu.digital.domain.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

public class HuobiWSResp {

    public static final String STATUES_OK = "ok";

    public String ch;

    public String ts;

    public String id;

    public String status;

    public String subbed;

    @SerializedName("err-code")
    @JsonProperty("err-code")
    public String errCode;


    @SerializedName("err-msg")
    @JsonProperty("err-msg")
    public String errMsg;

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }
}
