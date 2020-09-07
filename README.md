# zzq-spring-cloud-demo
### eureka-server
eureka服务端
### eureka-client
一个微服务模块 通过 @EnableDiscoveryClient 注解注册到eureka。
通过DiscoveryClient可以获取eureka上的微服务模块
### eureka-consumer
一个消费服务模块 调用eureka-client提供的API。
通过LoadBalancerClient 选择指定服务名称获取host 和 port 结合mapping进行远程Rest调用

### eureka-consumer-ribbon
eureka-consumer的升级版 利用ribbon。
结合@LoadBalanced注解直接用RestTemplate通过微服务名称进行远程Rest调用
> 那么这样的请求为什么可以调用成功呢？
因为Spring Cloud Ribbon有一个拦截器，它能够在这里进行实际调用的时候，自动的去选取服务实例，并将实际要请求的IP地址和端口替换这里的服务名，从而完成服务接口的调用

> Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端负载均衡的工具。它是一个基于HTTP和TCP的客户端负载均衡器。它可以通过在客户端中配置ribbonServerList来设置服务端列表去轮询访问以达到均衡负载的作用。
  当Ribbon与Eureka联合使用时，ribbonServerList会被DiscoveryEnabledNIWSServerList重写，扩展成从Eureka注册中心中获取服务实例列表。同时它也会用NIWSDiscoveryPing来取代IPing，它将职责委托给Eureka来确定服务端是否已经启动。
  而当Ribbon与Consul联合使用时，ribbonServerList会被ConsulServerList来扩展成从Consul获取服务实例列表。同时由ConsulPing来作为IPing接口的实现。

### eureka-consumer-feign
Spring Cloud feign 通过@EnableFeignClients 开启扫描@FeignClient 结合 Spring MVC mapping、接口绑定web服务API。(扩展了Feign文件上传)


### config-repo
配置文件

### config-server 
一个分布式配置中心 @EnableConfigServer开启

### config-server-eureka
一个分布式配置中心 注册到eureka 

### config-client
一个用来从 分布式配置中心 获取配置的demo 因为分布式配置中心 注册到注册中心 所以可以通过注册中心获取配置
只需配置注册中心地址  指定的服务名称
可以通过RefreshScope热更新配置 （通过spring-boot-starter-actuator模块的/refresh）
```properties
#注册中心
eureka.client.serviceUrl.defaultZone=http://127.0.0.1:10001/eureka/
#开启通过服务来访问Config Server
spring.cloud.config.discovery.enabled=true
#指定访问的服务
spring.cloud.config.discovery.serviceId=config-server-eureka
#环境配置
spring.cloud.config.profile=dev
#关闭鉴权RefreshScope
management.security.enabled=false
```
### eureka-consumer-ribbon-hystrix
利用hystrix 实现服务降级、隔离、断路 @EnableCircuitBreaker开启
>那么断路器是在什么情况下开始起作用呢？这里涉及到断路器的三个重要参数：快照时间窗、请求总数下限、错误百分比下限。这个参数的作用分别是：
 快照时间窗：断路器确定是否打开需要统计一些请求和错误数据，而统计的时间范围就是快照时间窗，默认为最近的10秒。
 请求总数下限：在快照时间窗内，必须满足请求总数下限才有资格根据熔断。默认为20，意味着在10秒内，如果该hystrix命令的调用此时不足20次，即时所有的请求都超时或其他原因失败，断路器都不会打开。
 错误百分比下限：当请求总数在快照时间窗内超过了下限，比如发生了30次调用，如果在这30次调用中，有16次发生了超时异常，也就是超过50%的错误百分比，在默认设定50%下限情况下，这时候就会将断路器打开。
 那么当断路器打开之后会发生什么呢？我们先来说说断路器未打开之前，对于之前那个示例的情况就是每个请求都会在当hystrix超时之后返回fallback，每个请求时间延迟就是近似hystrix的超时时间，如果设置为5秒，那么每个请求就都要延迟5秒才会返回。当熔断器在10秒内发现请求总数超过20，并且错误百分比超过50%，这个时候熔断器打开。打开之后，再有请求调用的时候，将不会调用主逻辑，而是直接调用降级逻辑，这个时候就不会等待5秒之后才返回fallback。通过断路器，实现了自动地发现错误并将降级逻辑切换为主逻辑，减少响应延迟的效果。
 在断路器打开之后，处理逻辑并没有结束，我们的降级逻辑已经被成了主逻辑，那么原来的主逻辑要如何恢复呢？对于这一问题，hystrix也为我们实现了自动恢复功能。当断路器打开，对主逻辑进行熔断之后，hystrix会启动一个休眠时间窗，在这个时间窗内，降级逻辑是临时的成为主逻辑，当休眠时间窗到期，断路器将进入半开状态，释放一次请求到原来的主逻辑上，如果此次请求正常返回，那么断路器将继续闭合，主逻辑恢复，如果这次请求依然有问题，断路器继续进入打开状态，休眠时间窗重新计时



### hystrix-dashboard
hystrix面板 @EnableHystrixDashboard开启服务
通过 /hystrix 进入 对单个服务进行监控 http://hystrix-app:port/hystrix.stream
![config-repo/hystrix-dashboard.png](config-repo/hystrix-dashboard.png)

### api-gateway
微服务网关 @EnableZuulProxy 开启服务
> 由于Spring Cloud Zuul在整合了Eureka之后，具备默认的服务路由功能，即：当我们这里构建的api-gateway应用启动并注册到eureka之后，服务网关会发现上面我们启动的两个服务eureka-client和eureka-consumer，这时候Zuul就会创建两个路由规则。每个路由规则都包含两部分，一部分是外部请求的匹配规则，另一部分是路由的服务ID。针对当前示例的情况，Zuul会创建下面的两个路由规则：
转发到eureka-client服务的请求规则为：/eureka-client/**
转发到eureka-consumer服务的请求规则为：/eureka-consumer/**

通过 继承 ZuulFilter类可实现权限拦截等

### stream-kafka
Spring Cloud Stream
>Spring Cloud Stream是一个用来为微服务应用构建消息驱动能力的框架。它可以基于Spring Boot来创建独立的、可用于生产的Spring应用程序。它通过使用Spring Integration来连接消息代理中间件以实现消息事件驱动的微服务应用。Spring Cloud Stream为一些供应商的消息中间件产品提供了个性化的自动化配置实现，并且引入了发布-订阅、消费组以及消息分区这三个核心概念

### alibaba-nacos-server
一个注册在nacos上的服务提供者  
注意这里和eureka-server 区别在于 前者是注册中心本体  而后者是借助外部nacos注册中心 注册的一个微服务模块提供API
> [nacos](https://github.com/alibaba/nacos/releases)启动   
> Linux/Unix/Mac：sh startup.sh -m standalone
>
> Windows：cmd startup.cmd -m standalone
> 
> 默认用户名 密码 nacos

### alibaba-nacos-client
注册一个模块到nacos 并且通过服务发现远程调用 alibaba-nacos-server

### alibaba-nacos-config-client
nacos 作为配置中心 本模块获取配置中心配置
需要注意 nacos 注册中心 配置中心 地址一样 配置别名不一致
```properties
#配置中心
spring.cloud.nacos.config.server-addr=127.0.0.1:8848
#注册中心
spring.cloud.nacos.discovery.server-addr=127.0.0.1:8848
```

> Data ID中的alibaba-nacos-config-client：对应客户端的配置spring.cloud.nacos.config.prefix，默认值为${spring.application.name}，即：服务名
>
> Data ID中的properties：对应客户端的配置spring.cloud.nacos.config.file-extension，默认值为properties
>
> Group的值DEFAULT_GROUP：对应客户端的配置spring.cloud.nacos.config.group，默认值为DEFAULT_GROUP
>
>spring.profiles.active=DEV
>
>${spring.cloud.nacos.config.prefix}-${spring.profile.active}.${spring.cloud.nacos.config.file-extension}
>
>在采用默认值的应用要加载的配置规则就是：Data ID=${spring.application.name}.properties，Group=DEFAULT_GROUP。
 
建议这些配置不要配置在bootstrap.properties  而是在发布脚本的启动命令中，用-Dspring.profiles.active=DEV的方式来动态指定，会更加灵活