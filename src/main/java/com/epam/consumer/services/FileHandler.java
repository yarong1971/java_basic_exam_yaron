package com.epam.consumer.services;

import java.util.Optional;

public interface FileHandler {
    Optional<String> getFile();
    void deleteFile(String path);
}
