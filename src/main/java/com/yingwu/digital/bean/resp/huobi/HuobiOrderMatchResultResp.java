package com.yingwu.digital.bean.resp.huobi;


import com.google.gson.annotations.SerializedName;
import com.yingwu.digital.bean.HuobiOrderMatchResult;

public class HuobiOrderMatchResultResp extends HuobiResp {

    @SerializedName("data")
    private HuobiOrderMatchResult matchResult;

    public HuobiOrderMatchResult getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(HuobiOrderMatchResult matchResult) {
        this.matchResult = matchResult;
    }

    public HuobiOrderMatchResultResp() {
    }
}
