package com.yingwu.digital.controller;

import com.yingwu.digital.base.ApiRequest;
import com.yingwu.digital.base.ApiResponse;
import com.yingwu.digital.service.HuobiApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/huobiapi")
public class HoubiApiController {

    @Autowired
    @Qualifier("ehoubiApiService")
    private HuobiApiService houbiApiService;

    private static Logger log = LoggerFactory.getLogger(HoubiApiController.class);

    @RequestMapping("/subkline")
    public ApiResponse subKline(@RequestBody ApiRequest apiRequest){
        log.info("subKline请求参数：" + apiRequest.toString());
        ApiResponse  apiResponse = new ApiResponse();
        try {
            apiResponse = houbiApiService.subKline(apiRequest);
            log.info("subKline请求返回值：" + apiResponse.toString());
        } catch (Exception e) {
            log.info("subKline请求异常：" + e.toString());
            apiResponse.setError();
        }
        return apiResponse;
    }

    @RequestMapping("/subdepth")
    public ApiResponse subDepth(@RequestBody ApiRequest apiRequest){
        log.info("subDepth请求参数：" + apiRequest.toString());
        ApiResponse  apiResponse = new ApiResponse();
        try {

            apiResponse = houbiApiService.subDepth(apiRequest);
            log.info("subDepth请求返回值：" + apiResponse.toString());
        } catch (Exception e) {
            log.info("subDepth请求异常：" + e.toString());
            apiResponse.setError();
        }
        return apiResponse;
    }
    @RequestMapping("/subtradedetail")
    public ApiResponse subTradeDetail(@RequestBody ApiRequest apiRequest){
        log.info("subTradeDetail请求参数：" + apiRequest.toString());
        ApiResponse  apiResponse = new ApiResponse();
        try {
            apiResponse = houbiApiService.subTradeDetail(apiRequest);
            log.info("subTradeDetail请求返回值：" + apiResponse.toString());
        } catch (Exception e) {
            log.info("subTradeDetail请求异常：" + e.toString());
            apiResponse.setError();
        }
        return apiResponse;
    }
}
