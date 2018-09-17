package com.yingwu.digital.client.okex;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yingwu.digital.base.DigitalConst;
import com.yingwu.digital.bean.resp.okex.OKExBaseResponse;
import com.yingwu.digital.bean.resp.okex.OKExWSSub;
import com.yingwu.digital.bean.ws.HuobiWSError;
import com.yingwu.digital.service.OKExWSEventHandler;
import com.yingwu.digital.util.ApiUtil;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import org.apache.commons.lang3.StringUtils;

import java.io.Closeable;
import java.io.IOException;

public abstract class AbsOKExApiWSClient<T extends OKExBaseResponse> extends WebSocketListener implements Closeable {

    private ObjectMapper objectMapper = new ObjectMapper();

    protected final OKExApiWSClientImpl client;

    protected final OKExWSEventHandler handler;

    protected final Class<T> clazz;

    protected WebSocket webSocket;


    public AbsOKExApiWSClient(final OKExApiWSClientImpl client, final OKExWSEventHandler handler, final Class<T> clazz) {
        this.client = client;
        this.handler = handler;
        this.clazz = clazz;
    }

    public void start() {
        Request.Builder builder = new Request.Builder().url(DigitalConst.OKEX_WS_URL);
        this.webSocket = client.getClient().newWebSocket(builder.build(), this);
    }

    public void shutdown() {
        this.webSocket.close(0, "manual");
    }

    protected abstract OKExWSSub calcSub();

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        System.out.println(String.format("%s onOpen", getClass().getSimpleName()));
        // String id = UUID.randomUUID().toString();
        // HuobiWSSub sub = new HuobiWSSub(String.format("market.%s.depth.%s", symbol, type), id);
        OKExWSSub sub = calcSub();
        this.webSocket.send(ApiUtil.toJson(sub));
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        // logger.info("onMessage {},{}", symbol,text);
    }

    protected abstract void doHandler(T resp);

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        String json = null;
        try {
            json = ApiUtil.uncompress(bytes.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (StringUtils.isEmpty(json)) {
            return;
        }

        if (json.contains("ping")) {
            String pong = json.replace("ping", "pong");
            webSocket.send(pong);
            return;
        }
        // System.out.println(json);
        try {
            T resp =  objectMapper.readValue(json, clazz);
            if (resp.getResult() != null && !resp.getResult().equals("false")) {
                HuobiWSError err = new HuobiWSError(resp.getErrorcode(), resp.getData());
                if (handler != null) {
                    this.handler.onError(err);
                }
            } else {
                this.doHandler(resp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        System.out.println(String.format("%s onClosing %d,%s", getClass().getSimpleName(), code, reason));
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        System.out.println(String.format("%s onClosed %d,%s", getClass().getSimpleName(), code, reason));
        if (this.handler != null) {
            this.handler.onClosed(code, reason);
        }
        if(this.client.getOption().isReconWhenClosed()){
            this.start();
        }

    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        System.out.println(String.format("%s onFailure,%s", getClass().getSimpleName(), t.getMessage()));
        if (this.handler != null) {
            handler.onFailure(t.getMessage());
        }
        if(this.client.getOption().isReconWhenFailure()){
            this.start();
        }
    }

    @Override
    public void close() throws IOException {
        this.shutdown();
    }
}
