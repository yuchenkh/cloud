# Consumer Order 消费者端订单管理模块

使用 Spring Framework web 板块提供的 [RestTemplate](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html) 
实现 RESTful 风格的对支付模块的订单创建、查看接口的调用。 


## Controller

### 1. `@Resource` Annotation

`javax.annotation.Resource`

被用于变量 (field) 或方法上时，容器会自动注入该资源 (resource)，和 `@Autowired` 作用类似。

注意：注入依赖前，得确定在 IoC Container 中有声明这个 bean。