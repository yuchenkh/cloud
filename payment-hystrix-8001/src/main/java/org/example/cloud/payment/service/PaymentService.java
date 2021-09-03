package org.example.cloud.payment.service;

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
        return "线程池：" + Thread.currentThread().getName() + ".          通畅端点          ID: " + id;
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
        return "线程池：" + Thread.currentThread().getName() + ".          拥塞端点          ID: " + id;
    }

    public String lagHandler(int id) {
        return "线程池：" + Thread.currentThread().getName() + ".          8001 端口系统繁忙。          ID: " + id;
    }
}
