package com.yingwu.digital.controller;

import com.yingwu.digital.base.ApiResponse;
import com.yingwu.digital.base.DigitalException;
import com.yingwu.digital.bean.dto.UserInfo;
import com.yingwu.digital.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/21
 **/
@Controller
@RequestMapping("user")
public class UserController {

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("new")
    public ApiResponse newUserInfo(UserInfo userInfo){
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse = userService.newUserInfo(userInfo);
        }catch (DigitalException e){
            log.info("新增用户信息出错");
            apiResponse.setError();
        }
        return apiResponse;
    }
}
