package com.cristian.accounts.domain.members;

import com.cristian.accounts.common.Entity;
import com.cristian.accounts.domain.Identifier;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import static java.util.Objects.requireNonNull;

@Data
public class Member implements Entity {
    private final Identifier id;
    private final Identifier organization;
    private Identifier account;

    @Builder
    public Member(@NotNull Identifier id,
                  @NotNull Identifier organization,
                  @NotNull Identifier account) {
        requireNonNull(organization);
        requireNonNull(account);

        this.id = id;
        this.account = account;
        this.organization = organization;
    }

}
