package com.bazinga.eg.catalogservice.common.util;

import java.time.LocalDateTime;

public class AuditingBaseEntity {

    private String createdBy;

    private String lastModifiedBy;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
