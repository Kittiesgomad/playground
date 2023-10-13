package com.marius.playground.repository;

import com.marius.playground.domain.Kid;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.UUID;

@org.springframework.stereotype.Repository
@Slf4j
public class KidRepository extends Repository<Kid> {

    public KidRepository() throws IOException {
        super();

        log.info("Starting kid generation");
        Path path = Paths.get("src/main/resources/names.txt");

        Files.readAllLines(path).forEach(this::generateKid);
        log.info("Finished generating kids");
    }

    private void generateKid(String name) {
        final var ticket = UUID.randomUUID()
                .toString()
                .substring(0, 8);

        var kid = new Kid(
                name,
                randomAge(3, 12),
                ticket,
                new Random().nextBoolean()
        );

        add(kid);
    }

    private int randomAge(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }
}
