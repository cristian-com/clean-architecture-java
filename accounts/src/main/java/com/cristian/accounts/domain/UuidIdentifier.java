package com.cristian.accounts.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class UuidIdentifier implements Identifier {
    UUID id;

    public static UuidIdentifier random() {
        return new UuidIdentifier(UUID.randomUUID());
    }

    public static UuidIdentifier of(String id) {
        return new UuidIdentifier(UUID.fromString(id));
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
