package com.cristian.accounts.infrastructure.database;

import com.cristian.accounts.application.gateways.MembersGateway;
import com.cristian.accounts.domain.accounts.Account;
import com.cristian.accounts.domain.members.Member;
import com.cristian.accounts.domain.organizations.Organization;

import java.util.Optional;

public class MembersInMemoryGateway extends SimpleInMemoryGateway<Member>
        implements MembersGateway {

    @Override
    public boolean exists(Account account, Organization organization) {
        return db.values()
                .parallelStream()
                .anyMatch(member -> member.getAccount().equals(account.getId()) &&
                        member.getOrganization().equals(organization.getId()));
    }

}
