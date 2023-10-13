package com.marius.playground.domain;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

public enum EquipmentType {
    BALL_PIT("Ball pit", BallPit.class, BallPit::new),
    CAROUSEL("Carousel", Carousel.class, Carousel::new),
    DOUBLE_SWING("Double swing", DoubleSwing.class, DoubleSwing::new),
    SLIDE("Slide", Slide.class, Slide::new);

    private final String name;
    private final Class<? extends Equipment> equipmentClass;
    private final Supplier<Equipment> equipmentSupplier;

    EquipmentType(String name, Class<? extends Equipment> equipmentClass, Supplier<Equipment> equipmentSupplier) {
        this.name = name;
        this.equipmentClass = equipmentClass;
        this.equipmentSupplier = equipmentSupplier;
    }

    public static Optional<Equipment> fromName(String name) {
        return Arrays.stream(values())
                .filter(equipmentType -> equipmentType.name.equalsIgnoreCase(name))
                .findFirst()
                .map(equipmentType -> equipmentType.equipmentSupplier.get());
    }

    public static Optional<String> nameOf(Class<? extends Equipment> equipmentClass) {
        return Arrays.stream(values())
                .filter(equipmentType -> equipmentType.equipmentClass.equals(equipmentClass))
                .findFirst()
                .map(equipmentType -> equipmentType.name);
    }
}
