package com.bazinga.eg.catalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.bazinga.eg.catalogservice"})
public class CatalogServiceApplication {

    public static void main(String... args) {
        SpringApplication.run(CatalogServiceApplication.class, args);
    }
}
