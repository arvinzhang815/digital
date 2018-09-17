package com.yingwu.digital.client.huobi;


import com.yingwu.digital.base.DigitalException;
import com.yingwu.digital.base.DigitalWSClientOption;
import com.yingwu.digital.service.HuobiWSEventHandler;

public interface HuobiApiWSClient {

    void setOption(DigitalWSClientOption option);

    DigitalWSClientOption getOption();

    void depth(String symbol, String type, HuobiWSEventHandler handler) throws DigitalException;

    void kline(String symbol, String period, HuobiWSEventHandler handler) throws DigitalException;

    void tradeDetail(String symbol, HuobiWSEventHandler handler) throws DigitalException;

    void marketDetail(String symbol, HuobiWSEventHandler handler) throws DigitalException;
}
