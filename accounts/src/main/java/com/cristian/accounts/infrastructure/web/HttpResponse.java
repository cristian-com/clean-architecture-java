package com.cristian.accounts.infrastructure.web;

import lombok.Builder;

import java.util.Collections;
import java.util.List;

public record HttpResponse<T>(int status, List<String> messages, T resource) {

    @Builder
    public HttpResponse {
        if (messages == null) messages = Collections.emptyList();
    }

    public static <T> HttpResponse<T> created(T resource) {
        return created(null, resource);
    }

    @SuppressWarnings("unchecked")
    public static <T> HttpResponse<T> created(List<String> messages, T resource) {
        return (HttpResponse<T>) HttpResponse.builder()
                .resource(resource)
                .messages(messages)
                .build();
    }
}
