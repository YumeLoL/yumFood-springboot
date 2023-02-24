package com.yumfood;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@SpringBootApplication
@ServletComponentScan
public class YumFoodApplication {

    public static void main(String[] args) {

        SpringApplication.run(YumFoodApplication.class, args);
        log.info("YumFood project is running ...........");
    }

}
