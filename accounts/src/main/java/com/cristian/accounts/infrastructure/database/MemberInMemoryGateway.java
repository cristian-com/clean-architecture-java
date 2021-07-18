package com.cristian.accounts.infrastructure.database;

import com.cristian.accounts.application.gateways.MemberGateway;
import com.cristian.accounts.domain.accounts.Account;
import com.cristian.accounts.domain.members.Member;
import com.cristian.accounts.domain.organizations.Organization;

import java.util.Optional;

public class MemberInMemoryGateway extends SimpleInMemoryGateway<Member>
        implements MemberGateway {

    @Override
    public Optional<Member> findByAccountAndOrganization(Account account, Organization organization) {
        return db.values()
                .parallelStream()
                .filter(member -> member.getAccount().equals(account.getId()) &&
                        member.getOrganization().equals(organization.getId()))
                .findAny();
    }

}
