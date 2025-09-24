package com.bazinga.eg.catalogservice.resource.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/catalog")
@Tag(name = "Home", description = "Home endpoints for the catalog service")
public record HomeController() {

    @GetMapping("/")
    @Operation(
            summary = "Get greeting message",
            description = "Returns a welcome message for the books catalog service"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    public String getGreeting() {
        log.info("Received request for greeting");

        return "Welcome to bazinga's books catalog!";
    }
}
