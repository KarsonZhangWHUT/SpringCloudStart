package com.karson.springcloud.controller;

import com.karson.springcloud.entities.CommonResult;
import com.karson.springcloud.entities.Payment;
import com.karson.springcloud.service.PaymentOpenFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Karson
 */
@RestController
public class OrderController {

    @Resource
    private PaymentOpenFeignService service;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> payment(@PathVariable("id") Long id) {
        return service.getPaymentById(id);
    }
}
