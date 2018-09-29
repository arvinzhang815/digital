package com.yingwu.digital.controller;

import com.yingwu.digital.base.ApiResponse;
import com.yingwu.digital.bean.request.ApiRequest;
import com.yingwu.digital.bean.request.zb.ZBRestApiRequest;
import com.yingwu.digital.mapper.zb.ZBKLineMapper;
import com.yingwu.digital.service.HuobiApiService;
import com.yingwu.digital.service.ZBApiService;
import com.yingwu.digital.service.impl.HuobiApiServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/19
 **/
@Component
public class TimerController {
    private static Logger log = LoggerFactory.getLogger(TimerController.class);
    @Autowired
    private ZBApiService zbApiService;

//    @Scheduled(cron = "0 0,30 0/1 * * ? ")
    public void scheduled() {

        ZBRestApiRequest request = new ZBRestApiRequest();
        List<String> list = Arrays.asList("1min","3min","5min","15min","30min","1day","3day","1week","1hour","2hour","4hour","6hour","12hour");
        for(String temp:list){
            request.setSymbols("btc_usdt");
            request.setTimes(temp);
            ApiResponse response = zbApiService.subDeals(request);
            zbApiService.subDepth(request);
            zbApiService.subTicker(request);
        }
    }

//    @Scheduled(fixedRate = 5000)
    public void scheduled1() {
        log.info("=====>>>>>使用fixedRate{}", System.currentTimeMillis());
        System.out.println("world");
    }

//    @Scheduled(fixedDelay = 5000)
    public void scheduled2() {
        log.info("=====>>>>>fixedDelay{}", System.currentTimeMillis());
        System.out.println("yingwu");
    }
}
