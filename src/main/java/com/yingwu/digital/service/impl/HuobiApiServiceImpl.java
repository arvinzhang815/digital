package com.yingwu.digital.service.impl;

import com.yingwu.digital.HuobiApiClientFactory;
import com.yingwu.digital.HuobiApiWSClient;
import com.yingwu.digital.base.ApiRequest;
import com.yingwu.digital.base.ApiResponse;
import com.yingwu.digital.base.DigitalException;
import com.yingwu.digital.bean.DTO.Depth;
import com.yingwu.digital.bean.DTO.KLine;
import com.yingwu.digital.bean.DTO.TradeDetail;
import com.yingwu.digital.bean.HuobiKLineData;
import com.yingwu.digital.bean.HuobiTradeDetail;
import com.yingwu.digital.bean.ws.HuobiWSDepthEvent;
import com.yingwu.digital.bean.ws.HuobiWSKLineEvent;
import com.yingwu.digital.bean.ws.HuobiWSTradeDetailEvent;
import com.yingwu.digital.dao.DepthMapper;
import com.yingwu.digital.dao.KLineMapper;
import com.yingwu.digital.dao.TradeDetailMapper;
import com.yingwu.digital.service.HuobiApiService;
import com.yingwu.digital.service.HuobiWSEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketClient;

@Service("ehoubiApiService")
public class HuobiApiServiceImpl implements HuobiApiService {
    private WebSocketClient client = null;

    @Autowired
    private KLineMapper kLineMapper;
    @Autowired
    private DepthMapper depthMapper;
    @Autowired
    private TradeDetailMapper tradeDetailMapper;

    private static Logger log = LoggerFactory.getLogger(HuobiApiServiceImpl.class);
    private static HuobiApiClientFactory factory = HuobiApiClientFactory.newInstance();

    @Override
    public ApiResponse subKline(ApiRequest apiRequest) throws DigitalException {
        ApiResponse response = new ApiResponse();
        HuobiApiWSClient client = factory.newWSClient();
        try {
            apiRequest = getSymbolBySub(apiRequest);
            log.info("开始订阅时apiRequest：" + apiRequest.toString());
            client.kline(apiRequest.getSymbol(), apiRequest.getType(), new HuobiWSEventHandler() {
                @Override
                public void handleKLine(HuobiWSKLineEvent event) {
                    KLine kLine = new KLine();
                    BeanUtils.copyProperties(event,kLine);
                    HuobiKLineData data = event.getData();
                    BeanUtils.copyProperties(data,kLine);
                    kLine.setKlinId(data.getId());
                    log.info("K线数据"+kLine.toString());
                    int count = kLineMapper.insert(kLine);
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
        HuobiApiWSClient client = factory.newWSClient();
        try {
            apiRequest = getSymbolBySub(apiRequest);
            log.info("开始订阅时apiRequest：" + apiRequest.toString());
            client.depth(apiRequest.getSymbol(), apiRequest.getType(), new HuobiWSEventHandler() {
                @Override
                public void handleDepth(HuobiWSDepthEvent event) {
                    Depth depth = new Depth();
                    BeanUtils.copyProperties(event,depth);
                    depth.setAsks(event.getAsks().toString());
                    depth.setBids(event.getBids().toString());
                    log.info("深度数据"+depth.toString());
                    int count = depthMapper.insert(depth);
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
    public ApiResponse subTradeDetail(ApiRequest apiRequest) throws DigitalException {
        ApiResponse response = new ApiResponse();
        HuobiApiWSClient client = factory.newWSClient();
        try {
            apiRequest = getSymbolBySub(apiRequest);
            log.info("开始订阅时apiRequest：" + apiRequest.toString());
            client.tradeDetail(apiRequest.getSymbol(), new HuobiWSEventHandler() {

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
                            int count = tradeDetailMapper.insert(tradeDetail);
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


}
