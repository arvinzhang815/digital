package com.yingwu.digital.controller;

import com.yingwu.digital.bean.request.huobi.HuobiWSApiRequest;
import com.yingwu.digital.base.ApiResponse;
import com.yingwu.digital.service.OKExApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/okex")
public class OKExController {

    @Autowired
    @Qualifier("eokexApiService")
    private OKExApiService okexApiService;

    private static Logger log = LoggerFactory.getLogger(OKExController.class);

    @RequestMapping("/subkline")
    public ApiResponse subKline(@RequestBody HuobiWSApiRequest huobiWSApiRequest){
        log.info("subKline请求参数：" + huobiWSApiRequest.toString());
        ApiResponse  apiResponse = new ApiResponse();
        try {
            apiResponse = okexApiService.subKline(huobiWSApiRequest);
            log.info("subKline请求返回值：" + apiResponse.toString());
        } catch (Exception e) {
            log.info("subKline请求异常：" + e.toString());
            apiResponse.setError();
        }
        return apiResponse;
    }

    @RequestMapping("/subdepth")
    public ApiResponse subDepth(@RequestBody HuobiWSApiRequest huobiWSApiRequest){
        log.info("subDepth请求参数：" + huobiWSApiRequest.toString());
        ApiResponse  apiResponse = new ApiResponse();
        try {

            apiResponse = okexApiService.subDepth(huobiWSApiRequest);
            log.info("subDepth请求返回值：" + apiResponse.toString());
        } catch (Exception e) {
            log.info("subDepth请求异常：" + e.toString());
            apiResponse.setError();
        }
        return apiResponse;
    }
    @RequestMapping("/subticker")
    public ApiResponse subTicker(@RequestBody HuobiWSApiRequest huobiWSApiRequest){
        log.info("subTicker请求参数：" + huobiWSApiRequest.toString());
        ApiResponse  apiResponse = new ApiResponse();
        try {
            apiResponse = okexApiService.subTicker(huobiWSApiRequest);
            log.info("subTicker请求返回值：" + apiResponse.toString());
        } catch (Exception e) {
            log.info("subTicker请求异常：" + e.toString());
            apiResponse.setError();
        }
        return apiResponse;
    }
    @RequestMapping("/subdeals")
    public ApiResponse subDeals(@RequestBody HuobiWSApiRequest huobiWSApiRequest){
        log.info("subDeals请求参数：" + huobiWSApiRequest.toString());
        ApiResponse  apiResponse = new ApiResponse();
        try {
            apiResponse = okexApiService.subDeals(huobiWSApiRequest);
            log.info("subDeals请求返回值：" + apiResponse.toString());
        } catch (Exception e) {
            log.info("subDeals请求异常：" + e.toString());
            apiResponse.setError();
        }
        return apiResponse;
    }
}
