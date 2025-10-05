package com.bazinga.eg.catalogservice.common.audit;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public @NotNull Optional<String> getCurrentAuditor() {
        return Optional.of("SYS_ADMIN");
    }
}
