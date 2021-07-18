package com.cristian.accounts.application.usecases.members.impl;

import com.cristian.accounts.application.gateways.AccountGateway;
import com.cristian.accounts.application.gateways.MemberGateway;
import com.cristian.accounts.application.gateways.MemberRegistrationGateway;
import com.cristian.accounts.application.gateways.OrganizationGateway;
import com.cristian.accounts.application.usecases.members.IMemberRegistrationUseCase;
import com.cristian.accounts.common.exceptions.AccountNotFoundException;
import com.cristian.accounts.common.exceptions.MemberAlreadyExistsException;
import com.cristian.accounts.common.exceptions.OrganizationNotFoundException;
import com.cristian.accounts.domain.members.MemberRegistration;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRegistrationUseCase implements IMemberRegistrationUseCase {

    private final MemberGateway memberGateway;
    private final AccountGateway accountGateway;
    private final OrganizationGateway organizationGateway;
    private final MemberRegistrationGateway memberRegistrationGateway;

    @Override
    public MemberRegistration register(CreateMemberCommand command) {
        var organization = organizationGateway.find(command.organization())
                .orElseThrow(OrganizationNotFoundException::new);

        var account = accountGateway.find(command.account())
                .orElseThrow(AccountNotFoundException::new);

        if (memberGateway.findByAccountAndOrganization(account, organization).isPresent()) {
            throw new MemberAlreadyExistsException();
        }

        return memberRegistrationGateway.save(
                MemberRegistration.builder()
                        .organization(organization)
                        .account(account)
                        .build()
        );
    }

}
