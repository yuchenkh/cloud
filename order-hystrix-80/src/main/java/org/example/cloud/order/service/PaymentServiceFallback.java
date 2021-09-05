package org.example.cloud.order.service;

import org.springframework.stereotype.Component;

/**
 * Fallback class for the {@code PaymentService} Feign client interface.
 * @author yuchen
 * Sep 5, 2021
 */
@Component
public class PaymentServiceFallback implements PaymentService{

    @Override
    public String ok(int id) {
        return "支付服务目前无法访问，您看到的是订单服务的回退方法";
    }

    @Override
    public String timeout(int id) {
        return "支付服务目前无法访问，您看到的是订单服务的回退方法";
    }
}
