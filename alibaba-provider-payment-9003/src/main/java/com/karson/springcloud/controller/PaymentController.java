package com.karson.springcloud.controller;

import com.karson.springcloud.entities.CommonResult;
import com.karson.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author Karson
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    public static HashMap<Long, Payment> hashMap = new HashMap<>();

    static {
        hashMap.put(1L, new Payment(1L, "1111111111111111"));
        hashMap.put(2L, new Payment(2L, "2222222222222222"));
        hashMap.put(3L, new Payment(3L, "3333333333333333"));
        hashMap.put(4L, new Payment(4L, "4444444444444444"));
    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
        Payment payment = hashMap.get(id);
        return new CommonResult<Payment>(200, "serverport:" + serverPort, payment);
    }
}
