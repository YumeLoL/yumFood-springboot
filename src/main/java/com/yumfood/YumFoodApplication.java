package com.yumfood;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class YumFoodApplication {

    public static void main(String[] args) {

        SpringApplication.run(YumFoodApplication.class, args);
        log.info("YumFood project is running ...........");
    }

}
