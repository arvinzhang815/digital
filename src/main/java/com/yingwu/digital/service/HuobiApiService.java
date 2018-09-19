package com.yingwu.digital.service;

import com.yingwu.digital.bean.request.ApiRequest;
import com.yingwu.digital.bean.request.huobi.HuobiOrderInfoRequest;
import com.yingwu.digital.bean.request.huobi.HuobiWSApiRequest;
import com.yingwu.digital.base.ApiResponse;
import com.yingwu.digital.base.DigitalException;

public interface HuobiApiService {

    public ApiResponse subKline(HuobiWSApiRequest request)throws DigitalException;
    public ApiResponse subDepth(HuobiWSApiRequest request)throws DigitalException;
    public ApiResponse subTradeDetail(HuobiWSApiRequest request)throws DigitalException;
    public ApiResponse requestMsg(String json)throws DigitalException;
    public ApiResponse getUserInfo(ApiRequest request) throws DigitalException;
    public ApiResponse getOrderInfo(HuobiOrderInfoRequest request) throws DigitalException;
    public ApiResponse getOrders(ApiRequest request) throws DigitalException;


}
