package com.cristian.accounts.infrastructure.database;

import com.cristian.accounts.application.gateways.SimpleEntityGateway;
import com.cristian.accounts.common.Entity;
import com.cristian.accounts.domain.Identifier;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;

abstract class SimpleInMemoryGateway<T extends Entity> implements SimpleEntityGateway<T> {

    protected final Map<Identifier, T> db;

    protected SimpleInMemoryGateway() {
        this.db = new ConcurrentHashMap<>();
    }

    protected SimpleInMemoryGateway(Map<Identifier, T> accounts) {
        this.db = requireNonNullElse(accounts,
                new ConcurrentHashMap<>());
    }

    @Override
    public boolean exists(Identifier identifier) {
        return db.containsKey(identifier);
    }

    @Override
    public Optional<T> find(Identifier identifier) {
        return Optional.ofNullable(db.get(identifier));
    }

    @Override
    public T save(T entity) {
        requireNonNull(entity);
        db.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public T delete(T entity) {
        return SimpleEntityGateway.super.delete(entity);
    }

}
