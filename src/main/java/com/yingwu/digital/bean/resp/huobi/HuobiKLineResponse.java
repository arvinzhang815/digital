package com.yingwu.digital.bean.resp.huobi;

import com.yingwu.digital.base.DigitalException;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/25
 **/
public class HuobiKLineResponse<T>  {

        private String status;
        private String ch;
        private String ts;
        public String errCode;
        public String errMsg;
        public T data;

        public T checkAndReturn() {
            if ("ok".equals(status)) {
                return data;
            }
            throw new DigitalException(errCode, errMsg);
        }


        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCh() {
            return ch;
        }

        public void setCh(String ch) {
            this.ch = ch;
        }

        public String getTs() {
            return ts;
        }

        public void setTs(String ts) {
            this.ts = ts;
        }
}
