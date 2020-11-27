package com.karson.springcloud.service;

import com.karson.springcloud.entities.CommonResult;
import com.karson.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Karson
 */
@Component
@FeignClient("PAYMENT-SERVICE")
public interface PaymentOpenFeignService {
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
}
