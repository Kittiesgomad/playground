package com.marius.playground.service;

import com.marius.playground.domain.Kid;
import com.marius.playground.exception.NotFoundException;
import com.marius.playground.repository.KidRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KidService {

    private KidRepository kidRepository;

    public List<Kid> findAll() {
        return kidRepository.all();
    }

    public Kid findByTicket(String ticket) {
        return kidRepository.all().stream()
                .filter(kid -> kid.getTicket().equalsIgnoreCase(ticket))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Kid not found"));
    }
}
