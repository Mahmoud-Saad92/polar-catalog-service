package com.bazinga.eg.catalogservice.common.property;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "catalog")
public class CatalogProperties {
    /**
     * A message to be displayed when a hello world controller is requested.
     */
    private String greetingMessage;
}
