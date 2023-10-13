package com.marius.playground.service;

import com.marius.playground.domain.Kid;
import com.marius.playground.domain.PlaySite;
import com.marius.playground.exception.BadRequestException;
import com.marius.playground.exception.NotFoundException;
import com.marius.playground.repository.PlaySiteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlaySiteService {

    private PlaySiteRepository playSiteRepository;

    public void store(PlaySite playSite) {
        playSiteRepository.add(playSite);
    }

    public PlaySite findById(int id) {
        return playSiteRepository.all().stream()
                .filter(playSite -> playSite.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Play site %s not found", id)));
    }

    public List<PlaySite> findAll() {
        return playSiteRepository.all();
    }

    public void addKidToPlaySite(final Kid kid, final PlaySite playSite) {
        if (playSite.isFull() && !kid.isPatient()) {
            throw new BadRequestException("The play site is full and the kid doesn't want to wait");
        }
        kid.getCurrentPlaySite().ifPresent(kidsPlaySite -> kidsPlaySite.removeKid(kid));
        playSite.addKid(kid);
        kid.setCurrentPlaySite(playSite);
    }

    public void removeKidFromPlaySite(final Kid kid, final PlaySite playSite) {
        playSite.removeKid(kid);
    }
}
