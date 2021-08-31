package org.example.cloud.order;

import org.example.cloud.rule.RuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author yuchen
 * Aug 24, 2021
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "payment-service", configuration = RuleConfig.class)
public class OrderApp {

    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class, args);
    }
}
