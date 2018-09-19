package com.yingwu.digital;

import com.yingwu.digital.bean.dto.huobi.Account;
import com.yingwu.digital.bean.dto.huobi.HuobiBalance;
import com.yingwu.digital.bean.resp.huobi.*;
import com.yingwu.digital.client.huobi.HuobiApiRestClient;
import com.yingwu.digital.util.ApiUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/17
 **/
public class RestFullTest {
    private String API_KEY = "7d8219aa-09bfe59d-1f9089a1-28430";

    private String API_SECRET = "90f6a25d-8421df14-816d2a20-379a9";
    @Test
    public void restFullTest(){
        HuobiApiRestClient client = new HuobiApiRestClient(API_KEY, API_SECRET);
        // get symbol list:
        List<Account> response = client.getAccounts();
        MatchresultsOrdersDetailResponse responsew = client.matchresult();
        //Account{id='4604531', type='spot', state='working', userid='null'}Account{id='4604531', type='spot', state='working', userid='null'}
        print(response);
        print(responsew);

        //获取 K 线
        //------------------------------------------------------ kline -------------------------------------------------------
//        KlineResponse kline = client.kline("btcusdt", "5min", "100");
//        print(kline);
//
//        //------------------------------------------------------ merged -------------------------------------------------------
//
//        MergedResponse merged = client.merged("ethusdt");
//        print(merged);
//
//        //------------------------------------------------------ depth -------------------------------------------------------
//
//        DepthRequest depthRequest = new DepthRequest();
//        depthRequest.setSymbol("btcusdt");
//        depthRequest.setType("step0");
//        DepthResponse depth = client.depth(depthRequest);
//        print(depth);
//
//        //------------------------------------------------------ trade -------------------------------------------------------
//        TradeResponse trade = client.trade("ethusdt");
//        print(trade);
//
//        //------------------------------------------------------ historyTrade -------------------------------------------------------
//        HistoryTradeResponse historyTrade = client.historyTrade("ethusdt", "20");
//        print(historyTrade);
//
//        //------------------------------------------------------ historyTrade -------------------------------------------------------
//        DetailResponse detailTrade = client.detail("ethusdt");
//        print(detailTrade);
//
//        //------------------------------------------------------ symbols -------------------------------------------------------
//        SymbolsResponse symbols = client.symbols("btcusdt");
//        print(symbols);
//
//        //------------------------------------------------------ Currencys -------------------------------------------------------
//        CurrencysResponse currencys = client.currencys("btcusdt");
//        print(currencys);
//
//        //------------------------------------------------------ Currencys -------------------------------------------------------
//        TimestampResponse timestamp = client.timestamp();
//        print(timestamp);
//
//        //------------------------------------------------------ accounts -------------------------------------------------------
//        AccountsResponse accounts = client.accounts();
//        print(accounts);
//
//        //------------------------------------------------------ balance -------------------------------------------------------
//        List<Accounts> list = (List<Accounts>) accounts.getData();
//        BalanceResponse balance = client.balance(String.valueOf(list.get(0).getId()));
//        BalanceResponse balance2 = client.balance(String.valueOf(list.get(1).getId()));
//
//        print(balance); //spot
//        print(balance2);//otc
//
//        Long orderId = 123L;
//        if (!list.isEmpty()) {
//            // find account id:
//            Accounts account = list.get(0);
//            long accountId = account.getId();
//            // create order:
//            CreateOrderRequest createOrderReq = new CreateOrderRequest();
//            createOrderReq.accountId = String.valueOf(accountId);
//            createOrderReq.amount = "0.02";
//            createOrderReq.price = "0.1";
//            createOrderReq.symbol = "eosusdt";
//            createOrderReq.type = CreateOrderRequest.OrderType.BUY_LIMIT;
//            createOrderReq.source = "api";
//
//            //------------------------------------------------------ 创建订单  -------------------------------------------------------
//            orderId = client.createOrder(createOrderReq);
//            print(orderId);
//            // place order:
//
//            //------------------------------------------------------ 执行订单  -------------------------------------------------------
//            String r = client.placeOrder(orderId);
//            print(r);
//        }
//
//        //------------------------------------------------------ submitcancel 取消订单 -------------------------------------------------------
//
////    SubmitcancelResponse submitcancel = client.submitcancel(orderId.toString());
////    print(submitcancel);
//
//        //------------------------------------------------------ submitcancel 批量取消订单-------------------------------------------------------
////    String[] orderList = {"727554767","727554766",""};
////    String[] orderList = {String.valueOf(orderId)};
//        List orderList = new ArrayList();
//        orderList.add(orderId);
//        BatchcancelResponse submitcancels = client.submitcancels(orderList);
//        print(submitcancels);
//
//        //------------------------------------------------------ ordersDetail 订单详情 -------------------------------------------------------
//        OrdersDetailResponse ordersDetail = client.ordersDetail(String.valueOf(orderId));
//        print(ordersDetail);
//
//        //------------------------------------------------------ ordersDetail 已经成交的订单详情 -------------------------------------------------------
////    String.valueOf(orderId)
//        MatchresultsOrdersDetailResponse matchresults = client.matchresults("714746923");
//        print(ordersDetail);
//
//        //------------------------------------------------------ ordersDetail 已经成交的订单详情 -------------------------------------------------------
////    String.valueOf(orderId)
//        IntrustOrdersDetailRequest req = new IntrustOrdersDetailRequest();
//        req.symbol = "btcusdt";
//        req.types = IntrustOrdersDetailRequest.OrderType.BUY_LIMIT;
////    req.startDate = "2018-01-01";
////    req.endDate = "2018-01-14";
//        req.states = IntrustOrdersDetailRequest.OrderStates.FILLED;
////    req.from = "";
////    req.direct = "";
////    req.size = "";
//
//
////    public String symbol;	   //true	string	交易对		btcusdt, bccbtc, rcneth ...
////    public String types;	   //false	string	查询的订单类型组合，使用','分割		buy-market：市价买, sell-market：市价卖, buy-limit：限价买, sell-limit：限价卖
////    public String startDate;   //false	string	查询开始日期, 日期格式yyyy-mm-dd
////    public String endDate;	   //false	string	查询结束日期, 日期格式yyyy-mm-dd
////    public String states;	   //true	string	查询的订单状态组合，使用','分割		pre-submitted 准备提交, submitted 已提交, partial-filled 部分成交,
////    // partial-canceled 部分成交撤销, filled 完全成交, canceled 已撤销
////    public String from;	       //false	string	查询起始 ID
////    public String direct;	   //false	string	查询方向		prev 向前，next 向后
////    public String size;	       //false	string	查询记录大小
//
//
//        //------------------------------------------------------ order 查询当前委托、历史委托 -------------------------------------------------------
//
//        IntrustDetailResponse intrustDetail = client.intrustOrdersDetail(req);
//        print(intrustDetail);


//    // get accounts:
//    List<Account> accounts1 = client.getAccounts();
//    print(accounts1);

    }

    static void print(Object obj) {
        try {
            System.out.println(ApiUtil.writeValue(obj));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
