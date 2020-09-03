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



