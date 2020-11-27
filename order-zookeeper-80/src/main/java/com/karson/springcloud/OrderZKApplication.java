package com.karson.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Karson
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderZKApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderZKApplication.class, args);
    }
}
