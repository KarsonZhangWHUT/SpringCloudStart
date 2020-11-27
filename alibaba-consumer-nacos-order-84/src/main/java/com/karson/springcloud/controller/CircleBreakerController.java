package com.karson.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.karson.springcloud.entities.CommonResult;
import com.karson.springcloud.entities.Payment;
import com.karson.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author Karson
 */
@RestController
@Slf4j
public class CircleBreakerController {

    public static final String SERVICE_URL = "http://nacos-payment-service";

    //======================原始RestTemplate=====================
    @Autowired
    private RestTemplate restTemplate;


    /*
     * fallback只负责业务运行时异常
     *
     * blockHandler只负责sentinel控制台配置的违规
     */
    @RequestMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback", fallback = "handlerFallback")
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler")
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler")
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) {
        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class);
        if (id == 4)
            throw new IllegalArgumentException("非法参数异常。。。");
        else if (result.getData() == null)
            throw new NullPointerException("没有该ID对应的记录");
        return result;
    }

    public CommonResult<Payment> handlerFallback(@PathVariable("id") Long id, Throwable e) {
        Payment payment = new Payment(id, "null");
        return new CommonResult<Payment>(444, "兜底异常方法" + e.getMessage(), payment);
    }

    public CommonResult blockHandler(@PathVariable Long id, BlockException exception) {

        Payment payment = new Payment(id, "null");

        return new CommonResult(445, "sentinel控制台配置的违规", payment);
    }


    //==========================OpenFeign==========================

    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/paymentSQL/{id}")
    @SentinelResource(value = "paymentSQL")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        return paymentService.paymentSQL(id);
    }


}
