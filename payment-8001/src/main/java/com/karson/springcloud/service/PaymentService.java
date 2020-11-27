package com.karson.springcloud.service;

import com.karson.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author Karson
 */
public interface PaymentService {


    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);

}
