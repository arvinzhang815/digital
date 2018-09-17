package com.yingwu.digital.service.impl;

import com.yingwu.digital.ApiClientFactory;
import com.yingwu.digital.base.ApiRequest;
import com.yingwu.digital.base.ApiResponse;
import com.yingwu.digital.base.DigitalException;
import com.yingwu.digital.bean.dto.okex.Deals;
import com.yingwu.digital.bean.dto.okex.Depth;
import com.yingwu.digital.bean.dto.okex.KLine;
import com.yingwu.digital.bean.dto.okex.Ticker;
import com.yingwu.digital.bean.resp.okex.OKExDealsResponse;
import com.yingwu.digital.bean.resp.okex.OKExDepthResponse;
import com.yingwu.digital.bean.resp.okex.OKExKLineResponse;
import com.yingwu.digital.bean.resp.okex.OKExTickerResponse;
import com.yingwu.digital.client.okex.OKExApiWSClient;
import com.yingwu.digital.dao.okex.OKExDealsMapper;
import com.yingwu.digital.dao.okex.OKExDepthMapper;
import com.yingwu.digital.dao.okex.OKExKLineMapper;
import com.yingwu.digital.dao.okex.OKExTickerMapper;
import com.yingwu.digital.service.OKExApiService;
import com.yingwu.digital.service.OKExWSEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketClient;

import java.math.BigDecimal;

@Service("eokexApiService")
public class OKExServiceImpl implements OKExApiService {
    private WebSocketClient client = null;

    @Autowired
    private OKExKLineMapper OKExKLineMapper;
    @Autowired
    private OKExDepthMapper OKExDepthMapper;
    @Autowired
    private OKExDealsMapper OKExDealsMapper;
    @Autowired
    private OKExTickerMapper OKExTickerMapper;

    private static Logger log = LoggerFactory.getLogger(OKExServiceImpl.class);
    private static ApiClientFactory factory = ApiClientFactory.newInstance();

    @Override
    public ApiResponse subKline(ApiRequest apiRequest) throws DigitalException {
        ApiResponse response = new ApiResponse();
        OKExApiWSClient client = factory.newOKExWSClient();
        try {
//            apiRequest = getSymbolBySub(apiRequest);
            log.info("开始订阅K线（KLine）时apiRequest：" + apiRequest.toString());
            client.kline(apiRequest.getSymbol(), apiRequest.getType(), new OKExWSEventHandler() {
                @Override
                public void handleKLine(OKExKLineResponse event) {
                    KLine kLine = new KLine();
                    BeanUtils.copyProperties(event,kLine);
                    log.info("K线数据"+kLine.toString());
                    int count = OKExKLineMapper.insert(kLine);
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

    private ApiRequest getSymbolBySub(ApiRequest apiRequest) {
        String[] beginIndex = apiRequest.getSub().split("\\.");
        apiRequest.setSymbol(beginIndex[1]);
        apiRequest.setType(beginIndex[3]);
        return apiRequest;
    }

    @Override
    public ApiResponse subDepth(ApiRequest apiRequest) throws DigitalException {
        ApiResponse response = new ApiResponse();
        OKExApiWSClient client = factory.newOKExWSClient();
        try {
            apiRequest = getSymbolBySub(apiRequest);
            log.info("开始订阅深度（depth）时apiRequest：" + apiRequest.toString());
            client.depth(apiRequest.getSymbol(), apiRequest.getType(), new OKExWSEventHandler() {
                @Override
                public void handleDepth(OKExDepthResponse event) {
                    Depth depth = new Depth();
                    BeanUtils.copyProperties(event.getDepthData(),depth);
                    log.info("深度数据"+depth.toString());
                    int count = OKExDepthMapper.insert(depth);
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
    public ApiResponse subDeals(ApiRequest apiRequest) throws DigitalException {
        ApiResponse response = new ApiResponse();
        OKExApiWSClient client = factory.newOKExWSClient();
        try {
            apiRequest = getSymbolBySub(apiRequest);
            log.info("开始订阅交易（deals)时apiRequest：" + apiRequest.toString());
            client.deals(apiRequest.getSymbol(), new OKExWSEventHandler() {
                @Override
                public void handleDeals(OKExDealsResponse event) {
                    Deals deals = new Deals();
                    String[] dealsList = event.getData().split(",");
                    deals.setTradeId(dealsList[0]);
                    deals.setPrice(new BigDecimal(dealsList[1]));
                    deals.setAmount(new BigDecimal(dealsList[2]));
                    deals.setTradeTime(dealsList[3]);
                    deals.setDirection(dealsList[4]);
                    log.info("交易数据"+deals.toString());
                    int count = OKExDealsMapper.insert(deals);
                    if(count < 1){
                        log.info("交易数据入库异常" + event.toString());
                        throw new DigitalException("交易数据入库异常" + event.toString());
                    }
                    response.setSuccess();
                    response.setData(event.toString());
                }
            });
        } catch (Exception e) {
            log.info("订阅交易异常" + e.toString());
            response.setError();
            response.setData(e.toString());
        }
        return response;
    }

    @Override
    public ApiResponse subTicker(ApiRequest apiRequest) throws DigitalException {
        ApiResponse response = new ApiResponse();
        OKExApiWSClient client = factory.newOKExWSClient();
        try {
            apiRequest = getSymbolBySub(apiRequest);
            log.info("开始订阅行情（Ticker）时apiRequest：" + apiRequest.toString());
            client.ticker(apiRequest.getSymbol(), new OKExWSEventHandler() {

                @Override
                public void handleTicker(OKExTickerResponse event) {

                    Ticker ticker = new Ticker();
                    BeanUtils.copyProperties(event.getTickerData(),ticker);
//                    if(event.getDetails() != null && event.getDetails().size() > 0){
//                        for(HuobiTradeDetail tmp : event.getDetails()){
//                            BeanUtils.copyProperties(tmp,tradeDetail);
//                            tradeDetail.setTradeId(tmp.getId());
//                            tradeDetail.setTradeTs(tmp.getTs());
//                            log.info("交易详情数据"+tradeDetail.toString());
                            int count = OKExTickerMapper.insert(ticker);
                            if(count < 1){
                                log.info("行情信息入库异常" + event.toString());
                                throw new DigitalException("行情信息入库异常" + event.toString());
                            }
//                        }
//                    }
                    response.setSuccess();
                    response.setData(event.toString());
                }
            });
        } catch (Exception e) {
            log.info("订阅行情信息异常" + e.toString());
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


}
