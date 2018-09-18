//package com.yingwu.digital;
//
//
//import com.yingwu.digital.client.huobi.HuobiApiRestClient;
//import com.yingwu.digital.client.huobi.HuobiApiWSClient;
//import com.yingwu.digital.client.huobi.HuobiApiRestClientImpl;
//import com.yingwu.digital.client.huobi.HuobiApiWSClientImpl;
//import com.yingwu.digital.client.okex.OKExApiWSClient;
//import com.yingwu.digital.client.okex.OKExApiWSClientImpl;
//
//public class ApiClientFactory {
//
//    private String apiKey = "7d8219aa-09bfe59d-1f9089a1-28430";
//
//    private String secret = "90f6a25d-8421df14-816d2a20-379a9";
//
//    private String privateKey = null;
//
//    public ApiClientFactory() {
//    }
//
//    private ApiClientFactory(String apiKey, String secret) {
//       this(apiKey,secret,null);
//    }
//
//    private ApiClientFactory(String apiKey, String secret, String privateKey) {
//        this.apiKey = apiKey;
//        this.secret = secret;
//        this.privateKey = privateKey;
//    }
//
//    public static ApiClientFactory newInstance(){
//        return new ApiClientFactory();
//    }
//
//    public static ApiClientFactory newInstance(String apiKey, String secret,String privateKey){
//        return new ApiClientFactory(apiKey, secret,privateKey);
//    }
//
//    public static ApiClientFactory newInstance(String apiKey, String secret){
//        return new ApiClientFactory(apiKey, secret);
//    }
//
//    public HuobiApiRestClient newRestClient(){
//        return new HuobiApiRestClient(apiKey, secret,privateKey);
//    }
//    public HuobiApiWSClient newWSClient(){
//        return new HuobiApiWSClientImpl();
//    }
//    public OKExApiWSClient newOKExWSClient(){
//        return new OKExApiWSClientImpl();
//    }
//
//}
