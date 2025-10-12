package com.bazinga.eg.catalogservice.common.property;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "catalog.test-data")
public class CatalogTestDataProperties {
    /**
     * A flag to enable/disable test data generation.
     */
    private Boolean enabled;
}
