package com.marius.playground.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@RequiredArgsConstructor
@Getter
public class Kid {

    private final String name;
    private final int age;
    private final String ticket;
    private final boolean patient;

    @Setter
    private PlaySite currentPlaySite = null;

    public Optional<PlaySite> getCurrentPlaySite() {
        return Optional.ofNullable(currentPlaySite);
    }
}
