package com.marius.playground.controller;

import com.marius.playground.response.KidResponse;
import com.marius.playground.service.KidService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("kids")
@AllArgsConstructor
public class KidController {

    private final KidService kidService;

    @GetMapping
    @ResponseBody
    public List<KidResponse> getAll() {
        return kidService.findAll().stream()
                .map(KidResponse::fromKid)
                .toList();
    }
}
