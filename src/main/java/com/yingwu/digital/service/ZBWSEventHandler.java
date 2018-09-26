package com.yingwu.digital.service;


import com.yingwu.digital.bean.resp.okex.OKExDealsResponse;
import com.yingwu.digital.bean.resp.okex.OKExDepthResponse;
import com.yingwu.digital.bean.resp.okex.OKExKLineResponse;
import com.yingwu.digital.bean.resp.okex.OKExTickerResponse;
import com.yingwu.digital.bean.ws.HuobiWSError;

public abstract class ZBWSEventHandler {

//    public void handleDepth(OKExDepthResponse event){}

    public void handleKLine(OKExKLineResponse event){}

//    public void handleTicker(OKExTickerResponse event){}
//
//    public void handleDeals(OKExDealsResponse event){}

    public void onClosed(int code, String reason){}

    public void onFailure(String msg){}

    public void onError(HuobiWSError error){}
}
