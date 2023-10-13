package com.marius.playground.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class Slide implements Equipment {

    private static final int CAPACITY = 1;

    @Override
    public int getCapacity() {
        return CAPACITY;
    }
}
