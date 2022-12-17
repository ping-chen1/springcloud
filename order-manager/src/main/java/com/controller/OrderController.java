package com.controller;

import com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/p")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/test")
    public String test() {
        return orderService.test();

    }
}
