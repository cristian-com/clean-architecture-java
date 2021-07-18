package com.cristian.accounts.domain.organizations;

import com.cristian.accounts.common.Entity;
import com.cristian.accounts.common.Strings;
import com.cristian.accounts.domain.Identifier;
import com.cristian.accounts.domain.UuidIdentifier;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

import static java.util.Objects.requireNonNullElse;

@Data
@Builder
public class Organization implements Entity {
    private Identifier id;
    private String name;
    private final Instant creationDate;

    public Organization(@NotNull Identifier id,
                        @NotBlank String name,
                        Instant creationDate) {
        this.id = requireNonNullElse(id, UuidIdentifier.random());
        this.creationDate = requireNonNullElse(creationDate, Instant.now());
        this.setName(name);
    }

    public void setName(@NotBlank String name) {
        Strings.requiresNonBlank(name);
        this.name = name;
    }

}
