package com.cristian.accounts.domain.members;

import com.cristian.accounts.common.Entity;
import com.cristian.accounts.domain.Identifier;
import com.cristian.accounts.domain.UuidIdentifier;
import com.cristian.accounts.domain.accounts.Account;
import com.cristian.accounts.domain.accounts.AccountStatus;
import com.cristian.accounts.domain.organizations.Organization;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;

import static com.cristian.accounts.domain.members.MemberRegistrationStatus.PENDING;
import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberRegistration implements Entity {

    private final Identifier id;
    private final Instant createdAt;
    private final Identifier organization;
    private final Identifier account;
    @Setter(AccessLevel.NONE)
    private MemberRegistrationStatus status;
    private Identifier member;

    @Builder
    private MemberRegistration(Identifier id, Instant createdAt,
                               MemberRegistrationStatus status,
                               Organization organization,
                               Account account,
                               Identifier member) {
        requireNonNull(account);
        requireNonNull(organization);

        this.id = requireNonNullElse(id, UuidIdentifier.random());
        this.createdAt = requireNonNullElse(createdAt, Instant.now());
        this.status = requireNonNullElse(status, PENDING);
        this.organization = organization.getId();
        this.account = account.getId();
        this.member = member;
    }

    public void cancel() {
        status = MemberRegistrationStatus.CANCELED;
    }

    public void accept() {
        status = MemberRegistrationStatus.ACCEPTED;
    }

    public void reject() {
        status = MemberRegistrationStatus.REJECTED;
    }

}
