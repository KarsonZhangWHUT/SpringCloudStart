package com.karson.springcloud.controller;

import com.karson.springcloud.entities.CommonResult;
import com.karson.springcloud.entities.Payment;
import com.karson.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Karson
 */
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int res = paymentService.create(payment);
        log.info("*******插入结果：" + res);
        if (res > 0)
            return new CommonResult(200, "插入数据成功,serverPort:" + serverPort, res);
        else
            return new CommonResult(444, "插入失败");
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);

        log.info("******查询结果：" + payment + "哈哈哈哈哈");

        if (payment != null)
            return new CommonResult<>(200, "查询成功,serverPort:" + serverPort, payment);
        else
            return new CommonResult<>(444, "没有此订单");
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("*******service:" + service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }
}
