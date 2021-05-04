package com.epam.consumer.services;

import java.io.File;
import java.util.Optional;

public interface FileHandler {
    Optional<File> handleFile();
}
