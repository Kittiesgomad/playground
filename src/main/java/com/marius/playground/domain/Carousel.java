package com.marius.playground.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class Carousel implements Equipment {

    private static final int CAPACITY = 4;

    @Override
    public int getCapacity() {
        return CAPACITY;
    }
}
