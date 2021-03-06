package com.andev.framework.data.entry;

import com.andev.framework.data.DevEntry;

public class IntEntry<V>
        extends DevEntry<Integer, V> {

    public IntEntry() {
    }

    public IntEntry(final Integer key) {
        super(key);
    }

    public IntEntry(
            final Integer key,
            final V value
    ) {
        super(key, value);
    }
}