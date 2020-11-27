package com.karson.springcloud.service.impl;

import com.karson.springcloud.dao.OrderDao;
import com.karson.springcloud.entity.Order;
import com.karson.springcloud.service.AccountService;
import com.karson.springcloud.service.OrderService;
import com.karson.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Karson
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    public void create(Order order) {
        log.info("------开始新建订单");
        orderDao.create(order);


        log.info("-------订单微服务开始调用库存，做减库存");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("-------订单微服务开始调用库存，减库存结束");

        log.info("-------订单微服务开始调用账户，做扣减余额");
        accountService.decease(order.getUserId(), order.getMoney());
        log.info("------订单微服务开始调用账户，扣减余额结束");

        log.info("------修改订单状态开始");
        orderDao.update(order.getUserId(), 0);
        log.info("------修改订单状态结束");

        log.info("------新建订单结束");


    }
}
