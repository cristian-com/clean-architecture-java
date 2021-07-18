package com.cristian.accounts.infrastructure.database;

import com.cristian.accounts.application.gateways.MemberRegistrationGateway;
import com.cristian.accounts.domain.members.MemberRegistration;

public class MemberRegistrationInMemoryGateway extends SimpleInMemoryGateway<MemberRegistration>
        implements MemberRegistrationGateway {
}
