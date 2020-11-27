package com.karson.springcloud.service;

import com.karson.springcloud.entities.CommonResult;
import com.karson.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @author Karson
 */
@Component
public class PaymentFallbackService implements PaymentService {

    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(4444444, "服务降级返回");
    }
}
