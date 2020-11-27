package com.karson.springcloud.controller;

import com.karson.springcloud.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Karson
 */
@RestController
public class SendMessageController {

    @Autowired
    private IMessageService messageServiceImpl;


    @GetMapping("/sendMessage")
    public String sendMessage() {
        return messageServiceImpl.send();
    }

}
