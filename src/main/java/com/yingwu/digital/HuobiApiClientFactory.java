package com.yingwu.digital;


import com.yingwu.digital.client.huobi.HuobiApiRestClient;
import com.yingwu.digital.client.huobi.HuobiApiWSClient;
import com.yingwu.digital.client.huobi.HuobiApiRestClientImpl;
import com.yingwu.digital.client.huobi.HuobiApiWSClientImpl;

public class HuobiApiClientFactory {

    private String apiKey = null;

    private String secret = null;

    private String privateKey = null;

    public HuobiApiClientFactory() {
    }

    private HuobiApiClientFactory(String apiKey, String secret) {
       this(apiKey,secret,null);
    }

    private HuobiApiClientFactory(String apiKey, String secret, String privateKey) {
        this.apiKey = apiKey;
        this.secret = secret;
        this.privateKey = privateKey;
    }

    public static HuobiApiClientFactory newInstance(){
        return new HuobiApiClientFactory();
    }

    public static HuobiApiClientFactory newInstance(String apiKey, String secret,String privateKey){
        return new HuobiApiClientFactory(apiKey, secret,privateKey);
    }

    public static HuobiApiClientFactory newInstance(String apiKey, String secret){
        return new HuobiApiClientFactory(apiKey, secret);
    }

    public HuobiApiRestClient newRestClient(){
        return new HuobiApiRestClientImpl(apiKey, secret,privateKey);
    }
    public HuobiApiWSClient newWSClient(){
        return new HuobiApiWSClientImpl();
    }

}
