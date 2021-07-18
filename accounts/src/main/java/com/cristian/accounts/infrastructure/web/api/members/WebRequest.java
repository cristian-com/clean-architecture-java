package com.cristian.accounts.infrastructure.web.api.members;

import com.cristian.accounts.application.usecases.members.IMemberRegistrationUseCase;
import com.cristian.accounts.domain.UuidIdentifier;

public interface WebRequest {

    static IMemberRegistrationUseCase.CreateMemberCommand toCreateCommand(CreateMemberRegistrationRequest request) {
        return new IMemberRegistrationUseCase.CreateMemberCommand(
                UuidIdentifier.of(request.accountId()),
                UuidIdentifier.of(request.organization()));
    }

    record CreateMemberRegistrationRequest(String accountId, String organization) {
    }

}
