package org.example.cloud.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yuchen
 * Sep 2, 2021
 */
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class OrderHystrixApp {

    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixApp.class, args);
    }
}
