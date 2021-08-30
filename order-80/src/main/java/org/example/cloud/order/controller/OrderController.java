package org.example.cloud.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.cloud.order.entity.Payment;
import org.example.cloud.util.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 消费者端订单管理
 * @author yuchen
 * Aug 25, 2021
 */
@RestController
@Slf4j
@RequestMapping("/order")
public class OrderController {

    public static final String PAYMENT_URL = "http://payment-service";       // 支付服务 URL，支付服务开启集群后使用服务名称访问

    @Resource
    private RestTemplate restTemplate;      // 依赖注入（需先在容器中声明该 bean）

    /**
     * 用户订单发起支付
     * @param payment 支付订单实体（自定义的订单号）
     * @return 支付信息的响应
     */
    @GetMapping("/pay")
    public Response pay(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, Response.class);
    }

    /**
     * 查看订单的支付信息
     * @param id 支付流水号
     * @return 支付信息的响应
     */
    @GetMapping("/payment_info/{id}")
    public Response getPaymentInfo(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/getById/" + id, Response.class);
    }
}
