package org.example.cloud.payment.controller;

import org.example.cloud.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yuchen
 * Sep 2, 2021
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService service;

    @GetMapping(path = "/smooth")
    public String ok(@RequestParam(value = "id") int id) {
        return service.smooth(id);
    }

    @GetMapping("/lag")
    public String timeout(@RequestParam(value = "id") int id) {
        return service.lag(id);
    }

    // 用于测试断路器 Circuit Breaker（服务熔断）
    @GetMapping(path = "/circuit")
    public String circuit(@RequestParam(value = "id") int id) {
        return service.circuit(id);
    }
}
