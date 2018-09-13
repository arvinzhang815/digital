package com.yingwu.digital.client.okex;


import com.yingwu.digital.base.DigitalApiException;
import com.yingwu.digital.base.DigitalWSClientOption;
import com.yingwu.digital.service.HuobiWSEventHandler;

public interface OKExApiWSClient {

    void setOption(DigitalWSClientOption option);

    DigitalWSClientOption getOption();

    void depth(String symbol, String type, OKExApiWSClient handler) throws DigitalApiException;

    void kline(String symbol, String period, OKExApiWSClient handler) throws DigitalApiException;

    void ticker(String symbol, OKExApiWSClient handler) throws DigitalApiException;

    void deals(String symbol, OKExApiWSClient handler) throws DigitalApiException;
}
