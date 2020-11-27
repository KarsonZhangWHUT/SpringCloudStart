package com.karson.springcloud.servive;

/**
 * @author Karson
 */
public interface StorageService {
    void decrease(Long productId, Integer count);
}
