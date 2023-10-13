package com.marius.playground.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@EqualsAndHashCode
@Getter
public class PlaySite {

    private static final AtomicInteger nextId = new AtomicInteger(1);

    private final int id;
    private final List<Equipment> equipmentList;
    private final List<Kid> kids = new ArrayList<>();

    public PlaySite(List<Equipment> equipmentList) {
        this.id = nextId.getAndIncrement();
        this.equipmentList = equipmentList;
    }

    public int getCapacity() {
        return equipmentList.stream().mapToInt(Equipment::getCapacity).sum();
    }

    public int getOccupancy() {
        return kids.size();
    }

    public boolean isFull() {
        return getOccupancy() >= getCapacity();
    }

    public void addKid(Kid kid) {
        kids.add(kid);
    }

    public void removeKid(Kid kid) {
        kids.remove(kid);
    }

    public double getUtilization() {
        if (isFull()) {
            return 100.0f;
        }

        return equipmentList.stream()
                .mapToDouble(equipment -> getUtilizationForEquipment(equipment) * equipment.getCapacity())
                .sum() / getCapacity();
    }

    private double getUtilizationForEquipment(Equipment equipment) {
        final var previousEquipmentCapacity = equipmentList.subList(0, equipmentList.indexOf(equipment)).stream()
                .mapToInt(Equipment::getCapacity)
                .sum();

        final var kidsLeftForEquipment = getOccupancy() - previousEquipmentCapacity;

        return kidsLeftForEquipment <= 0 ? 0
                : kidsLeftForEquipment >= equipment.getCapacity() ? 100.0f
                : equipment.getUtilization(kidsLeftForEquipment);
    }
}
