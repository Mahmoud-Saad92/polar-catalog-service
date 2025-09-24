package com.bazinga.eg.catalogservice.common.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({OpenApiConfiguration.class})
public class ApplicationConfiguration {
}
