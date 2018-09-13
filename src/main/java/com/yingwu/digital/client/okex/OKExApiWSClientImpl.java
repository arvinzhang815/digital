package com.yingwu.digital.client.okex;

import com.yingwu.digital.base.DigitalApiException;
import com.yingwu.digital.base.DigitalWSClientOption;
import com.yingwu.digital.bean.resp.okex.OKExDealsResponse;
import com.yingwu.digital.bean.resp.okex.OKExDepthResponse;
import com.yingwu.digital.bean.resp.okex.OKExKLineResponse;
import com.yingwu.digital.bean.resp.okex.OKExTickerResponse;
import com.yingwu.digital.service.HuobiWSEventHandler;
import okhttp3.OkHttpClient;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OKExApiWSClientImpl implements OKExApiWSClient, Closeable {

    private final OkHttpClient client;

    private DigitalWSClientOption option;

    public OKExApiWSClientImpl() {
        this(null);
    }

    public OKExApiWSClientImpl(DigitalWSClientOption option) {
        this.option = option == null ? new DigitalWSClientOption() : option;
        final OkHttpClient.Builder build = new OkHttpClient.Builder();
        build.connectTimeout(this.option.getConnectTimeout(), TimeUnit.SECONDS);
        build.readTimeout(this.option.getReadTimeout(), TimeUnit.SECONDS);
        build.writeTimeout(this.option.getWriteTimeout(), TimeUnit.SECONDS);

        this.client = build.build();
        this.client.dispatcher().setMaxRequests(this.option.getMaxRequests());
        this.client.dispatcher().setMaxRequestsPerHost(this.option.getMaxRequestsPerHost());
    }

    public OkHttpClient getClient() {
        return client;
    }

    @Override
    public void setOption(DigitalWSClientOption option) {
        if (option == null) {
            throw new IllegalArgumentException();
        }
        this.option = option;
        this.client.dispatcher().setMaxRequests(this.option.getMaxRequests());
        this.client.dispatcher().setMaxRequestsPerHost(this.option.getMaxRequestsPerHost());
    }

    @Override
    public DigitalWSClientOption getOption() {
        return this.option;
    }

    @Override
    public void depth(String symbol, String type, OKExDepthResponse handler) throws DigitalApiException {
        try {
            AbsOKExApiWSClient client = new OKExApiWSDepthClient(this, handler, symbol, type);
            client.start();
        } catch (Exception e) {
            throw new DigitalApiException(e);
        }
    }

    @Override
    public void kline(String symbol, String period, OKExKLineResponse handler) throws DigitalApiException {

        try {
            AbsOKExApiWSClient client = new OKExApiWSKLineClient(this, handler, symbol, period);
            client.start();
        } catch (Exception e) {
            throw new DigitalApiException(e);
        }

    }

    @Override
    public void ticker(String symbol, OKExTickerResponse handler) throws DigitalApiException {
        try {
            AbsOKExApiWSClient client = new OKExWSTradeDetailClient(this, handler, symbol);
            client.start();
        } catch (Exception e) {
            throw new DigitalApiException(e);
        }
    }

    @Override
    public void deals(String symbol, OKExDealsResponse handler) throws DigitalApiException {
        try {
            new OKExApiWSMarketDetailClient(this, handler, symbol).start();
        } catch (Exception e) {
            throw new DigitalApiException(e);
        }
    }

    @Override
    public void close() throws IOException {
        this.client.dispatcher().executorService().shutdown();
    }
}
