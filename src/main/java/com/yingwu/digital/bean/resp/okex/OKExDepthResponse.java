package com.yingwu.digital.bean.resp.okex;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/11
 **/
public class OKExDepthResponse extends OKExBaseResponse {
    private DepthData depthData;
    private class DepthData{
        private String timestamp;
        private String asks;//卖方深度
        private String bids;//买方深度

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getAsks() {
            return asks;
        }

        public void setAsks(String asks) {
            this.asks = asks;
        }

        public String getBids() {
            return bids;
        }

        public void setBids(String bids) {
            this.bids = bids;
        }

        @Override
        public String toString() {
            return "DepthData{" +
                    "timestamp='" + timestamp + '\'' +
                    ", asks='" + asks + '\'' +
                    ", bids='" + bids + '\'' +
                    '}';
        }
    }

    public DepthData getDepthData() {
        return depthData;
    }

    public void setDepthData(DepthData depthData) {
        this.depthData = depthData;
    }

    @Override
    public String toString() {
        return "OKExDepthResponse{" +
                "depthData=" + depthData +
                '}';
    }
}
