package org.example.cloud.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration 类（见 Reference）
 * @author yuchen
 * Aug 25, 2021
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced       // 开启负载均衡
    public RestTemplate restTemplate() {        // 声明 bean 交由 IoC 容器管理
        return new RestTemplate();
    }
}
