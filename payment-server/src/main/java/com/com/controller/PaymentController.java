package com.com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/p")
public class PaymentController {

    @GetMapping("/test")
    public String test(){
        return "payment";
    }
}
