package com.karson.springcloud.controller;

import com.karson.springcloud.entity.CommonResult;
import com.karson.springcloud.entity.Order;
import com.karson.springcloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Karson
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult createOrder(Order order) {
        orderService.create(order);
        return new CommonResult(200, "订单创建完成");
    }


}
