package org.example.cloud.order.service;

import org.example.cloud.util.Payment;
import org.example.cloud.util.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author yuchen
 * Aug 31, 2021
 * 支付服务 (Feign)
 */
@Component
@FeignClient(value = "payment-service")
public interface PaymentService {

    /**
     * 根据 ID 查询订单信息（和 {@code payment-8001} 的 Controller 中的方法对应）
     * @param id 订单编号
     * @return 响应
     */
    @GetMapping("/payment/getById/{id}")
    Response<Payment> getById(@PathVariable(name = "id") Long id);
}
