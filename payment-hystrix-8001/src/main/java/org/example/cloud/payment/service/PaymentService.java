package org.example.cloud.payment.service;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author yuchen
 * Sep 2, 2021
 */
@Service
public class PaymentService {

    // 模拟常规的服务接口
    public String smooth(int id) {
        return "线程池：" + Thread.currentThread().getName() + ". 通畅端点, ID: " + id;
    }

    // 模拟逻辑复杂或者网络拥堵的服务接口
    @HystrixCommand(fallbackMethod = "lagHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String lag(int id) {
        int sleepTime = 3;
        try {
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + ". 拥塞端点, ID: " + id;
    }

    public String lagHandler(int id) {
        return "线程池：" + Thread.currentThread().getName() + ". 8001 端口系统繁忙, ID: " + id;
    }

    // 用于测试断路器 Circuit Breaker（服务熔断）
    // id 为正则为正常，否则为异常
    @HystrixCommand(fallbackMethod = "circuitFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
    })
    public String circuit(int id) {
        if (id <= 0) {
            throw new RuntimeException("ID 不能为 0 或负数");
        }
        return Thread.currentThread().getName() + ". 调用成功，流水号：" + IdUtil.simpleUUID();
    }

    public String circuitFallback(int id) {
        return "请输入正数的 ID，您的输入：" + id;
    }
}
