package com.yingwu.digital.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/18
 **/
public class DigitalStringUtils {
    public static boolean isEmpty(String str) {
        if(str == null)
            return true;
        String tempStr = str.trim();
        if(tempStr.length() == 0)
            return true;
        if(tempStr.equals("null"))
            return true;
        return false;
    }

    /**
     * 判断对象是否为空或空值、空格
     * @param value
     * @return
     */
    public static boolean isNull(Object value) {
        boolean result = false;
        if (value == null) {
            return true;
        }
        if (value instanceof String) {
            result = StringUtils.isBlank((String)value);
        }
        if(value instanceof BigDecimal){
            result = (value == null);
        }
        return result;
    }

    /**
     * 判断对象是否不为空或空值、空格
     * @param value
     * @return
     */
    public static boolean isNotNull(Object value) {
        return !isNull(value);
    }
}
