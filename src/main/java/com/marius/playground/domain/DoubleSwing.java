package com.marius.playground.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class DoubleSwing implements Equipment {

    private static final int CAPACITY = 2;

    @Override
    public int getCapacity() {
        return CAPACITY;
    }

    @Override
    public double getUtilization(int occupancy) {
        return CAPACITY == occupancy ? 100f : 0f;
    }
}
