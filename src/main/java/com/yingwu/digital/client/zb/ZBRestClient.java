package com.yingwu.digital.client.zb;

import com.alibaba.fastjson.JSONObject;
import com.yingwu.digital.base.DigitalConst;
import com.yingwu.digital.util.DigitalStringUtils;
import com.yingwu.digital.util.HttpUtil;
import com.yingwu.digital.util.ZBEncryDigestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/27
 **/
public class ZBRestClient {

    private static Logger log = LoggerFactory.getLogger(ZBRestClient.class);

    // 正式
    public final String ACCESS_KEY ;
    public final String SECRET_KEY ;
    public final String URL_PREFIX ;//= "https://trade.zb.com/api/"


    public ZBRestClient(String apiKey,String secretKey,String url){
        this.ACCESS_KEY = apiKey;
        this.SECRET_KEY = secretKey;
        this.URL_PREFIX = url;
    }
    public final String PAY_PASS = "xxxx";

    /**
     * 委托下单 tradeType 1买，0卖
     */

    public void order() {
        try {
            // 需加密的请求参数， tradeType=0卖单
            Map<String, String> params = new HashMap<String, String>();
            params.put("method", "order");
            params.put("price", "1.9001");
            params.put("amount", "1.502");
            params.put("tradeType", "1");
            params.put("currency", "qtum_usdt");

            // 请求
            String json = this.getJsonPost(params);
            System.out.println("testOrder 结果: " + json);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 取消下单
     */
    public void cancelOrder(String orderId,String symbols) {
//        String orderId = "201710111625";// 201710111608
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("method", "cancelOrder");
            params.put("id", orderId);
            params.put("currency", "ltc_btc");//"ltc_btc"

            String json = this.getJsonPost(params);
            System.out.println("getOrder 结果: " + json);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 获取订单信息
     */
    public void getOrder(String orderId,String symbols) {
//        String orderId = "201710122805";
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("method", "getOrder");
            params.put("id", orderId);
            params.put("currency", symbols);//"ltc_btc"
            String json = this.getJsonPost(params);

            System.out.println("getOrder 结果: " + json);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 获取多个委托买单或卖单，每次请求返回10条记录
     */
    public void getOrders() {
        try {
            String[] currencyArr = new String[]{"ltc_btc", "eth_btc", "etc_btc"};
            for (String currency : currencyArr) {
                Map<String, String> params = new HashMap<String, String>();
                params.put("method", "getOrders");
                params.put("tradeType", "1");
                params.put("currency", currency);
                params.put("pageIndex", "1");

                String json = this.getJsonPost(params);
                log.info("getOrders 结果: " + json);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * (新)获取多个委托买单或卖单，每次请求返回pageSize<=100条记录
     */
    public void getOrdersNew() {
        try {
            String[] currencyArr = new String[]{"ltc_btc", "eth_btc", "etc_btc"};
            for (String currency : currencyArr) {
                Map<String, String> params = new HashMap<String, String>();
                params.put("method", "getOrdersNew");
                params.put("tradeType", "1");
                params.put("currency", currency);
                params.put("pageIndex", "1");
                params.put("pageSize", "1");
                String json = this.getJsonPost(params);
                log.info("getOrdersNew 结果: " + json);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * 与getOrdersNew的区别是取消tradeType字段过滤，可同时获取买单和卖单，每次请求返回pageSize<=100条记录
     */
    public void getOrdersIgnoreTradeType() {
        try {
            String[] currencyArr = new String[]{"ltc_btc", "eth_btc", "etc_btc"};
            for (String currency : currencyArr) {
                Map<String, String> params = new HashMap<String, String>();
                params.put("method", "getOrdersIgnoreTradeType");
                params.put("currency", currency);
                params.put("pageIndex", "1");
                params.put("pageSize", "1");

                String json = this.getJsonPost(params);
                log.info("getOrdersIgnoreTradeType 结果: " + json);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * 获取未成交或部份成交的买单和卖单，每次请求返回pageSize<=100条记录
     */
    public void getUnfinishedOrdersIgnoreTradeType() {
        try {
            String[] currencyArr = new String[]{"ltc_btc", "eth_btc", "etc_btc"};
            for (String currency : currencyArr) {
                Map<String, String> params = new HashMap<String, String>();
                params.put("method", "getUnfinishedOrdersIgnoreTradeType");
                params.put("currency", currency);
                params.put("pageIndex", "1");
                params.put("pageSize", "10");

                String json = this.getJsonPost(params);
                log.info("getUnfinishedOrdersIgnoreTradeType 结果: " + json);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 获取个人信息
     */

    public void getAccountInfo() {
        try {
            // 需加密的请求参数
            Map<String, String> params = new HashMap<String, String>();
            params.put("method", "getAccountInfo");
            String json = this.getJsonPost(params);
            log.info("getAccountInfo 结果: " + json);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 获取个人的充值地址
     */
    public void getUserAddress() {
        try {
            // 需加密的请求参数
            Map<String, String> params = new HashMap<String, String>();
            params.put("method", "getUserAddress");
            params.put("currency", "btc");
            String json = this.getJsonPost(params);
            System.out.println("getUserAddress 结果: " + json);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 获取认证的提现地址
     */
    public void getWithdrawAddress() {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("method", "getWithdrawAddress");
            params.put("currency", "etc");

            String json = this.getJsonPost(params);
            System.out.println("getWithdrawAddress 结果: " + json);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 获取提现记录
     */
    public void getWithdrawRecord() {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("method", "getWithdrawRecord");
            params.put("currency", "eth");
            params.put("pageIndex", "1");
            params.put("pageSize", "10");
            String json = this.getJsonPost(params);
            System.out.println("getWithdrawRecord 结果: " + json);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 获取虚拟货币充值记录
     */
    public void getChargeRecord() {
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("method", "getChargeRecord");
            params.put("currency", "btc");
            params.put("pageIndex", "1");
            params.put("pageSize", "10");
            String json = this.getJsonPost(params);

            System.out.println("getChargeRecord 结果: " + json);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 提现操作
     */
    public void withdraw() {
        try {
            String addr = "143GwqgnjNi5DqGv4xzwqeGTi7BGxxxxxx";
            String fees = "0.0003";
            String currency = "etc";
            String amount = "0.0004";
            String itransfer = "0";

            Map<String, String> params = new HashMap<String, String>();
            params.put("amount", amount);
            params.put("currency", currency);
            params.put("fees", fees);
            params.put("itransfer", itransfer);
            params.put("method", "withdraw");
            params.put("receiveAddr", addr);
            params.put("safePwd", PAY_PASS);

            String json = this.getJsonPost(params);
            System.out.println("withdraw 结果: " + json);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 获取行情
     */
    public String ticker(String symbols) {
        String result = "";
        try {
            // 请求地址
            String url = DigitalConst.ZB_API_URL + "/data/v1/ticker?market=" + symbols;
            log.info(symbols + "-ticker url: " + url);
            // 请求
            result = get(url, "UTF-8");
            log.info(symbols + "-ticker 结果: " + result);
        } catch (Exception ex) {
            log.error("获取：" + symbols + "行情出错" + ex.toString());
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 获取深度
     */
    public String depth(String symbols,String merge,String size) {

        String url = "";
        String result = "";
        try {
            // 请求地址
            if(DigitalStringUtils.isEmpty(merge) && DigitalStringUtils.isEmpty(size)){
                url = DigitalConst.ZB_API_URL + "/data/v1/depth?market=" + symbols;
            }else if (DigitalStringUtils.isEmpty(merge) && !DigitalStringUtils.isEmpty(size)){
                 url = DigitalConst.ZB_API_URL+"/data/v1/depth?currency=" + symbols +
                 "&size=" + size;
            }else {
                 url = DigitalConst.ZB_API_URL+"/data/v1/depth?currency=" + symbols +
                 "&size=" + size + "&merge=" + merge;
            }
            log.info(symbols + "-testDepth url: " + url);
            // 请求
            result = get(url, "UTF-8");
            log.info(symbols + "-testDepth 结果: " + result);
        } catch (Exception ex) {
            log.error("获取：" + symbols + "行情出错" + ex.toString());
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 获取最近交易记录
     */
    public String trades(String symbols,String tradeId) {
        String result = "";
        try {
            // 请求地址
            String url = DigitalConst.ZB_API_URL + "/data/v1/trades?market=" + symbols;
            log.info(symbols + "-testTrades url: " + url);
            // 请求
            result = get(url, "UTF-8");
            log.info(symbols + "-testTrades 结果: " + result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 获取K线数据
     */
    public String kline(String symbols,String times) {
        String result = "";
        try {
            // 请求地址
            String url = DigitalConst.ZB_API_URL + "/data/v1/kline?market=" + symbols + "&times=" + times;
            log.info(symbols + "-testKline url: " + url);
            // 请求
            result = get(url, "UTF-8");
            log.info(symbols + "-testKline 结果: " + result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 获取json内容(统一加密)
     *
     * @param params
     * @return
     */
    private String getJsonPost(Map<String, String> params) {
        params.put("accesskey", ACCESS_KEY);// 这个需要加入签名,放前面
        String digest = ZBEncryDigestUtil.digest(SECRET_KEY);

        String sign = ZBEncryDigestUtil.hmacSign(params, digest); // 参数执行加密
        String method = params.get("method");

        // 加入验证
        params.put("sign", sign);
        params.put("reqTime", System.currentTimeMillis() + "");
        String url = "请求地址:" + URL_PREFIX + method + " 参数:" + params;
        System.out.println(url);
        String json = "";
        try {
            json = HttpUtil.getInstance().requestHttpPost(URL_PREFIX, method, params);
        } catch (Exception e) {
            log.error("获取交易json异常", e);
        }
        return json;
    }

    /**
     * @param urlAll  :请求接口
     * @param charset :字符编码
     * @return 返回json结果
     */
    public String get(String urlAll, String charset) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";// 模拟浏览器
        try {
            URL url = new URL(urlAll);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(30000);
            connection.setConnectTimeout(30000);
            connection.setRequestProperty("User-agent", userAgent);
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, charset));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
