package com.marius.playground.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Repository<T> {

    private final List<T> records;

    private Repository(List<T> records) {
        this.records = records;
    }

    Repository() {
        this(new ArrayList<>());
    }

    public void add(T record) {
        records.add(record);
    }

    public List<T> all() {
        return Collections.unmodifiableList(records);
    }
}
