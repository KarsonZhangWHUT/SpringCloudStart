package com.karson.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Karson
 */
@Configuration
@MapperScan({"com.karson.springcloud.dao"})
public class MyBatisConfig {
}
