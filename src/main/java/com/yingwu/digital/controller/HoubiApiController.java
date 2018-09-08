package com.yingwu.digital.controller;

import com.yingwu.digital.base.ApiRequest;
import com.yingwu.digital.base.ApiResponse;
import com.yingwu.digital.service.HuobiApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/huobiapi")
public class HoubiApiController {

    @Autowired
    @Qualifier("ehoubiApiService")
    private HuobiApiService houbiApiService;

    @RequestMapping("/subkline")
    public ApiResponse subKline(@RequestBody ApiRequest apiRequest){
        ApiResponse  apiResponse = new ApiResponse();
        try {
            apiResponse = houbiApiService.subKline(apiRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResponse;
    }

    @RequestMapping("/subdepth")
    public ApiResponse subDepth(@RequestBody ApiRequest apiRequest){
        ApiResponse  apiResponse = new ApiResponse();
        try {
            apiResponse = houbiApiService.subDepth(apiRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResponse;
    }
    @RequestMapping("/subtradedetail")
    public ApiResponse subTradeDetail(@RequestBody ApiRequest apiRequest){
        ApiResponse  apiResponse = new ApiResponse();
        try {
            apiResponse = houbiApiService.subTradeDetail(apiRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResponse;
    }
}
