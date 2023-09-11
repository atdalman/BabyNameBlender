package io.randomizer.name_blender.service;

import org.springframework.scheduling.annotation.Scheduled;

import java.io.FileNotFoundException;

public interface NameBlenderService {
    @Scheduled(fixedDelay = 5)
    void blendName() throws FileNotFoundException;
}
