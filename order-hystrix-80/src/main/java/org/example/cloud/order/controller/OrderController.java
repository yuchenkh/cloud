package org.example.cloud.order.controller;

import lombok.RequiredArgsConstructor;
import org.example.cloud.order.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测试 Hystrix 的服务降级效果的订单服务，调用支付服务的拥堵接口
 * @author yuchen
 * Sep 2, 2021
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/order")
public class OrderController {

    private final PaymentService paymentService;

    /**
     * 调用支付服务的流畅接口，获取支付信息
     * @param id ID
     * @return String
     */
    @GetMapping(path = "/get-payment")
    public String getPayment(@RequestParam(name = "id") int id) {
        return paymentService.ok(id);
    }

    /**
     * 调用支付服务的拥挤接口，获取支付信息
     * @param id ID
     * @return String
     */
    @GetMapping(path = "/get-payment-slow")
    public String getPaymentSlow(@RequestParam(name = "id") int id) {
        return paymentService.timeout(id);
    }
}
