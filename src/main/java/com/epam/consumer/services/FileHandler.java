package com.epam.consumer.services;

import java.io.File;

public interface FileHandler {
    File getFile();
    void deleteFile(File file);
}
