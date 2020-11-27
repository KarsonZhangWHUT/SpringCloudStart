package com.karson.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Karson
 */
@Service
public class PaymentService {
    public String paymentInfo_OK(Integer id) {
        return "ThreadPoll：" + Thread.currentThread().getName() + "  paymentInfo_OK,ID: " + id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_ERROR(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        int i = 10 / 0;
        return "ThreadPoll：" + Thread.currentThread().getName() + "  paymentInfo_ERROR,ID: " + id;
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "ThreadPoll：" + Thread.currentThread().getName() + "  FallBackMethod,ID: " + id;
    }

    //    ======================服务熔断=====================

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")//失败率
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("*****id不能是负数");
        }
        return Thread.currentThread().getName() + "\t" + "流水号" + IdUtil.simpleUUID().toString();
    }

    public String paymentCircuitBreaker_fallback(Integer id) {
        return "id不能为负数，请重试 id：" + id;
    }

}