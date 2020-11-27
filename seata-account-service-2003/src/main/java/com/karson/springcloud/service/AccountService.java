package com.karson.springcloud.service;

import java.math.BigDecimal;

/**
 * @author Karson
 */
public interface AccountService {
    void decrease(Long userId, BigDecimal money);
}
