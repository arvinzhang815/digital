package com.yingwu.digital;

import com.yingwu.digital.base.ApiResponse;
import com.yingwu.digital.bean.request.ApiRequest;
import com.yingwu.digital.mapper.huobi.HuobiEntrustInfoMapper;
import com.yingwu.digital.service.HuobiApiService;
import com.yingwu.digital.service.impl.HuobiApiServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DigitalApplicationTests {

    @Autowired
    private HuobiEntrustInfoMapper huobiEntrustInfoMapper;
    @Autowired
    @Qualifier("ehoubiApiService")
    private HuobiApiServiceImpl huobiApiService;
    private String API_KEY = "7d8219aa-09bfe59d-1f9089a1-28430";

    private String API_SECRET = "90f6a25d-8421df14-816d2a20-379a9";

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void sendSimpleMail() throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("545286799@qq.com");
        message.setTo("545286799@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");

        mailSender.send(message);
    }

    @Test
    public void contextLoads() {

//            HuobiApiRestClient client = new HuobiApiRestClient(API_KEY, API_SECRET);
//            // get symbol list:
//            MatchresultsOrdersDetailResponse ordersDetailResponse = client.matchresult();
////        log.info("订单查询返回值为：" + ordersDetailResponse.toString());
//            List<HuobiOrderMatchResult> responseList = (List<HuobiOrderMatchResult>)ordersDetailResponse.EntrustInfogetData();
//            for(HuobiOrderMatchResult temp : responseList){
//                HuobiEntrustInfo info = new HuobiEntrustInfo();
//                BeanUtils.copyProperties(temp,info);
//                //入库
//                int count = huobiEntrustInfoMapper.insert(info);
//                if(count < 1){
////                log.info("交易详情入库异常" + info.toString());
//                    throw new DigitalException("交易详情入库异常" + info.toString());
//                }
//            }
        ApiRequest request = new ApiRequest();
        request.setAccount("123456");
        huobiApiService.lastValueTimer();

        }


}
