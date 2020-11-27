package com.karson.springcloud.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.karson.springcloud.entities.CommonResult;
import com.karson.springcloud.entities.Payment;

/**
 * @author Karson
 */
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(444, "自定义11111111global handlerException", new Payment(2020L, "serial22445321"));
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(444, "自定义22222222global handlerException", new Payment(2020L, "serial22445321"));
    }
}
