package com.cristian.accounts.infrastructure.database;

import com.cristian.accounts.application.gateways.AccountsGateway;
import com.cristian.accounts.domain.EmailAddress;
import com.cristian.accounts.domain.UuidIdentifier;
import com.cristian.accounts.domain.accounts.Account;

import java.util.Map;

public class AccountsInMemoryGateway extends SimpleInMemoryGateway<Account>
        implements AccountsGateway {

    public AccountsInMemoryGateway() {
        super(Map.of(
                UuidIdentifier.of("21b2486a-1ad4-4cfd-9414-d6a0bc05c759"),
                Account.builder()
                        .id(UuidIdentifier.of("21b2486a-1ad4-4cfd-9414-d6a0bc05c759"))
                        .name("Fake name")
                        .email(EmailAddress.of("email@gmail.com"))
                        .build()
        ));
    }

}
