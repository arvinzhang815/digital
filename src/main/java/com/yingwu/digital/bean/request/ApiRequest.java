package com.yingwu.digital.bean.request;

import java.util.Date;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/19
 **/
public class ApiRequest {
    private String apiKey;//apikey
    private String secretKey;//secretKey
    private String assetPassword;//密码
    private String account;//账户
    private String requestTime = new Date().toString();//请求时间

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getAssetPassword() {
        return assetPassword;
    }

    public void setAssetPassword(String assetPassword) {
        this.assetPassword = assetPassword;
    }

    @Override
    public String toString() {
        return "ApiRequest{" +
                "apiKey='" + apiKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                ", assetPassword='" + assetPassword + '\'' +
                ", account='" + account + '\'' +
                ", requestTime='" + requestTime + '\'' +
                '}';
    }
}
