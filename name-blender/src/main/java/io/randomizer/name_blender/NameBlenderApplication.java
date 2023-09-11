package io.randomizer.name_blender;

import eu.crydee.syllablecounter.SyllableCounter;
import io.randomizer.name_blender.model.FullName;
import io.randomizer.name_blender.service.NameBlenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class NameBlenderApplication implements CommandLineRunner {

    private final NameBlenderService blenderService;

    public static void main(final String[] args) {
        SpringApplication.run(NameBlenderApplication.class, args);

    }

    @Override
    public void run(String... args) throws FileNotFoundException {
        log.info("EXECUTING : command line runner");

        blenderService.blendName();
    }
}



