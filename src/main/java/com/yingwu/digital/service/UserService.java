package com.yingwu.digital.service;

import com.yingwu.digital.base.ApiResponse;
import com.yingwu.digital.base.DigitalException;
import com.yingwu.digital.bean.dto.UserInfo;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/21
 **/
public interface UserService {
    public ApiResponse newUserInfo(UserInfo userInfo) throws DigitalException;
}
