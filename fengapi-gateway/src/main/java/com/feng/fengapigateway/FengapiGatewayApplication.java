package com.feng.fengapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FengapiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FengapiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        // 创建路由规则的构建器
        return builder.routes()
                // 定义路由规则，给该规则起一个名字 "tobaidu"
                .route("tobaidu", r -> r.path("/baidu")
                        .uri("https://baidu.com"))
                // 将满足 "/google" 路径的请求转发到 "www.google.com"
                .route("tospring", r -> r.path("/spring")
                        .uri("https://github.com/zhi-ac/fengapi-frontend"))
                // 创建并返回路由规则配置对象
                .build();
    }
}
