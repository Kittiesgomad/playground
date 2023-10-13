package com.marius.playground.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.marius.playground.domain.PlaySite;

import java.util.ArrayList;
import java.util.List;

public record AllPlaySitesResponse(
        @JsonProperty int totalVisitors,
        @JsonProperty List<PlaySiteResponse> playSites) {

    public static AllPlaySitesResponse fromPlaySites(List<PlaySite> playSites) {
        int totalVisitors = 0;
        final var playSiteResponses = new ArrayList<PlaySiteResponse>();

        for (PlaySite playSite : playSites) {
            totalVisitors += playSite.getOccupancy();
            playSiteResponses.add(PlaySiteResponse.fromPlaySite(playSite));
        }

        return new AllPlaySitesResponse(totalVisitors, playSiteResponses);
    }
}
