package com.marius.playground.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marius.playground.domain.Kid;

public record KidResponse(
        @JsonProperty String name,
        @JsonProperty int age,
        @JsonProperty String ticket,
        @JsonProperty boolean isPatient,
        @JsonProperty PlaySiteResponse atPlaySite) {

    public static KidResponse fromKid(Kid kid) {
        return new KidResponse(
                kid.getName(),
                kid.getAge(),
                kid.getTicket(),
                kid.isPatient(),
                kid.getCurrentPlaySite().map(PlaySiteResponse::fromPlaySite).orElse(null));
    }
}
