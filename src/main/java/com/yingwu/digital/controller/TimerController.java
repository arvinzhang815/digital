package com.yingwu.digital.controller;

import com.yingwu.digital.bean.request.ApiRequest;
import com.yingwu.digital.service.HuobiApiService;
import com.yingwu.digital.service.impl.HuobiApiServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/19
 **/
@Component
public class TimerController {
    private static Logger log = LoggerFactory.getLogger(TimerController.class);
    @Autowired
    private HuobiApiService huobiApiService;

    @Scheduled(cron = "0/20 * * * * *")
    public void scheduled() {
        log.info("=====>>>>>使用cron  {}", System.currentTimeMillis());
        //    private String API_KEY = "7d8219aa-09bfe59d-1f9089a1-28430";
        //
        //    private String API_SECRET = "90f6a25d-8421df14-816d2a20-379a9";
//        ApiRequest request = new ApiRequest();
//        request.setApiKey("7d8219aa-09bfe59d-1f9089a1-28430");
//        request.setSecretKey("90f6a25d-8421df14-816d2a20-379a9");
//        System.out.println(huobiApiService.test());
        System.out.println("helllo");
    }

    @Scheduled(fixedRate = 5000)
    public void scheduled1() {
        log.info("=====>>>>>使用fixedRate{}", System.currentTimeMillis());
        System.out.println("world");
    }

    @Scheduled(fixedDelay = 5000)
    public void scheduled2() {
        log.info("=====>>>>>fixedDelay{}", System.currentTimeMillis());
        System.out.println("yingwu");
    }
}
