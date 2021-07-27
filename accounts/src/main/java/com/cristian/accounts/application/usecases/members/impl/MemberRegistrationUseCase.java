package com.cristian.accounts.application.usecases.members.impl;

import com.cristian.accounts.application.gateways.AccountsGateway;
import com.cristian.accounts.application.gateways.MembersGateway;
import com.cristian.accounts.application.gateways.MemberRegistrationsGateway;
import com.cristian.accounts.application.gateways.OrganizationGateway;
import com.cristian.accounts.application.usecases.members.IMemberRegistrationUseCase;
import com.cristian.accounts.application.exceptions.AccountNotFoundException;
import com.cristian.accounts.application.exceptions.MemberAlreadyExistsException;
import com.cristian.accounts.application.exceptions.OrganizationNotFoundException;
import com.cristian.accounts.domain.members.MemberRegistration;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRegistrationUseCase implements IMemberRegistrationUseCase {

    private final MembersGateway membersGateway;
    private final AccountsGateway accountsGateway;
    private final OrganizationGateway organizationGateway;
    private final MemberRegistrationsGateway memberRegistrationsGateway;

    @Override
    public MemberRegistration register(CreateMemberCommand command) {
        var organization = organizationGateway.find(command.organization())
                .orElseThrow(OrganizationNotFoundException::new);

        var account = accountsGateway.find(command.account())
                .orElseThrow(AccountNotFoundException::new);

        if (membersGateway.exists(account, organization)) {
            throw new MemberAlreadyExistsException();
        }

        return memberRegistrationsGateway.save(
                MemberRegistration.builder()
                        .organization(organization)
                        .account(account)
                        .build()
        );
    }

}
