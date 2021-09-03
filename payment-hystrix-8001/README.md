# Hystrix 构建支付微服务

## Fallback 回退

* 主启动类注解 `@EnableCircuitBreaker`（Spring Cloud 提供）
* 在 service 层的相关方法上注解 `@HystrixCommand` 并指定 **回退方法（Fallback method）**。



---

## 其他
### 关于 `@EnableCircuitBreaker`

> Annotation to enable a [CircuitBreaker](https://martinfowler.com/bliki/CircuitBreaker.html) implementation.

断路器，一个设计模式，并不是由 Spring Cloud 或者 Netflix 提出，这里只是 Spring Cloud 提供了一个实现。