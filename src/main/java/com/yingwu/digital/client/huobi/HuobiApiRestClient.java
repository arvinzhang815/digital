package com.yingwu.digital.client.huobi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.yingwu.digital.base.DigitalConst;
import com.yingwu.digital.base.DigitalException;
import com.yingwu.digital.bean.HuobiOrderMatchResult;
import com.yingwu.digital.bean.dto.huobi.Account;
import com.yingwu.digital.bean.dto.huobi.HuobiBalance;
import com.yingwu.digital.bean.dto.huobi.HuobiEntrustInfo;
import com.yingwu.digital.bean.dto.huobi.Symbol;
import com.yingwu.digital.bean.resp.ApiResponse;
import com.yingwu.digital.bean.resp.huobi.*;
import com.yingwu.digital.util.ApiSignature;
import com.yingwu.digital.util.ApiUtil;
import okhttp3.*;
import org.springframework.beans.BeanUtils;

import javax.xml.bind.DatatypeConverter;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.MatchResult;
import java.util.stream.Collectors;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/17
 **/
public class HuobiApiRestClient {

    static final int CONN_TIMEOUT = 5;
    static final int READ_TIMEOUT = 5;
    static final int WRITE_TIMEOUT = 5;


//    static final String API_URL = "https://api.huobi.pro";
    static final String API_URL = DigitalConst.MARKET_URL_H;
    static final String API_HOST = getHost();

    static final MediaType JSON = MediaType.parse("application/json");
    static final OkHttpClient client = createOkHttpClient();

    final String accessKeyId;
    final String accessKeySecret;
    final String assetPassword;

    /**
     * 创建一个ApiClient实例
     *
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     */
    public HuobiApiRestClient(String accessKeyId, String accessKeySecret) {
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.assetPassword = null;
    }

    /**
     * 创建一个HuobiApiRestClientt实例
     *
     * @param accessKeyId     AccessKeyId
     * @param accessKeySecret AccessKeySecret
     * @param assetPassword   AssetPassword
     */
    public HuobiApiRestClient(String accessKeyId, String accessKeySecret, String assetPassword) {
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.assetPassword = assetPassword;
    }

    /**
     * GET /market/trade 获取 Trade Detail 数据
     *
     * @param symbol
     * @return
     */
    public TradeResponse trade(String symbol) {
        HashMap map = new HashMap();
        map.put("symbol", symbol);
        TradeResponse resp = get("/market/trade", map, new TypeReference<TradeResponse>() {
        });
        return resp;
    }
    /**
     * 查询交易对
     *
     * @return List of symbols.
     */
    public List<Symbol> getSymbols() {
        ApiResponse<List<Symbol>> resp =
                get("/v1/common/symbols", null, new TypeReference<ApiResponse<List<Symbol>>>() {
                });
        return resp.checkAndReturn();
    }

    /**
     * 查询所有账户信息
     *
     * @return List of accounts.
     */
    public List<Account> getAccounts() {
        ApiResponse<List<Account>> resp =
                get("/v1/account/accounts", null, new TypeReference<ApiResponse<List<Account>>>() {
                });
        return resp.checkAndReturn();
    }

    /**
     * GET /v1/common/symbols 查询系统支持的所有交易对及精度
     *
     * @param symbol
     * @returnx
     */
    public SymbolsResponse symbols(String symbol) {
        HashMap map = new HashMap();
        map.put("symbol", symbol);
        SymbolsResponse resp = get("/v1/common/symbols", map, new TypeReference<SymbolsResponse<Symbol>>() {
        });
        return resp;
    }

    /**
     * GET /v1/common/currencys 查询系统支持的所有币种
     *
//     * @param symbol
     * @return
     */
    public CurrencysResponse currencys() {
        HashMap map = new HashMap();
//        map.put("symbol", symbol);
        CurrencysResponse resp = get("/v1/common/currencys", map, new TypeReference<CurrencysResponse>() {
        });
        return resp;
    }

    /**
     * GET /v1/common/timestamp 查询系统当前时间
     *
     * @return
     */
    public TimestampResponse timestamp() {
        TimestampResponse resp = get("/v1/common/timestamp", null, new TypeReference<TimestampResponse>() {
        });
        return resp;
    }

    /**
     * GET /v1/account/accounts 查询当前用户的所有账户(即account-id)
     *
     * @return
     */
    public AccountsResponse accounts() {
        AccountsResponse resp = get("/v1/account/accounts", null, new TypeReference<AccountsResponse<List<Account>>>() {
        });
        return resp;
    }

    /**
     * GET /v1/account/accounts/{account-id}/balance 查询指定账户的余额
     *
     * @param accountId
     * @return
     */
    public BalanceResponse balance(String accountId) {
        BalanceResponse resp = get("/v1/account/accounts/" + accountId + "/balance", null, new TypeReference<BalanceResponse<HuobiBalance>>() {
        });
        return resp;
    }



    /**
     * GET /v1/order/orders/{order-id}/matchresults 查询某个订单的成交明细
     *
     * @param orderId
     * @return
     */
    public MatchresultsOrdersDetailResponse matchresults(String orderId) {
        MatchresultsOrdersDetailResponse resp = get("/v1/order/orders/" + orderId + "/matchresults", null, new TypeReference<MatchresultsOrdersDetailResponse>() {
        });
        return resp;
    }
    public MatchresultsOrdersDetailResponse matchresult() {
        HashMap map = new HashMap();
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date time=cal.getTime();
        String startDate = new SimpleDateFormat("yyyy-MM-dd").format(time);
        String endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString();
        map.put("start-date", startDate);
        map.put("end-date", endDate);

        MatchresultsOrdersDetailResponse resp = get("/v1/order/matchresults", map, new TypeReference<MatchresultsOrdersDetailResponse>() {
        });

        List<Map> respData = (List<Map>) resp.getData();
        List<HuobiOrderMatchResult> resultList = new ArrayList<>();

        for(Map temp : respData){
            HuobiOrderMatchResult results = JSONObject.toJavaObject((JSON) JSONObject.toJSON(temp),HuobiOrderMatchResult.class);
            resultList.add(results);
        }
        resp.setData(resultList);
        return resp;
    }

//    public IntrustDetailResponse intrustOrdersDetail(IntrustOrdersDetailRequest req) {
//        HashMap map = new HashMap();
//        map.put("symbol", req.symbol);
//        map.put("states", req.states);
//        if (req.startDate != null) {
//            map.put("startDate", req.startDate);
//        }
//        if (req.startDate != null) {
//            map.put("start-date", req.startDate);
//        }
//        if (req.endDate != null) {
//            map.put("end-date", req.endDate);
//        }
//        if (req.types != null) {
//            map.put("types", req.types);
//        }
//        if (req.from != null) {
//            map.put("from", req.from);
//        }
//        if (req.direct != null) {
//            map.put("direct", req.direct);
//        }
//        if (req.size != null) {
//            map.put("size", req.size);
//        }
//        IntrustDetailResponse resp = get("/v1/order/orders/", map, new TypeReference<IntrustDetailResponse<List<IntrustDetail>>>() {
//        });
//        return resp;
//    }

//  public IntrustDetailResponse getALlOrdersDetail(String orderId) {
//    IntrustDetailResponse resp = get("/v1/order/orders/"+orderId, null,new TypeReference<IntrustDetailResponse>() {});
//    return resp;
//  }


    // send a GET request.
    <T> T get(String uri, Map<String, String> params, TypeReference<T> ref) {
        if (params == null) {
            params = new HashMap<>();
        }
        return call("GET", uri, null, params, ref);
    }

    // send a POST request.
    <T> T post(String uri, Object object, TypeReference<T> ref) {
        return call("POST", uri, object, new HashMap<String, String>(), ref);
    }

    // call api by endpoint.
    <T> T call(String method, String uri, Object object, Map<String, String> params,
               TypeReference<T> ref) {
        ApiSignature sign = new ApiSignature();
        sign.createSignature(this.accessKeyId, this.accessKeySecret, method, API_HOST, uri, params);
        try {
            Request.Builder builder = null;
            if ("POST".equals(method)) {
                RequestBody body = RequestBody.create(JSON, ApiUtil.writeValue(object));
                builder = new Request.Builder().url(API_URL + uri + "?" + toQueryString(params)).post(body);
            } else {
                builder = new Request.Builder().url(API_URL + uri + "?" + toQueryString(params)).get();
            }
            if (this.assetPassword != null) {
                builder.addHeader("AuthData", authData());
            }
            Request request = builder.build();
            Response response = client.newCall(request).execute();
            String s = response.body().string();
            return ApiUtil.readValue(s, ref);
        } catch (IOException e) {
            throw new DigitalException(e);
        }
    }

    String authData() {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update(this.assetPassword.getBytes(StandardCharsets.UTF_8));
        md.update("hello, moto".getBytes(StandardCharsets.UTF_8));
        Map<String, String> map = new HashMap<>();
        map.put("assetPwd", DatatypeConverter.printHexBinary(md.digest()).toLowerCase());
        try {
            return ApiSignature.urlEncode(ApiUtil.writeValue(map));
        } catch (IOException e) {
            throw new RuntimeException("Get json failed: " + e.getMessage());
        }
    }

    // Encode as "a=1&b=%20&c=&d=AAA"
    String toQueryString(Map<String, String> params) {
        return String.join("&", params.entrySet().stream().map((entry) -> {
            return entry.getKey() + "=" + ApiSignature.urlEncode(entry.getValue());
        }).collect(Collectors.toList()));
    }

    // create OkHttpClient:
    static OkHttpClient createOkHttpClient() {
        return new OkHttpClient.Builder().connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS).writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    static String getHost() {
        String host = null;
        try {
            host = new URL(API_URL).getHost();
        } catch (MalformedURLException e) {
            System.err.println("parse API_URL error,system exit!,please check API_URL:" + API_URL);
            System.exit(0);
        }
        return host;
    }

}
