package com.cristian.accounts.common;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

public interface Adapter<M, V> {

    M transform(V from);

    @SuppressWarnings("unchecked")
    default Stream<M> transform(V... from) {
        return transform(Arrays.asList(from));
    }

    default Stream<M> transform(Collection<V> from) {
        return from.stream().map(this::transform);
    }

}
