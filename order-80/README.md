# Consumer Order 消费者端订单管理模块

使用 Spring Framework web 板块提供的 [RestTemplate](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html) 
实现 RESTful 风格的对支付模块的订单创建、查看接口的调用。 


## Controller

### 1. `@Resource` Annotation

`javax.annotation.Resource`

被用于变量 (field) 或方法上时，容器会自动注入该资源 (resource)，和 `@Autowired` 作用类似。

注意：注入依赖前，得确定在 IoC Container 中有声明这个 bean。


## Ribbon: Client Side Load Balancing

[Spring 文档](https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-ribbon.html)

这里的客户端指的是与 Eureka Server 相对的客户端，包括服务的提供者和消费者。

`spring-cloud-netflix-eureka-client` 中已经包括了 Ribbon，所以已经实现了负载均衡。

### 修改负载均衡规则 (rule / strategy)

Ribbon 提供的规则有：
* 轮询 `RoundRobinRule`（默认）
* 随机 `RandomRule`
* `RetryRule` 
* `BestAvailableRule` 等

修改步骤：
1. 用一个定制 Ribbon client `@Configuration` 类来配置，注意不能放在该应用主启动类所在的目录及其子目录下。在这个配置类中声明需要的规则类作为一个 bean。
2. 在主启动类上注解 `@RibbonClient`

Feign (`@FeignClient`) 使用了 Ribbon。