# SpringCloud Consul
## 一.SpringCloud Consul介绍
## 四.服务的发现
服务的发现基于微服务架构.通过手动配置每个客户端式非常困难的.
consul通过Http API和DNS提供服务发下.SpringCloud利用consul的Http API实现服务的发现.
不会影响非SpringCloud通过DNS实现服务的发现与注册.Consul Agents服务器运行在一个集群中,该集群通过gossip协议进行通信，并使用Raft共识协议.
### 4.1.添加依赖
```manifest
<dependency>
   <groupId>org.springframework.cloud</groupId>
   <artifactId>spring-cloud-starter-consul-discovery</artifactId>
</dependency>
```
### 4.2.注册服务
当一个客户端向consul注册服务,则会生产一个客户端的元数据,如:host、port、id、服务名和tags.还会创建一个http的健康检查,consul默认每10s调用一次/actuator/health进行健康检测.如果健康检查失败,则会进行标记.
<br>
客户端例子:
```java
@SpringBootApplication
@RestController
public class Application {

    @RequestMapping("/")
    public String home() {
        return "Hello world";
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).web(true).run(args);
    }

}
```
application.yml
```yaml
spring:
  cloud:
    consul:
      host: localhost
      port: 8500
```
注意:
如果使用 Spring Cloud Consul Config,则需要将上述值放在bootstrap.yml而不是 application.yml中.
默认service-name= ${spring.application.name}, instance-id=Spring 上下文id和port = ${server.port}.
可以通过设置spring.cloud.consul.discovery.enabled=false禁用客户端.当设置spring.cloud.discovery.enabled=false时Consul Discovery Client也会被禁用.