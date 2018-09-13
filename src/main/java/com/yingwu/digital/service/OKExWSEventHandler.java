package com.yingwu.digital.service;


import com.yingwu.digital.bean.resp.okex.OKExDealsData;
import com.yingwu.digital.bean.resp.okex.OKExDepthResponse;
import com.yingwu.digital.bean.resp.okex.OKExKLineResponse;
import com.yingwu.digital.bean.resp.okex.OKExTickerResponse;
import com.yingwu.digital.bean.ws.*;

public abstract class OKExWSEventHandler {

    public void handleDepth(OKExDepthResponse event){}

    public void handleKLine(OKExKLineResponse event){}

    public void handleTicker(OKExTickerResponse event){}

    public void handleDeals(OKExDealsData event){}

    public void onClosed(int code, String reason){}

    public void onFailure(String msg){}

    public void onError(HuobiWSError error){}
}
