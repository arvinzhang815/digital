package com.yingwu.digital.service.impl;

import com.yingwu.digital.ApiClientFactory;
import com.yingwu.digital.base.DigitalConst;
import com.yingwu.digital.bean.HuobiOrderMatchResult;
import com.yingwu.digital.bean.dto.UserInfo;
import com.yingwu.digital.bean.dto.huobi.*;
import com.yingwu.digital.bean.request.ApiRequest;
import com.yingwu.digital.bean.request.huobi.HuobiOrderInfoRequest;
import com.yingwu.digital.bean.resp.huobi.BalanceResponse;
import com.yingwu.digital.bean.resp.huobi.MatchresultsOrdersDetailResponse;
import com.yingwu.digital.bean.resp.huobi.TradeResponse;
import com.yingwu.digital.client.huobi.HuobiApiRestClient;
import com.yingwu.digital.client.huobi.HuobiApiWSClient;
import com.yingwu.digital.bean.request.huobi.HuobiWSApiRequest;
import com.yingwu.digital.base.ApiResponse;
import com.yingwu.digital.base.DigitalException;
import com.yingwu.digital.bean.HuobiKLineData;
import com.yingwu.digital.bean.HuobiTradeDetail;
import com.yingwu.digital.bean.ws.HuobiWSDepthEvent;
import com.yingwu.digital.bean.ws.HuobiWSKLineEvent;
import com.yingwu.digital.bean.ws.HuobiWSTradeDetailEvent;
import com.yingwu.digital.dao.UserInfoMapper;
import com.yingwu.digital.dao.huobi.HuobiDepthMapper;
import com.yingwu.digital.dao.huobi.HuobiEntrustInfoMapper;
import com.yingwu.digital.dao.huobi.HuobiKLineMapper;
import com.yingwu.digital.dao.huobi.HuobiTradeDetailMapper;
import com.yingwu.digital.service.HuobiApiService;
import com.yingwu.digital.service.HuobiWSEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketClient;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("ehoubiApiService")
public class HuobiApiServiceImpl implements HuobiApiService {
    private WebSocketClient client = null;

    @Autowired
    private HuobiKLineMapper huobiKLineMapper;
    @Autowired
    private HuobiDepthMapper huobiDepthMapper;
    @Autowired
    private HuobiTradeDetailMapper huobiTradeDetailMapper;
    @Autowired
    private HuobiEntrustInfoMapper huobiEntrustInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private JavaMailSender mailSender;

    private static Logger log = LoggerFactory.getLogger(HuobiApiServiceImpl.class);
    private static ApiClientFactory factory = ApiClientFactory.newInstance();

    @Override
    public ApiResponse subKline(HuobiWSApiRequest huobiWSApiRequest) throws DigitalException {
        ApiResponse response = new ApiResponse();
        HuobiApiWSClient client = factory.newWSClient();
        try {
            huobiWSApiRequest = getSymbolBySub(huobiWSApiRequest);
            log.info("开始订阅时apiRequest：" + huobiWSApiRequest.toString());
            client.kline(huobiWSApiRequest.getSymbol(), huobiWSApiRequest.getType(), new HuobiWSEventHandler() {
                @Override
                public void handleKLine(HuobiWSKLineEvent event) {
                    KLine kLine = new KLine();
                    BeanUtils.copyProperties(event,kLine);
                    HuobiKLineData data = event.getData();
                    BeanUtils.copyProperties(data,kLine);
                    kLine.setKlinId(data.getId());
                    log.info("K线数据"+kLine.toString());
                    int count = huobiKLineMapper.insert(kLine);
                    if(count < 1){
                        log.info("K线数据入库出错" + event.toString());
                        throw new DigitalException("K线数据入库出错" + event.toString());
                    }
                    response.setSuccess();
                    response.setData(event.toString());
                }
            });
        } catch (Exception e) {
//            throw new DigitalException("新增K线出错" + e.toString());
            log.info("订阅K线异常" + e.toString());
            response.setError();
            response.setData(e.toString());
        }
        return response;
    }

    private HuobiWSApiRequest getSymbolBySub(HuobiWSApiRequest huobiWSApiRequest) {
        String[] beginIndex = huobiWSApiRequest.getSub().split("\\.");
        huobiWSApiRequest.setSymbol(beginIndex[1]);
        huobiWSApiRequest.setType(beginIndex[3]);
        return huobiWSApiRequest;
    }

    @Override
    public ApiResponse subDepth(HuobiWSApiRequest huobiWSApiRequest) throws DigitalException {
        ApiResponse response = new ApiResponse();
        HuobiApiWSClient client = factory.newWSClient();
        try {
            huobiWSApiRequest = getSymbolBySub(huobiWSApiRequest);
            log.info("开始订阅时apiRequest：" + huobiWSApiRequest.toString());
            client.depth(huobiWSApiRequest.getSymbol(), huobiWSApiRequest.getType(), new HuobiWSEventHandler() {
                @Override
                public void handleDepth(HuobiWSDepthEvent event) {
                    Depth depth = new Depth();
                    BeanUtils.copyProperties(event,depth);
                    depth.setAsks(event.getAsks().toString());
                    depth.setBids(event.getBids().toString());
                    log.info("深度数据"+depth.toString());
                    int count = huobiDepthMapper.insert(depth);
                    if(count < 1){
                        log.info("深度数据入库异常" + event.toString());
                        throw new DigitalException("深度数据入库异常" + event.toString());
                    }
                    response.setSuccess();
                    response.setData(event.toString());
                }
            });
        } catch (Exception e) {
            log.info("订阅深度异常" + e.toString());
            response.setError();
            response.setData(e.toString());
        }
        return response;
    }

    @Override
    public ApiResponse subTradeDetail(HuobiWSApiRequest huobiWSApiRequest) throws DigitalException {
        ApiResponse response = new ApiResponse();
        HuobiApiWSClient client = factory.newWSClient();
        try {
            huobiWSApiRequest = getSymbolBySub(huobiWSApiRequest);
            log.info("开始订阅时apiRequest：" + huobiWSApiRequest.toString());
            client.tradeDetail(huobiWSApiRequest.getSymbol(), new HuobiWSEventHandler() {

                @Override
                public void handleTradeDetail(HuobiWSTradeDetailEvent event) {

                    TradeDetail tradeDetail = new TradeDetail();
                    BeanUtils.copyProperties(event,tradeDetail);
                    if(event.getDetails() != null && event.getDetails().size() > 0){
                        for(HuobiTradeDetail tmp : event.getDetails()){
                            BeanUtils.copyProperties(tmp,tradeDetail);
                            tradeDetail.setTradeId(tmp.getId());
                            tradeDetail.setTradeTs(tmp.getTs());
                            log.info("交易详情数据"+tradeDetail.toString());
                            int count = huobiTradeDetailMapper.insert(tradeDetail);
                            if(count < 1){
                                log.info("交易详情入库异常" + event.toString());
                                throw new DigitalException("交易详情入库异常" + event.toString());
                            }
                        }
                    }
                    response.setSuccess();
                    response.setData(event.toString());
                }
            });
        } catch (Exception e) {
            log.info("订阅交易详情异常" + e.toString());
            response.setError();
            response.setData(e.toString());
        }
        return response;
    }

    @Override
    public ApiResponse requestMsg(String json) throws DigitalException {
//        JSONObject sub = new JSONObject();
//        JSONObject sub = new JSONObject();
//        sub.put("sub", "market.btcusdt.kline.1min");
//        sub.put("id", "id1000");
//        client.sendText(sub.toString());

//
//        sub.put("sub", "market.btcusdt.kline.1min");
//        sub.put("id", "id1000");
        return new ApiResponse();
    }

    @Override
    public ApiResponse getUserInfo(ApiRequest request) throws DigitalException {
        ApiResponse response = new ApiResponse();
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> returnMap = new HashMap<>();
        try {
            //根据account获取apikey apisecretkey
            UserInfo info = new UserInfo();
            info.setAccount(request.getAccount());
            List<UserInfo> userInfoList = userInfoMapper.getUserInfo(info);
            if (userInfoList != null && userInfoList.size() > 0){
                info = userInfoList.get(0);
            }else{
                log.info("查询不到account：" + request.getAccount() + "用户相关信息");
                response.setError();
                response.setMsg("查询结果为空");
                return response;
            }
            //获取账号所有账户下所有币种余额
            resultMap = getUserBalanceInfo(info);

            //根据所有余额信息获取单币种总数
            Map<String,BigDecimal> countMap = getBalanceCountByMap(resultMap);
            //换算成币本位值
            BigDecimal lastValue = getLastValueByCount(countMap,info);
            returnMap.put("userInfo",info);
            returnMap.put("lastValue",lastValue);
            int opDate = getOpDate(info);
            returnMap.put("opDate",opDate);
            returnMap.put("change",lastValue.divide(info.getBaseCount(),6,BigDecimal.ROUND_FLOOR));
            response.setSuccess();
            response.setData(returnMap);
            log.info("账户APIKey：" + request.getApiKey() + "余额信息：" + resultMap.toString());
        }catch (Exception e){
            log.info("获取账户信息出错" + e.toString());
            throw new DigitalException("获取账户信息出错" );
        }
        return response;
    }

    private int getOpDate(UserInfo userInfo) {
        long nowDateTime = new Date().getTime();
        int opDate = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            long startDateTime = dateFormat.parse(userInfo.getStartDate()).getTime();
            opDate = (int)(nowDateTime - startDateTime)/(24*60*60*1000);
        } catch (ParseException e) {
            log.info("时间解析出错" + userInfo.getStartDate());
        }
        return opDate;
    }

    private BigDecimal getLastValueByCount(Map<String, BigDecimal> countMap, UserInfo info) {
        String baseCurrency = info.getBaseCurrency();
        log.info("基础币种为：" + baseCurrency);
        BigDecimal result = BigDecimal.ZERO;
        HuobiApiRestClient restClient  = new HuobiApiRestClient(info.getApiKey(),info.getSecretKey(),info.getPassword());
        for(Map.Entry<String,BigDecimal> entry : countMap.entrySet()){
            //原币种
            if(baseCurrency.equals(entry.getKey())){
                result = result.add(entry.getValue());
            }else { //其他币种，查询最新成交价换算
                //组合交易对
                String symbols = baseCurrency + entry.getKey();
                //获取当前交易对最新成交价
                TradeResponse response = restClient.trade(symbols);
                //判断交易对是否正确
                //FIXME 交易对问题
                if(!response.getStatus().equals("ok")){
                    symbols = entry.getKey() + baseCurrency;
                    response = restClient.trade(symbols);
                    if(!response.getStatus().equals("ok")){
                        //交易对错误
                        log.info("最新成交价查询错误，交易对：" + symbols + "返回值为：" + response.toString());

                    }else{
                        //获取最新成交价
                        List<Map<String,Object>> tradeData = (List<Map<String,Object>>)response.getTick().getData();
                        BigDecimal lastPrice = BigDecimal.valueOf((Double) tradeData.get(0).get("price")) ;
                        log.info("交易对------" + symbols + "最新成交价：" + lastPrice + "====result:" + result);
                        //换算成基础币
                        result = result.add(entry.getValue().multiply(lastPrice));
                    }
                }else{
                    //获取最新成交价
                    List<Map<String,Object>> tradeData = (List<Map<String,Object>>)response.getTick().getData();
                    BigDecimal lastPrice = BigDecimal.valueOf((Double) tradeData.get(0).get("price")) ;
                    //换算成基础币
                    log.info("交易对------" + symbols + "最新成交价：" + lastPrice + "====result:" + result);
                    result = result.add(entry.getValue().divide(lastPrice,10,BigDecimal.ROUND_HALF_UP));
                }
            }

        }
        return result;
    }

    private Map<String, BigDecimal> getBalanceCountByMap(Map<String, Object> resultMap) {
        //遍历不同账户类别 获取单个币种的总数量
        Map<String, BigDecimal> countMap = new HashMap<>();
        for(Map.Entry<String,Object> entry : resultMap.entrySet()){
            List<HuobiBalanceCurrency> balanceCurrencyList = (List<HuobiBalanceCurrency>)entry.getValue();
            for(HuobiBalanceCurrency temp : balanceCurrencyList){
                BigDecimal tempBalance = temp.getBalance();
                if(countMap.containsKey(temp.getCurrency())){
                    tempBalance = countMap.get(temp.getCurrency()).add(tempBalance);
                }
                countMap.put(temp.getCurrency(),tempBalance);
            }
        }
        return countMap;
    }

    private Map<String, Object> getUserBalanceInfo(UserInfo info) {
        Map<String,Object> resultMap = new HashMap<>();

        //先获取该apikey的所有账户信息
        HuobiApiRestClient restClient  = new HuobiApiRestClient(info.getApiKey(),info.getSecretKey(),info.getPassword());
        List<Account> accountList = restClient.getAccounts();
        if(accountList != null && accountList.size() > 0){
            for(Account temp : accountList){
                if(!DigitalConst.HUOBI_ACCOUNT_STATE_WORKING.equals(temp.getState())){
                    log.info("账户：" + temp.getId()+"被锁定");
                    continue;
                }
                if(DigitalConst.HUOBI_ACCOUNT_TYPE_SPOT.equals(temp.getType())){//现货账户
                    List<HuobiBalanceCurrency> retrunBalance = getBalanceListByAccountId(restClient,temp.getId());
                    if(retrunBalance != null && retrunBalance.size() > 0){
                        resultMap.put("DigitalConst.HUOBI_ACCOUNT_TYPE_SPOT",retrunBalance);
                    }
                }else if (DigitalConst.HUOBI_ACCOUNT_TYPE_OTC.equals(temp.getType())){//otc账户
                    List<HuobiBalanceCurrency> retrunBalance = getBalanceListByAccountId(restClient,temp.getId());
                    if(retrunBalance != null && retrunBalance.size() > 0){
                        resultMap.put("DigitalConst.HUOBI_ACCOUNT_TYPE_OTC",retrunBalance);
                    }
                }else if(DigitalConst.HUOBI_ACCOUNT_TYPE_MARGIN.equals(temp.getType())){//杠杆账户
                    List<HuobiBalanceCurrency> retrunBalance = getBalanceListByAccountId(restClient,temp.getId());
                    if(retrunBalance != null && retrunBalance.size() > 0){
                        resultMap.put("DigitalConst.HUOBI_ACCOUNT_TYPE_MARGIN",retrunBalance);
                    }
                }else if(DigitalConst.HUOBI_ACCOUNT_TYPE_POINT.equals(temp.getType())){//点卡账户
                    List<HuobiBalanceCurrency> retrunBalance = getBalanceListByAccountId(restClient,temp.getId());
                    if(retrunBalance != null && retrunBalance.size() > 0){
                        resultMap.put("DigitalConst.HUOBI_ACCOUNT_TYPE_POINT",retrunBalance);
                    }
                }
            }
        }
        return resultMap;
    }

    @Override
    public ApiResponse getOrderInfo(HuobiOrderInfoRequest request) throws DigitalException {
        ApiResponse response = new ApiResponse();
        try {
            //获取指定订单的订单详情
            HuobiApiRestClient restClient = new HuobiApiRestClient(request.getApiKey(), request.getSecretKey(), request.getAssetPassword());
            MatchresultsOrdersDetailResponse ordersDetailResponse = restClient.matchresults(request.getOrderId());
            response.setSuccess();
            response.setData(ordersDetailResponse.getData());
        } catch (Exception e) {
            log.info("查询订单：" + request.getOrderId() + "出错");
            throw new DigitalException("查询订单详情出错");
        }
        return response;
    }

//    @Scheduled(cron = "0 0 0 1/1 * ? ")  //每天一次
    @Override
    public ApiResponse getOrders(ApiRequest request) throws DigitalException {
        ApiResponse response = new ApiResponse();
        try {
            HuobiApiRestClient restClient = new HuobiApiRestClient(request.getApiKey(), request.getSecretKey(), request.getAssetPassword());
            MatchresultsOrdersDetailResponse ordersDetailResponse = restClient.matchresult();
            log.info("订单查询返回值为：" + ordersDetailResponse.toString());
            List<HuobiOrderMatchResult> responseList = (List<HuobiOrderMatchResult>)ordersDetailResponse.getData();
            for(HuobiOrderMatchResult temp : responseList){
                HuobiEntrustInfo info = new HuobiEntrustInfo();
                BeanUtils.copyProperties(temp,info);
                //入库
                int count = huobiEntrustInfoMapper.insert(info);
                if(count < 1){
                    log.info("交易详情入库异常" + info.toString());
                    throw new DigitalException("交易详情入库异常" + info.toString());
                }
            }
            if(ordersDetailResponse.getStatus().equals("ok")){
                response.setSuccess();
                response.setData(ordersDetailResponse.getData());
            }else {
                response.setError();
            }
        }catch (Exception e){
            log.info("查询订单出错");
            throw new DigitalException("查询订单出错");
        }
        return  response;
    }

    private List<HuobiBalanceCurrency> getBalanceListByAccountId(HuobiApiRestClient restClient,String accountId) throws DigitalException{
        //根据账户id查询账户余额
        List<HuobiBalanceCurrency> retrunBalance =  new ArrayList<>();
        BalanceResponse balanceResponse = restClient.balance(accountId);
        if(!balanceResponse.getStatus().equals("ok")){
            log.info("查询账户：" + accountId + "出错");
            return retrunBalance;
        }
        HuobiBalance balance = (HuobiBalance) balanceResponse.getData();
        List<HuobiBalanceCurrency> balanceCurrencyList = balance.getList();
        for(HuobiBalanceCurrency tempBalance : balanceCurrencyList){
            //返回有余额的账户
            if(!tempBalance.getBalance().equals(BigDecimal.ZERO)){
                retrunBalance.add(tempBalance);
            }
        }
        return retrunBalance;
    }

    @Scheduled(cron = "0 0,34 0/1 * * ? ")
    public void lastValueTimer(){
        //查找去用户表中所有用户
        List<UserInfo> infoList = userInfoMapper.getUserInfo(new UserInfo());
        if(infoList != null && infoList.size() > 0){
            for (UserInfo temp : infoList){
                ApiRequest request = new ApiRequest();
                request.setAccount(temp.getAccount());
                ApiResponse response = getUserInfo(request);
                Map<String,Object> returnMap = (Map<String,Object>)response.getData();
                //获取涨跌幅
                BigDecimal change = (BigDecimal)returnMap.get("change");
                if(change.compareTo(BigDecimal.valueOf(0.95)) < 0){
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setFrom("545286799@qq.com");
                    message.setTo("545286799@qq.com");
                    message.setSubject("主题：简单邮件");
                    message.setText("测试邮件内容");
                    mailSender.send(message);
                }

            }
        }
    }
}
