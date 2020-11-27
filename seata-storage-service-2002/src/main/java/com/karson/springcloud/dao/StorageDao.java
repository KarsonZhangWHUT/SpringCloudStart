package com.karson.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Karson
 */
@Mapper
public interface StorageDao {
    //    void decease(Storage storage);
    void decease(@Param("productId") Long productId, @Param("count") Integer count);
}
