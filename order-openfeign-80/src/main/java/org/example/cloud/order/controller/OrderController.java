package org.example.cloud.order.controller;

import org.example.cloud.order.service.PaymentService;
import org.example.cloud.util.Payment;
import org.example.cloud.util.Response;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单服务 Controller
 * @author yuchen
 * Aug 31, 2021
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    PaymentService paymentService;      // 项目内部定义的一个支付服务接口，本质上是对外部服务的 Feign 调用

    /**
     * 查看支付信息（以 Feign 形式调用外部服务）
     * @param id 支付编号
     * @return 响应
     */
    @GetMapping(path = "/payment-info")
    public Response<Payment> getPaymentInfo(@RequestParam(name = "id") Long id) {
        return paymentService.getById(id);
    }
}
