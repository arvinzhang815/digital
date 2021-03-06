package com.yingwu.digital.service;

import com.yingwu.digital.base.ApiRequest;
import com.yingwu.digital.base.ApiResponse;
import com.yingwu.digital.base.DigitalException;

public interface HuobiApiService {

    public ApiResponse subKline(ApiRequest request)throws DigitalException;
    public ApiResponse subDepth(ApiRequest request)throws DigitalException;
    public ApiResponse subTradeDetail(ApiRequest request)throws DigitalException;
    public ApiResponse requestMsg(String json)throws DigitalException;
}
