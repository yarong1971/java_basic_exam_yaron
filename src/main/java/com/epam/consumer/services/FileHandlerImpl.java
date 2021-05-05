package com.epam.consumer.services;

import com.epam.infra.InjectValue;
import com.epam.infra.Singleton;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

@Singleton
public class FileHandlerImpl implements FileHandler {

    @InjectValue("producer_output_location")
    private String locationDir;
    private BlockingQueue<File> producerFiles;
    private long lastFileSearch;

    public FileHandlerImpl() {
        this.producerFiles = new LinkedBlockingQueue<>();
        this.lastFileSearch = 0;
    }

    @SneakyThrows
    @Override
    public File getFile() {
        addNewFilesToQueue();
        return this.producerFiles.poll();
    }

    public boolean isFileQueueEmpty() {
        return this.producerFiles.isEmpty();
    }

    @SneakyThrows
    private void addNewFilesToQueue(){
        Path dir = Paths.get(this.locationDir);
        List<File> files = null;
        if (Files.isDirectory(dir)) {
            files = Files.list(dir)
                .filter(p -> !Files.isDirectory(p))
                .filter(p -> p.toFile().lastModified() > this.lastFileSearch)
                .map(p -> p.toFile())
                .collect(Collectors.toList());
        }

        if(!files.isEmpty()){
            this.producerFiles.addAll(files);
            this.lastFileSearch = files.stream()
                    .map(File::lastModified)
                    .max(Long::compareTo)
                    .orElse(this.lastFileSearch);
        }
    }

    @SneakyThrows
    @Override
    public void deleteFile(File file) {
        System.out.println("File path: " + file.getAbsolutePath());
        if(file.delete()){
            System.out.println("File " + file.getAbsolutePath() + " was successfuly deleted.");
        } else {
            System.out.println("Failed to delete file " + file.getAbsolutePath() +".");
        }
    }
}
