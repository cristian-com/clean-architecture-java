package com.cristian.accounts;

import com.cristian.accounts.application.usecases.members.impl.MemberRegistrationUseCase;
import com.cristian.accounts.domain.UuidIdentifier;
import com.cristian.accounts.infrastructure.database.AccountInMemoryGateway;
import com.cristian.accounts.infrastructure.database.MemberInMemoryGateway;
import com.cristian.accounts.infrastructure.database.MemberRegistrationInMemoryGateway;
import com.cristian.accounts.infrastructure.database.OrganizationInMemoryGateway;
import com.cristian.accounts.infrastructure.web.api.members.MemberRegistrationRestController;
import com.cristian.accounts.infrastructure.web.server.Server;
import lombok.extern.java.Log;

import java.io.IOException;

@Log
public class CleanArchitectureDemo {
    public static void main(String[] args) throws IOException {
        Server server = new Server("/accounts", 8080);

        var orgGateway = new OrganizationInMemoryGateway();
        var memberGateway = new MemberInMemoryGateway();
        var accountsGateway = new AccountInMemoryGateway();
        var memberRegistrationGateway = new MemberRegistrationInMemoryGateway();

        var useCase = new MemberRegistrationUseCase(memberGateway, accountsGateway,
                orgGateway, memberRegistrationGateway);
        Server.controller = new MemberRegistrationRestController(useCase);

        server.run();
        log.info("Running ...");
        log.info(UuidIdentifier.random().toString());
    }
}
