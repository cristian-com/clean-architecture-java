package com.cristian.accounts.common

import com.cristian.accounts.domain.EmailAddress
import com.cristian.accounts.domain.Identifier
import com.cristian.accounts.domain.PhoneNumber
import com.cristian.accounts.domain.UuidIdentifier
import com.cristian.accounts.domain.accounts.Account
import com.cristian.accounts.domain.members.Member
import com.cristian.accounts.domain.members.MemberRegistration
import com.cristian.accounts.domain.members.MemberRegistrationStatus
import com.cristian.accounts.domain.organizations.Organization

import java.time.Instant

class DummyObjectsFactory {

    private DummyObjectsFactory() {}

    static Account createAccount(Map props = [:]) {
        new Account(
                props.id ?: UuidIdentifier.random(),
                props.address ?: EmailAddress.of(props.email ?: "email@dot.com"),
                props.name ?: 'name',
                props.surname ?: 'surname',
                props.phone = PhoneNumber.of(props.phone ?: "20202020202"),
                props.contactDetails ?: null,
                com.cristian.accounts.domain.accounts.AccountStatus.ACTIVE)
    }

    static Organization createOrganization(Map props = [:]) {
        new Organization(
                props.id ?: UuidIdentifier.random(),
                props.name ?: 'name',
                props.creationDate ?: null)
    }

    static Member createMember(Map props = [:]) {
        new Member(
            props.id ?: UuidIdentifier.random(),
            props.organization ?: createOrganization().id,
            props.account ?: createAccount().id
        )
    }

    static MemberRegistration createMemberRegistration(Map props = [:]) {
        return new MemberRegistration(
                props.id ?: UuidIdentifier.random() as Identifier,
                props.createdAt as Instant,
                props.status as MemberRegistrationStatus,
                props.organization as Organization ?: createOrganization(),
                props.account as Account ?: createAccount(),
                props.member as Identifier
        )
    }
}
