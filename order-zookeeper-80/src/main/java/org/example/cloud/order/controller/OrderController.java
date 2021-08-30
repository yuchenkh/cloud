package org.example.cloud.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.cloud.util.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author yuchen
 * Aug 29, 2021
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {

    private static final String PAYMENT_URL = "http://payment-service";

    @Resource
    private RestTemplate restTemplate;

    /**
     * 调用 {@code payment-8004} 服务接口
     * @return 响应
     */
    @GetMapping("/go-to-payment")
    public Response<String> getPaymentInfo() {
        String result = restTemplate.getForObject(PAYMENT_URL + "/payment/zookeeper", String.class);
        return new Response<>(200, "查询订单信息成功", result);
    }
}
