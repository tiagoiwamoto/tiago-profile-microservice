package br.com.tiagoiwamoto.tiagoprofilemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TiagoProfileMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiagoProfileMicroserviceApplication.class, args);
    }

}
