# 使用 Hystrix 进行服务降级和熔断

Feign 形式调用 `payment-hystrix=8001` 的服务。

访问 order 服务的 slow 接口会显示 **Read timed out**。由于 payment 服务相应的 timeout 接口要休眠 3 秒钟，
服务消费者直接不想等了。

## 高并发测试
用两万个线程去压 payment service，这时再访问 order service 的 get-payment 接口（正常那个），
会出现等待现象。


## Fallback 回退
当使用 Feign Client 调用外部服务时，服务不可用则运行回退方法。

* 主启动类注解 `@EnableHystrix`
* 配置文件配置：`feign.hystrix.enabled=true`
* 在 service 层的相关方法上注解 `@HystrixCommand` 并指定 **回退方法 (Fallback method)**。

### 将回退方法从具体的业务接口中分离出来
* 定义一个类实现 service 接口
* 在 service 接口上 `@FeignClient` 注解中指定回退类的名称
* 配置文件配置：`feign.hystrix.enabled=true`



---
## 其他
### 关于 `@EnableHystrix`
> Convenience annotation for clients to enable Hystrix circuit breakers (specifically). 
> Use this (optionally) in case you want discovery and know for sure that it is Hystrix you want. 
> All it does is turn on circuit breakers 
> and let the autoconfiguration find the Hystrix classes if they are available (i.e. you need Hystrix on the classpath as well).

### 关于 OpenFeign，Ribbon 和 Hystrix
`spring-cloud-starter-openfeign` 依赖了：
* `spring-cloud-netflix-ribbon`
* OpenFeign 的 Feign，而 Feign 又集成了 Netflix 的 Hystrix

`spring-cloud-starter-netflix-hystrix` 依赖了：
* Spring Cloud 集成的 Netflix Ribbon 和 Hystrix
* Netflix 的 Hystrix