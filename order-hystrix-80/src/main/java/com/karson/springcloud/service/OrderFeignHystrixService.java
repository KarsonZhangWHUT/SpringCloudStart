package com.karson.springcloud.service;

import com.karson.springcloud.service.impl.OrderFeignHystrixServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Karson
 */
@Component
@FeignClient(value = "PAYMENT-SERVICE", fallback = OrderFeignHystrixServiceImpl.class)
public interface OrderFeignHystrixService {

    @GetMapping("/payment/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/error/{id}")
    String paymentInfo_ERROR(@PathVariable("id") Integer id);
}
