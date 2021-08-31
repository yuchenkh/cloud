package org.example.cloud.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定负载均衡规则的 Spring 配置类
 * @author yuchen
 * Aug 31, 2021
 */
@Configuration
public class RuleConfig {

    @Bean
    public IRule rule() {
        return new RandomRule();        // 定义为随机规则
    }
}
