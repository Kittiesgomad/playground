package com.marius.playground.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marius.playground.domain.Equipment;
import com.marius.playground.domain.EquipmentType;
import com.marius.playground.domain.PlaySite;
import com.marius.playground.exception.BadRequestException;

import java.util.List;

public record PlaySiteRequest(@JsonProperty List<String> equipment) {

    public PlaySite asPlaySite() {
        return new PlaySite(equipment.stream()
                .map(this::asEquipment).toList());
    }

    private Equipment asEquipment(String equipmentName) {
        return EquipmentType.fromName(equipmentName)
                .orElseThrow(() -> new BadRequestException(String.format("Unknown equipment type: %s", equipmentName)));
    }
}
