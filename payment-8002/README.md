# 支付服务集群

payment-8001 和 payment-8002 之间：
* 业务类和主启动类相同；
* 端口不同；
* `spring.application.name` 相同，即微服务的名称相同。

## Controller

使用 [`@Value`](https://docs.spring.io/spring-framework/docs/5.3.5/reference/html/core.html#beans-value-annotations) 注解注入外部属性

## DiscoveryClient

支付服务通过 Discovery Client，可以知道在 Eureka Server 中
注册的其他服务实例的相关信息，如服务名、主机、端口、URI。

1. 在主启动类上注解 `@EnableDiscoveryClient` 以启用服务发现
2. 在 Controller 中使用 `DiscoveryClient` 提供的方法
