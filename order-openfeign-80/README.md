# Order-OpenFeign-80

这个项目还是使用 Eureka 作为服务注册中心，自身作为一个 Eureka client。

OpenFeign。

需引入 `org.springframework.cloud:spring-cloud-starter-openfeign`。

开启步骤：
1. 主启动类上标注 `@EnableFeignClients`
2. 定义相应的 service 层，并标注 `FeignClient`

现在的机制和 `Order-80` 不同，后者是用 `RestTemplate` 通过 HTTP 调用外部服务的接口，
前者是在服务内部定义一个 REST client。

## 设置 Feign Client 输出日志的完整程度

----
[关于 Feign](https://github.com/OpenFeign/feign)