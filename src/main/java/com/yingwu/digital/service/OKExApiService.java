package com.yingwu.digital.service;

import com.yingwu.digital.bean.request.huobi.HuobiWSApiRequest;
import com.yingwu.digital.base.ApiResponse;
import com.yingwu.digital.base.DigitalException;

public interface OKExApiService {

    public ApiResponse subKline(HuobiWSApiRequest request)throws DigitalException;
    public ApiResponse subDepth(HuobiWSApiRequest request)throws DigitalException;
    public ApiResponse subTicker(HuobiWSApiRequest request)throws DigitalException;
    public ApiResponse subDeals(HuobiWSApiRequest request)throws DigitalException;
    public ApiResponse requestMsg(String json)throws DigitalException;
}
