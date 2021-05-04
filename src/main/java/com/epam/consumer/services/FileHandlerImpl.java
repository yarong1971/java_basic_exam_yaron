package com.epam.consumer.services;

import com.epam.infra.InjectValue;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class FileHandlerImpl implements FileHandler {

    @InjectValue("producer_output_location")
    String locationDir;

    @SneakyThrows
    @Override
    public Optional<File> handleFile() {

        //Optional<>
        Optional<File> file = Files.list(Paths.get(locationDir))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .findFirst();

        return file;
    }
}
