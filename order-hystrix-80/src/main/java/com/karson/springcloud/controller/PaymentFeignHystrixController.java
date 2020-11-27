package com.karson.springcloud.controller;

import com.karson.springcloud.service.OrderFeignHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Karson
 */
@RestController
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class PaymentFeignHystrixController {

    @Resource
    private OrderFeignHystrixService feignHystrixService;

    @GetMapping("/consumer/payment/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        return feignHystrixService.paymentInfo_OK(id);
    }

//    @GetMapping("/consumer/payment/error/{id}")
//    public String paymentInfo_ERROR(@PathVariable("id") Integer id) {
//        return feignHystrixService.paymentInfo_ERROR(id);
//    }

    //    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
//    })
    @GetMapping("/consumer/payment/error/{id}")
    @HystrixCommand
    public String paymentInfo_ERROR(@PathVariable("id") Integer id) {
        return feignHystrixService.paymentInfo_ERROR(id);
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "ThreadPoll：" + Thread.currentThread().getName() + "  FallBackMethod,80,ID: " + id;
    }

    //全局fallback
    public String payment_Global_FallbackMethod() {
        return "全局异常处理，，，，，";
    }

}
