package com.marius.playground.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marius.playground.domain.Equipment;
import com.marius.playground.domain.EquipmentType;
import com.marius.playground.domain.PlaySite;

import java.util.List;

public record PlaySiteResponse(
        @JsonProperty int id,
        @JsonProperty List<String> equipment,
        @JsonProperty String utilization,
        @JsonProperty int kidsInQueue) {

    public static PlaySiteResponse fromPlaySite(PlaySite playSite) {
        final var equipmentList = playSite.getEquipmentList().stream()
                .map(equipmentPiece -> nameOf(equipmentPiece.getClass())).toList();
        final var kidsInQueue = playSite.getOccupancy() > playSite.getCapacity()
                ? playSite.getOccupancy() - playSite.getCapacity() : 0;

        return new PlaySiteResponse(
                playSite.getId(),
                equipmentList,
                String.format("%.2f%%", playSite.getUtilization()),
                kidsInQueue);
    }

    private static String nameOf(Class<? extends Equipment> equipment) {
        return EquipmentType.nameOf(equipment)
                .orElseThrow(() -> new IllegalStateException(
                        String.format("Unknown equipment type: %s", equipment.getSimpleName()))
                );
    }
}
