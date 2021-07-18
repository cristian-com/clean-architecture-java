package com.cristian.accounts.application.usecases.members;

import com.cristian.accounts.domain.Identifier;
import com.cristian.accounts.domain.members.MemberRegistration;

public interface IMemberRegistrationUseCase {

    MemberRegistration register(CreateMemberCommand command);

    record CreateMemberCommand(Identifier account, Identifier organization) {
    }

}
