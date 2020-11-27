package com.karson.springcloud.service.impl;

import com.karson.springcloud.dao.AccountDao;
import com.karson.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author Karson
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;


    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("------------>account service开始扣款");
        accountDao.decrease(userId, money);
        log.info("------------>account service扣款完毕");

    }
}
