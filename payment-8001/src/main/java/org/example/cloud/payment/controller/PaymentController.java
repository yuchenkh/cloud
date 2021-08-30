package org.example.cloud.payment.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.cloud.payment.entity.Payment;
import org.example.cloud.payment.service.PaymentService;
import org.example.cloud.util.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private DiscoveryClient discoveryClient;

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

    /**
     * 服务发现？Aug 26
     * @return DiscoveryClient
     */
    @GetMapping("/discovery")
    public Object discovery() {
        // 获取当前 Eureka Server 中注册的所有的服务
        List<String> services = discoveryClient.getServices();
        log.info("----- 所有服务 -----");
        for (String service: services) {
            log.info("-----" + service + " -----");
        }
        // 获取支付服务的所有实例
        List<ServiceInstance> instances = discoveryClient.getInstances("payment-service");
        log.info("----- 支付服务的所有实例 -----");
        log.info("----- 服务名     主机     端口     URI -----");
        for (ServiceInstance instance: instances) {
            log.info("-----" + instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri() + " -----");
        }

        return discoveryClient;
    }
}
