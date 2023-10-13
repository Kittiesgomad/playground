package com.marius.playground.domain;

public interface Equipment {

    int getCapacity();

    default double getUtilization(final int occupancy) {

        return occupancy * 100.0f / getCapacity();
    }
}
