package com.cp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.ZonedDateTime;

@SpringBootApplication
public class GateWayMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(GateWayMain.class, args);
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
    }
}
