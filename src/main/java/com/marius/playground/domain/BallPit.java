package com.marius.playground.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class BallPit implements Equipment {

    private static final int CAPACITY = 3;

    @Override
    public int getCapacity() {
        return CAPACITY;
    }
}
