package com.yingwu.digital.domain.resp;


import com.google.gson.annotations.SerializedName;
import com.yingwu.digital.domain.HuobiOrderMatchResult;

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
