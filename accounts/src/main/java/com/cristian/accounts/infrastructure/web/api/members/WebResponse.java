package com.cristian.accounts.infrastructure.web.api.members;

import com.cristian.accounts.common.Adapter;
import com.cristian.accounts.infrastructure.web.HttpResponse;
import com.cristian.accounts.domain.members.MemberRegistration;

class WebResponse {

    private static final MemberRegistrationAdapter members = new MemberRegistrationAdapter();

    static HttpResponse<MemberRegistrationResponse> created(MemberRegistration model) {
        return HttpResponse.created(members.transform(model));
    }

    static class MemberRegistrationAdapter implements Adapter<MemberRegistrationResponse, MemberRegistration> {
        @Override
        public MemberRegistrationResponse transform(MemberRegistration registration) {
            return new MemberRegistrationResponse(
                    registration.getId().toString(),
                    registration.getStatus().toString()
            );
        }
    }

    record MemberRegistrationResponse(String id, String status) {
    }

}
