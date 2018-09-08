package com.yingwu.digital.base;

import com.yingwu.digital.util.IdUtil;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/8
 **/
public class ApiRequest {
    private String sub;
    private String id = IdUtil.getInstance().longPK()+"";;
    private String req;
    private String from;
    private String to;
    private String symbol;
    private String type;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getId() {
        return id;
    }

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ApiRequest{" +
                "sub='" + sub + '\'' +
                ", id='" + id + '\'' +
                ", req='" + req + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
