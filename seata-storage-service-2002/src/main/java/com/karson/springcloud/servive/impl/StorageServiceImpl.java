package com.karson.springcloud.servive.impl;

import com.karson.springcloud.dao.StorageDao;
import com.karson.springcloud.servive.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Karson
 */
@Service
public class StorageServiceImpl implements StorageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Resource
    private StorageDao storageDao;

    @Override
    public void decrease(Long productId, Integer count) {
        LOGGER.info("------------------>storage-service中扣减库存开始");
        storageDao.decease(productId, count);
        LOGGER.info("------------------>storage-service中扣减库存结束");
    }
}
