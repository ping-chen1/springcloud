package com.service.impl;

import com.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    private static String url = "http://PAYMENT-CLOUD";

    @Override
    public String test() {
        String forObject = restTemplate.getForObject(url+"/p/test", String.class);
        System.out.println(forObject);
        return forObject;
    }
}
