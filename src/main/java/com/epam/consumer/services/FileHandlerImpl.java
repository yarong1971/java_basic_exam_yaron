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
import java.util.stream.Collectors;

@Singleton
public class FileHandlerImpl implements FileHandler {

    @InjectValue("producer_output_location")
    private String locationDir;
    private BlockingQueue<File> producerFiles;
    private long lastFileSearch;

    public FileHandlerImpl() {
        this.producerFiles = new ArrayBlockingQueue<>(100);
        this.lastFileSearch = 0;
    }

    @SneakyThrows
    @Override
    public File getFile() {
        addNewFilesToList();
        return this.producerFiles.poll();
    }

    public boolean isFileQueueEmpty() {
        return this.producerFiles.isEmpty();
    }
        //Optional<>
        /*Optional<File> file = Files.list(Paths.get(locationDir))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .findFirst();

        return file;*/
    //}

    @SneakyThrows
    private void addNewFilesToList(){
        Path dir = Paths.get(this.locationDir);
        List<File> files = null;
        if (Files.isDirectory(dir)) {
            files = Files.list(dir)
                .filter(p -> !Files.isDirectory(p))
                .filter(p -> p.toFile().lastModified() > this.lastFileSearch)
                .map(p -> p.toFile())
                .collect(Collectors.toList());

            this.producerFiles.addAll(files);

/*
                    .map(p -> {
                    this.producerFiles.add(p.toFile());
                    System.out.println(p.toFile().getAbsolutePath());
                });
*/
        }
    }

    @SneakyThrows
    @Override
    public void deleteFile(File file) {
        System.out.println("File path: " + file.getAbsolutePath());
        //File newFile = new File(file);
        if(file.delete()){
            System.out.println("File " + file.getAbsolutePath() + " was successfuly deleted.");
        } else {
            System.out.println("Failed to delete file " + file.getAbsolutePath() +".");
        }
    }
}
