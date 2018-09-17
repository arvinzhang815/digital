package com.yingwu.digital.client.huobi;

import com.yingwu.digital.base.DigitalException;
import com.yingwu.digital.base.DigitalWSClientOption;
import com.yingwu.digital.service.HuobiWSEventHandler;
import okhttp3.OkHttpClient;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HuobiApiWSClientImpl implements HuobiApiWSClient, Closeable {

    private final OkHttpClient client;

    private DigitalWSClientOption option;

    public HuobiApiWSClientImpl() {
        this(null);
    }

    public HuobiApiWSClientImpl(DigitalWSClientOption option) {
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
    public void depth(String symbol, String type, HuobiWSEventHandler handler) throws DigitalException {
        try {
            AbsHuobiApiWSClient client = new HuobiApiWSDepthClient(this, handler, symbol, type);
            client.start();
        } catch (Exception e) {
            throw new DigitalException(e);
        }
    }

    @Override
    public void kline(String symbol, String period, HuobiWSEventHandler handler) throws DigitalException {

        try {
            AbsHuobiApiWSClient client = new HuobiApiWSKLineClient(this, handler, symbol, period);
            client.start();
        } catch (Exception e) {
            throw new DigitalException(e);
        }

    }

    @Override
    public void tradeDetail(String symbol, HuobiWSEventHandler handler) throws DigitalException {
        try {
            AbsHuobiApiWSClient client = new HuobiApiWSTradeDetailClient(this, handler, symbol);
            client.start();
        } catch (Exception e) {
            throw new DigitalException(e);
        }
    }

    @Override
    public void marketDetail(String symbol, HuobiWSEventHandler handler) throws DigitalException {
        try {
            new HuobiApiWSMarketDetailClient(this, handler, symbol).start();
        } catch (Exception e) {
            throw new DigitalException(e);
        }
    }

    @Override
    public void close() throws IOException {
        this.client.dispatcher().executorService().shutdown();
    }
}
