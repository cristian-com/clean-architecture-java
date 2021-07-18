package com.cristian.accounts.domain.accounts;

import com.cristian.accounts.common.Entity;
import com.cristian.accounts.domain.ContactDetail;
import com.cristian.accounts.domain.EmailAddress;
import com.cristian.accounts.domain.PhoneNumber;
import com.cristian.accounts.domain.Identifier;
import com.cristian.accounts.domain.UuidIdentifier;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.cristian.accounts.common.Strings.requiresNonBlank;
import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;

@Data
public class Account implements Entity {
    private final Identifier id;
    private EmailAddress email;
    private String name;
    private String surname;
    private PhoneNumber phone;
    private Set<ContactDetail> contactDetails;
    private AccountStatus status;

    @Builder
    protected Account(Identifier id,
                      @NotNull EmailAddress email,
                      @NotBlank String name,
                      String surname,
                      PhoneNumber phone,
                      Set<ContactDetail> contactDetails,
                      @NotNull AccountStatus accountStatus) {
        this.id = requireNonNullElse(id, UuidIdentifier.random());
        this.surname = surname;
        this.phone = phone;
        setName(name);
        setEmail(email);
        setContactDetails(contactDetails);
        setStatus(requireNonNullElse(accountStatus, AccountStatus.CREATED));
    }

    public void setStatus(AccountStatus status) {
        requireNonNull(status);
        this.status = status;
    }

    public void setName(@NotBlank String name) {
        requiresNonBlank(name);
        this.name = name;
    }

    public void setEmail(@NotNull EmailAddress email) {
        requireNonNull(email);
        this.email = email;
    }

    public void setContactDetails(Set<ContactDetail> par) {
        contactDetails = Objects.requireNonNullElse(par, new HashSet<>());
        contactDetails.add(email);
        if (Objects.nonNull(phone)) {
            contactDetails.add(phone);
        }
    }

}
