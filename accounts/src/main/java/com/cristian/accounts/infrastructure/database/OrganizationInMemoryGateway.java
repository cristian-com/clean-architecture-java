package com.cristian.accounts.infrastructure.database;

import com.cristian.accounts.application.gateways.OrganizationGateway;
import com.cristian.accounts.domain.UuidIdentifier;
import com.cristian.accounts.domain.organizations.Organization;

import java.util.Map;

public class OrganizationInMemoryGateway extends SimpleInMemoryGateway<Organization>
        implements OrganizationGateway {

    public OrganizationInMemoryGateway () {
        super(Map.of(
                UuidIdentifier.of("21b2486a-1ad4-4cfd-9414-d6a0bc05c759"),
                Organization.builder()
                        .id(UuidIdentifier.of("21b2486a-1ad4-4cfd-9414-d6a0bc05c759"))
                        .name("My Organization")
                        .build()
        ));
    }

}
