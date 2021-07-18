package com.cristian.accounts.application.gateways;

import com.cristian.accounts.domain.Identifier;

import java.util.Optional;

public interface SimpleEntityGateway<T> {

    boolean exists(Identifier identifier);

    Optional<T> find(Identifier identifier);

    default T save(T entity) {
        throw new UnsupportedOperationException();
    }

    default T delete(T entity) {
        throw new UnsupportedOperationException();
    }

}
