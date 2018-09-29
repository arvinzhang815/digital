package com.yingwu.digital.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yingwu.digital.ApiClientFactory;
import com.yingwu.digital.base.ApiResponse;
import com.yingwu.digital.base.DigitalException;
import com.yingwu.digital.bean.dto.zb.ZBDepth;
import com.yingwu.digital.bean.dto.zb.ZBKLine;
import com.yingwu.digital.bean.dto.zb.ZBTicker;
import com.yingwu.digital.bean.dto.zb.ZBTradeDetail;
import com.yingwu.digital.bean.request.zb.ZBRestApiRequest;
import com.yingwu.digital.client.zb.ZBRestClient;
import com.yingwu.digital.mapper.zb.ZBDepthMapper;
import com.yingwu.digital.mapper.zb.ZBKLineMapper;
import com.yingwu.digital.mapper.zb.ZBTickerMapper;
import com.yingwu.digital.mapper.zb.ZBTradeDetailMapper;
import com.yingwu.digital.service.ZBApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.applet.Main;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/27
 **/
@Service
public class ZBApiServiceImpl implements ZBApiService {
    //日志
    private static Logger log = LoggerFactory.getLogger(ZBApiServiceImpl.class);

    //工厂
    private static ApiClientFactory apiClientFactory = new ApiClientFactory();

    @Autowired
    private ZBKLineMapper zbkLineMapper;
    @Autowired
    private ZBDepthMapper zbDepthMapper;
    @Autowired
    private ZBTickerMapper zbTickerMapper;
    @Autowired
    private ZBTradeDetailMapper zBTradeDetailMapper;

    @Override
    public ApiResponse subKline(ZBRestApiRequest request) throws DigitalException {
        ApiResponse response = new ApiResponse();
        try {
            ZBRestClient client = apiClientFactory.newZBRestClient(request.getApiKey(), request.getSecretKey());
            String result = client.kline(request.getSymbols(), request.getTimes());
            JSONObject jsonObject = JSONObject.parseObject(result);
            ZBKLine zbkLine = new ZBKLine();
            zbkLine.setMoneytype((String) jsonObject.get("moneyType"));
            zbkLine.setSymbol((String) jsonObject.get("symbol"));
            zbkLine.setPeriod(request.getTimes());
            JSONArray array = jsonObject.getJSONArray("data");
            List<ZBKLine> lists = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                JSONArray jsonArray = array.getJSONArray(i);
                zbkLine.setTs(jsonArray.getString(0));
                zbkLine.setOpen((BigDecimal) jsonArray.get(1));
                zbkLine.setHigh((BigDecimal) jsonArray.get(2));
                zbkLine.setLow((BigDecimal) jsonArray.get(3));
                zbkLine.setClose((BigDecimal) jsonArray.get(4));
                zbkLine.setAmount((BigDecimal) jsonArray.get(5));
                log.info("新增ZBKline入库，zbkline:" + zbkLine.toString());
                lists.add(zbkLine);
                int count = zbkLineMapper.insert(zbkLine);
                if (count < 1) {
                    log.info("新增ZBKline入库出错");
                }
            }
            response.setSuccess();
            response.setData(lists);
        } catch (DigitalException e) {
            log.error("查询zbkine出错");
            throw new DigitalException(e);
        }
        return response;
    }

    public static void main(String[] args) {
        String result = "[{\n" +
                "\t\t\"amount\": 0.541,\n" +
                "\t\t\"date\": 1472711925,\n" +
                "\t\t\"price\": 81.87,\n" +
                "\t\t\"tid\": 16497097,\n" +
                "\t\t\"trade_type\": \"ask\",\n" +
                "\t\t\"type\": \"sell\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"amount\": 0.541,\n" +
                "\t\t\"date\": 1472711925,\n" +
                "\t\t\"price\": 81.87,\n" +
                "\t\t\"tid\": 16497097,\n" +
                "\t\t\"trade_type\": \"ask\",\n" +
                "\t\t\"type\": \"sell\"\n" +
                "\t}\n" +
                "]";
        ZBTradeDetail zbTicker = new ZBTradeDetail();
//        JSONObject jsonObject = JSONObject.parseObject(result);
        List<ZBTradeDetail>  lsit = JSONObject.parseArray(result,ZBTradeDetail.class);
//        zbTicker = JSONObject.parseObject(jsonObject.getString("ticker"), ZBTicker.class);
        System.out.println(lsit);
    }

    @Override
    public ApiResponse subDepth(ZBRestApiRequest request) throws DigitalException {
        ApiResponse response = new ApiResponse();
        try {
            ZBRestClient client = apiClientFactory.newZBRestClient(request.getApiKey(), request.getSecretKey());
            String result = client.depth(request.getSymbols(), request.getMerge(), request.getSize());
            ZBDepth zbDepth = new ZBDepth();
            zbDepth = JSONObject.parseObject(result, ZBDepth.class);
            zbDepth.setSymbol(request.getSymbols());
            log.info("新增zbdepth入库，zbdepth:" + zbDepth.toString());
            int count = zbDepthMapper.insert(zbDepth);
            if (count < 1) {
                log.info("新增zbdepth入库出错");
            }
            response.setSuccess();
            response.setData(zbDepth);
        } catch (DigitalException e) {
            log.error("查询zbdepth出错");
            throw new DigitalException(e);
        }
        return response;
    }

    @Override
    public ApiResponse subTicker(ZBRestApiRequest request) throws DigitalException {
        ApiResponse response = new ApiResponse();
        try {
            ZBRestClient client = apiClientFactory.newZBRestClient(request.getApiKey(), request.getSecretKey());
            String result = client.ticker(request.getSymbols());
            ZBTicker zbTicker = new ZBTicker();
            JSONObject jsonObject = JSONObject.parseObject(result);
            zbTicker = JSONObject.parseObject(jsonObject.getString("ticker"), ZBTicker.class);
            zbTicker.setDate(jsonObject.getString("date"));
            zbTicker.setSymbol(request.getSymbols());
            log.info("新增zbTicker入库，zbTicker:" + zbTicker.toString());
            int count = zbTickerMapper.insert(zbTicker);
            if (count < 1) {
                log.info("新增zbTicker入库出错");
            }
            response.setSuccess();
            response.setData(zbTicker);
        } catch (DigitalException e) {
            log.error("查询zbTicker出错");
            throw new DigitalException(e);
        }
        return response;
    }
    @Override
    public ApiResponse subDeals(ZBRestApiRequest request) throws DigitalException {
        ApiResponse response = new ApiResponse();
        try {
            ZBRestClient client = apiClientFactory.newZBRestClient(request.getApiKey(), request.getSecretKey());
            String result = client.trades(request.getSymbols(), request.getOrderId());
            ZBTradeDetail zbTradeDetail = new ZBTradeDetail();
//        JSONObject jsonObject = JSONObject.parseObject(result);
            List<ZBTradeDetail>  zbTradeDetails = JSONObject.parseArray(result,ZBTradeDetail.class);
            for(ZBTradeDetail temp : zbTradeDetails){
                temp.setSymbol(request.getSymbols());
                log.info("新增zbTradeDetails入库，zbTradeDetails:" + temp.toString());
                int count = zBTradeDetailMapper.insert(temp);
                if (count < 1) {
                    log.info("新增zbTradeDetails入库出错");
                }
            }
            response.setSuccess();
            response.setData(zbTradeDetails);
        } catch (DigitalException e) {
            log.error("查询zbTradeDetails出错");
            throw new DigitalException(e);
        }
        return response;
    }

    @Override
    public ApiResponse requestMsg(String json) throws DigitalException {
        return null;
    }
}
