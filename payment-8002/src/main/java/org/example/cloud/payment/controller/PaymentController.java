package org.example.cloud.payment.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.cloud.payment.entity.Payment;
import org.example.cloud.payment.service.PaymentService;
import org.example.cloud.util.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yuchen
 * Aug 20, 2021
 */
@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String SERVER_PORT;

    @PostMapping("/create")
    public Response<Integer> create(@RequestBody Payment payment) {
        int value = paymentService.create(payment);
        log.info("----- 插入结果： " + value + " -----");
        if (value > 0) {
            return new Response<>(200, "创建支付订单成功，服务端口：" + SERVER_PORT, value);
        } else {
            return new Response<>(400, "创建支付订单失败，服务端口：" + SERVER_PORT, value);
        }
    }

    @GetMapping("/getById/{id}")
    public Response<Payment> getById(@PathVariable Long id) {
        Payment p = paymentService.getById(id);
        log.info("----- 查询结果：" + p + " -----");
        if (p == null) {
            return new Response<>(400, id + "对应的订单不存在，服务端口：" + SERVER_PORT, null);
        } else {
            return new Response<>(200, "查询成功，服务端口：" + SERVER_PORT, p);
        }
    }
}
