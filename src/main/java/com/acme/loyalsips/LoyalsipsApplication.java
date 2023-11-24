package com.acme.loyalsips;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class LoyalsipsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoyalsipsApplication.class, args);
    }}
