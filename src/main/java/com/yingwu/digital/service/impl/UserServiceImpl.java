package com.yingwu.digital.service.impl;

import com.yingwu.digital.base.ApiResponse;
import com.yingwu.digital.base.DigitalException;
import com.yingwu.digital.bean.dto.UserInfo;
import com.yingwu.digital.mapper.UserInfoMapper;
import com.yingwu.digital.service.UserService;
import com.yingwu.digital.util.DigitalStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/21
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public ApiResponse newUserInfo(UserInfo userInfo) throws DigitalException {
        ApiResponse apiResponse = new ApiResponse();
        try {
            if(userInfo == null){
                throw new  DigitalException("userInfo不能为空");
            }
            log.info("新增用户信息为userInfo：" + userInfo.toString());
            validateNull(userInfo);
            //入库
            int count = userInfoMapper.insert(userInfo);
            if(count < 1){
                log.info("新增用户，入库错误");
                throw new DigitalException("新增用户，入库错误");
            }
            apiResponse.setSuccess();
        }catch (Exception e){
            log.info("新增用户信息出错" + e.toString());
            throw new DigitalException("新增用户信息出错");
        }
        return apiResponse;
    }

    private void validateNull(UserInfo userInfo) {
        //账号非空校验
        if(DigitalStringUtils.isNull(userInfo.getAccount())){
            throw new DigitalException("新增用户时，账号不能为空");
        }
        if(DigitalStringUtils.isNull(userInfo.getApiKey())){
            throw new DigitalException("新增用户时，apikey不能为空");
        }
        if(DigitalStringUtils.isNull(userInfo.getSecretKey())){
            throw new DigitalException("新增用户时，secretKey不能为空");
        }
        if(DigitalStringUtils.isNull(userInfo.getStartDate())){
            throw new DigitalException("新增用户时，起始时间不能为空");
        }
        if(DigitalStringUtils.isNull(userInfo.getBaseCurrency())){
            throw new DigitalException("新增用户时，起始币种不能为空");
        }
        if(DigitalStringUtils.isNull(userInfo.getBaseCount())){
            throw new DigitalException("新增用户时，起始币种不能为空");
        }
    }
}
