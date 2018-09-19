package com.yingwu.digital.bean.resp;

import com.yingwu.digital.base.DigitalException;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/17
 **/
public class ApiResponse<T> {

    public String status;
    public String errCode;
    public String errMsg;
    public T data;

    public T checkAndReturn() {
        if ("ok".equals(status)) {
            return data;
        }
        throw new DigitalException(errCode, errMsg);
    }
}
