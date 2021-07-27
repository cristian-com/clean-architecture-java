package com.cristian.accounts.infrastructure.database;

import com.cristian.accounts.application.gateways.MemberRegistrationsGateway;
import com.cristian.accounts.domain.members.MemberRegistration;

public class MemberRegistrationsInMemoryGateway extends SimpleInMemoryGateway<MemberRegistration>
        implements MemberRegistrationsGateway {
}
