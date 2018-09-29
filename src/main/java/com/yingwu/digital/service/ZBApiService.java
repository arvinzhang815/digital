package com.yingwu.digital.service;

import com.yingwu.digital.base.ApiResponse;
import com.yingwu.digital.base.DigitalException;
import com.yingwu.digital.bean.request.zb.ZBRestApiRequest;

public interface ZBApiService {

    public ApiResponse subKline(ZBRestApiRequest request)throws DigitalException;
    public ApiResponse subDepth(ZBRestApiRequest request)throws DigitalException;
    public ApiResponse subTicker(ZBRestApiRequest request)throws DigitalException;
    public ApiResponse subDeals(ZBRestApiRequest request)throws DigitalException;
    public ApiResponse requestMsg(String json)throws DigitalException;
}
