package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCircuitBreaker
// 开启数据监控注解
@SpringBootApplication
// 开启 FeignClients 注解
@EnableFeignClients
public class OrderServiceFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceFeignApplication.class, args);
    }

}
