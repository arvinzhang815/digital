package com.yingwu.digital.bean.resp.huobi;

import com.yingwu.digital.bean.dto.huobi.HuobiTrade;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/21
 **/
public class TradeResponse {
    /**
     * status : ok
     * ch : market.btcusdt.trade.detail
     * ts : 1489473346905
     * tick : {"id":600848670,"ts":1489464451000,"data":[{"id":600848670,"price":7962.62,"amount":0.0122,"direction":"buy","ts":1489464451000}]}
     */

    private String status;
    private String ch;
    private long ts;
    public String errCode;
    public String errMsg;
    private HuobiTrade tick;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

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

    public HuobiTrade getTick() {
        return tick;
    }

    public void setTick(HuobiTrade tick) {
        this.tick = tick;
    }

    @Override
    public String toString() {
        return "TradeResponse{" +
                "status='" + status + '\'' +
                ", ch='" + ch + '\'' +
                ", ts=" + ts +
                ", errCode='" + errCode + '\'' +
                ", errMsg='" + errMsg + '\'' +
                ", tick=" + tick +
                '}';
    }
}
