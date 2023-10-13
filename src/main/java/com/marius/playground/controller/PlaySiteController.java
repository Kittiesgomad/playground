package com.marius.playground.controller;

import com.marius.playground.request.PlaySiteRequest;
import com.marius.playground.response.AllPlaySitesResponse;
import com.marius.playground.response.PlaySiteResponse;
import com.marius.playground.service.KidService;
import com.marius.playground.service.PlaySiteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("play-site")
@AllArgsConstructor
public class PlaySiteController {

    private final PlaySiteService playSiteService;
    private final KidService kidService;

    @PostMapping
    public void createPlaySite(@RequestBody PlaySiteRequest playSiteRequest) {
        playSiteService.store(playSiteRequest.asPlaySite());
    }

    @PutMapping(path = "/{playSiteId}/assign-kid/{ticket}")
    public void assignKidToPlaySite(@PathVariable int playSiteId, @PathVariable String ticket) {
        final var playSite = playSiteService.findById(playSiteId);
        final var kid = kidService.findByTicket(ticket);

        playSiteService.addKidToPlaySite(kid, playSite);
    }

    @DeleteMapping(path = "/{playSiteId}/remove-kid/{ticket}")
    public void removeKidFromPlaySite(@PathVariable int playSiteId, @PathVariable String ticket) {
        final var playSite = playSiteService.findById(playSiteId);
        final var kid = kidService.findByTicket(ticket);

        playSiteService.removeKidFromPlaySite(kid, playSite);
    }

    @GetMapping(path = "/{playSiteId}")
    @ResponseBody
    public PlaySiteResponse getPlaySite(@PathVariable int playSiteId) {
        return PlaySiteResponse.fromPlaySite(playSiteService.findById(playSiteId));
    }

    @GetMapping
    @ResponseBody
    public AllPlaySitesResponse getAll() {
        return AllPlaySitesResponse.fromPlaySites(playSiteService.findAll());
    }
}
