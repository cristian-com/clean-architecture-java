package com.cristian.accounts.application

import com.cristian.accounts.application.gateways.AccountGateway
import com.cristian.accounts.application.gateways.MemberGateway
import com.cristian.accounts.application.gateways.MemberRegistrationGateway
import com.cristian.accounts.application.gateways.OrganizationGateway
import com.cristian.accounts.application.usecases.members.IMemberRegistrationUseCase
import com.cristian.accounts.application.usecases.members.impl.MemberRegistrationUseCase
import com.cristian.accounts.common.exceptions.AccountNotFoundException
import com.cristian.accounts.common.exceptions.MemberAlreadyExistsException
import com.cristian.accounts.common.exceptions.OrganizationNotFoundException
import com.cristian.accounts.domain.accounts.Account
import com.cristian.accounts.domain.organizations.Organization
import spock.lang.Specification

import static com.cristian.accounts.common.DummyObjectsFactory.*

class MemberRegistrationSpec extends Specification {

    MemberGateway memberGateway
    AccountGateway accountGateway
    OrganizationGateway organizationGateway
    MemberRegistrationGateway memberRegistrationGateway
    MemberRegistrationUseCase interactor

    Organization organization
    Account account
    IMemberRegistrationUseCase.CreateMemberCommand command

    def setup() {
        organization = createOrganization()
        account = createAccount()
        command = new IMemberRegistrationUseCase.CreateMemberCommand(
                account.id,
                organization.id
        )

        memberGateway = Mock()
        accountGateway = Mock()
        organizationGateway = Mock()
        memberRegistrationGateway = Mock()
        interactor = new MemberRegistrationUseCase(memberGateway, accountGateway,
                organizationGateway, memberRegistrationGateway)
    }

    void "Self registration to an non existent organization should fail"() {
        when:
        organizationGateway.find(command.organization()) >> Optional.empty()
        interactor.register(command)

        then:
        thrown(OrganizationNotFoundException)
    }

    void "Self registration to an non existent account should fail"() {
        when:
        organizationGateway.find(command.organization()) >> Optional.of(createOrganization())
        accountGateway.find(command.account()) >> Optional.empty()
        interactor.register(command)

        then:
        thrown(AccountNotFoundException)
    }

    void "Self registration to an existing member should fail"() {
        given:
        accountGateway.find(account.id) >> Optional.of(account)
        organizationGateway.find(organization.id) >> Optional.of(organization)
        memberGateway.findByAccountAndOrganization(account, organization) >> Optional.of(createMember())

        when:
        interactor.register(command)

        then:
        thrown(MemberAlreadyExistsException)
    }

    void "Member registration success"() {
        given:
        accountGateway.find(account.id) >> Optional.of(account)
        organizationGateway.find(organization.id) >> Optional.of(organization)
        memberGateway.findByAccountAndOrganization(account, organization) >> Optional.empty()

        when:
        memberRegistrationGateway.save(*_) >> createMemberRegistration()
        var member = interactor.register(command)

        then:
        member
    }

}
