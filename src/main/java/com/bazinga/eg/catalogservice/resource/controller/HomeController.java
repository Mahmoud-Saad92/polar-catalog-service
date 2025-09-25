package com.bazinga.eg.catalogservice.resource.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/catalog")
public record HomeController() {

    @GetMapping({"", "/"})
    public String getGreeting() {
        log.info("Received request for greeting");

        return "Welcome to bazinga's books catalog!";
    }
}
