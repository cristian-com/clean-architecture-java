package com.cristian.accounts.application.gateways;

import com.cristian.accounts.domain.accounts.Account;
import com.cristian.accounts.domain.members.Member;
import com.cristian.accounts.domain.organizations.Organization;

import java.util.Optional;

public interface MembersGateway extends SimpleEntityGateway<Member> {

    boolean exists(Account account, Organization organization);

}
