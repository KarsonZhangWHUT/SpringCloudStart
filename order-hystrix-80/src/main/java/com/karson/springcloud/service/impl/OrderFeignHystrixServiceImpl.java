package com.karson.springcloud.service.impl;

import com.karson.springcloud.service.OrderFeignHystrixService;
import org.springframework.stereotype.Component;

/**
 * @author Karson
 */
@Component
public class OrderFeignHystrixServiceImpl implements OrderFeignHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "OrderFeignHystrixService ---fallback --- paymentInfo_OK:" + id;
    }

    @Override
    public String paymentInfo_ERROR(Integer id) {
        return "OrderFeignHystrixService ---fallback --- paymentInfo_ERROR:" + id;
    }
}
