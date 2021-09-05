package org.example.cloud.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.TimeUnit;

/**
 * Feign Client
 * @author yuchen
 * Sep 2, 2021
 */
@Service
@FeignClient(value = "payment-hystrix-service", fallback = PaymentServiceFallback.class)
public interface PaymentService {

    // 流畅的方法
    @GetMapping(path = "/payment/smooth")
    String ok(@RequestParam(name = "id") int id);

    // 拥堵的方法
    @GetMapping(path = "/payment/lag")
    String timeout(@RequestParam(name = "id") int id);
}
