# SpringCloud Gateway
## 一.SpringCloud Gateway介绍
SpringCloud Gateway是Spring Cloud的一个全新项目,该项目是基于Spring 5.0,Spring Boot 2.0和Project Reactor等技术开发的网关,它旨在为微服务架构提供一种简单有效的统一的API路由管理方式.</br>

SpringCloud Gateway作为Spring Cloud生态系统中的网关,目标是替代Zuul,在Spring Cloud2.0以上版本中,没有对新版本的Zuul2.0最新高性能版本进行集成,仍然还是使用的Zuul 1.x非Reactor模式的老版本.而为了提升网关的性能,SpringCloud Gateway是基于WebFlux框架实现的,而WebFlux框架底层则使用了高性能的Reactor模式通信框架Netty.</br>

SpringCloud Gateway的目标提供统一的路由方式基于Filter链的方式提供了网关基本的功能,例如:安全,监控/指标,和限流.

注意:SpringCloud Gateway是通过springboot和spring WebFlux提供的netty环境中,所以它不需要提供servlet容器.
添加依赖:
```java
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
    <version>2.2.1.RELEASE</version>
</dependency>
```
## 二.SpringCloud Gateway 配置
### 2.1.简要配置
```yaml
spring:
  cloud:
    gateway:
      routes:
      - id: after_route
        uri: https://example.org
        predicates:
        - Cookie=mycookie,mycookievalue
```
上述简单例子定义了一个id为after_route的路由,Cookie的断言工厂,cookie的名字为mycookie,值为匹配mycookievalue
### 2.2.展开式配置
```yaml
spring:
  cloud:
    gateway:
      routes:
      - id: after_route
        uri: https://example.org
        predicates:
        - name: Cookie
          args:
            name: mycookie
            regexp: mycookievalue
```
上述简单例子定义了一个id为after_route的路由,Cookie的断言工厂,cookie的名字为mycookie,值为匹配mycookievalue
## 三.SpringCloud Gateway 三大核心概念
### 3.1.Route(路由)

### 3.2.Predicate(断言)
SpringCloud Gateway提供了12种断言工厂</br>
#### 1.After路由断言工厂
After路由断言工厂提供了datetime(java ZonedDateTime类型)的参数.该断言匹配发生在datetime之后的请求,如下示例:
```yaml
spring:
  cloud:
    gateway:
      routes:
      - id: after_route
        uri: https://example.org
        predicates:
        - After=2017-01-20T17:42:47.789-07:00[America/Denver]
```
上述例子路由匹配发生在2017-01-20T17:42:47.789-07:00[America/Denver]后的所有请求.
#### 2.Before路由断言工厂
Before路由断言工厂提供了datetime(java ZonedDateTime类型)参数.该断言匹配发生在datetime之前的请求,如下示例:
```yaml
spring:
  cloud:
    gateway:
      routes:
      - id: before_route
        uri: https://example.org
        predicates:
        - Before=2017-01-20T17:42:47.789-07:00[America/Denver]
```
上述例子路由匹配发生在2017-01-20T17:42:47.789-07:00[America/Denver]前的所有请求.
#### 3.Between路由断言工厂
Between路由断言工厂提供了datetime1,datetime2(java ZonedDateTime类型)两个参数.该断言匹配发生在datetime1和datetime2之间的请求,如下示例:
```yaml
spring:
  cloud:
    gateway:
      routes:
      - id: between_route
        uri: https://example.org
        predicates:
        - Between=2017-01-20T17:42:47.789-07:00[America/Denver], 2017-01-21T17:42:47.789-07:00[America/Denver]
```
上述例子路由匹配发生在2017-01-20T17:42:47.789-07:00[America/Denver]和2017-01-21T17:42:47.789-07:00[America/Denver]之间的所有请求.
#### 4.Cookie路由断言工厂
Cookie路由断言工厂提供两个参数,cookie参数名和java正则表达式,该断言匹配cookie名称值为正则表达式的请求,如下示例:
```yaml
spring:
  cloud:
    gateway:
      routes:
      - id: cookie_route
        uri: https://example.org
        predicates:
        - Cookie=chocolate, ch.p
```
上述例子表示匹配请求种cookie的名称为chocolate,符合正则表达式为ch.p的所有请求
#### 5.Header路由断言工厂
Header路由断言工厂提供两个参数,header参数名和java正则表达式,该断言匹配header名称为符合正则表达式的值,如下示例:
```yaml
spring:
  cloud:
    gateway:
      routes:
      - id: header_route
        uri: https://example.org
        predicates:
        - Header=X-Request-Id, \d+
```
上述示例表示匹配请求中header参数为X-Request-Id,值符合\d+的所有请求

#### 6.Host路由断言工厂
Host路由断言工厂,提供一个参数,一个hostname列表的参数,如下示例:
```yaml
spring:
  cloud:
    gateway:
      routes:
      - id: host_route
        uri: https://example.org
        predicates:
        - Host=**.somehost.org,**.anotherhost.org
```
还支持URI模板,例如:{sub}.myhost.org.</br>
如请求值为www.somehost.org、beta.somehost.org或者www.anotherhost.org的Host头,则匹配.</br>
此断言将URI模板变量(如前面示例中定义的sub)提取为名称和值的映射,并使用 ServerWebExchangeUtils.URI_TEMPLATE_VARIABLES_ATTRIBUTE 中定义的键将其放置在ServerWebExchange.getAttributes()中.然后,这些值可供网关筛选器工厂使用.</br>

#### 7.Method路由断言工厂

#### 8.Path路由断言工厂

#### 9.Query路由断言工厂

#### 10.RemoteAddr路由断言工厂

#### 11.Weight路由断言工厂

#### 12.XForwarded路由断言工厂

### 3.3.Filter(过滤器）