package com.cristian.accounts.infrastructure.web.api.members;

import com.cristian.accounts.application.usecases.members.IMemberRegistrationUseCase;
import com.cristian.accounts.common.http.HttpResponse;
import lombok.RequiredArgsConstructor;

import static com.cristian.accounts.infrastructure.web.api.members.WebRequest.CreateMemberRegistrationRequest;
import static com.cristian.accounts.infrastructure.web.api.members.WebRequest.toCreateCommand;
import static com.cristian.accounts.infrastructure.web.api.members.WebResponse.MemberRegistrationResponse;
import static com.cristian.accounts.infrastructure.web.api.members.WebResponse.created;

@RequiredArgsConstructor
public class MemberRegistrationRestController {

    private final IMemberRegistrationUseCase memberRegistration;

    public HttpResponse<MemberRegistrationResponse> create(CreateMemberRegistrationRequest request) {
        return created(memberRegistration.register(toCreateCommand(request)));
    }

}
