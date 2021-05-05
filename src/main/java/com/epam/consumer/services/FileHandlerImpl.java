package com.epam.consumer.services;

import com.epam.infra.InjectValue;
import lombok.SneakyThrows;

import java.io.File;
import java.util.Optional;
import java.util.stream.Stream;

public class FileHandlerImpl implements FileHandler {

    @InjectValue("producer_output_location")
    String locationDir;

    @SneakyThrows
    @Override
    public Optional<String> getFile() {
          return Stream.of(new File(locationDir).listFiles())
                        .filter(file -> !file.isDirectory())
                        .map(File::getPath)
                        .findAny();

        //Optional<>
        /*Optional<File> file = Files.list(Paths.get(locationDir))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .findFirst();

        return file;*/
    }

    @SneakyThrows
    @Override
    public void deleteFile(String path) {
        System.out.println("File path: " + path);
        //File newFile = new File(file);
        File file = new File(path);
        if(file.delete()){
            System.out.println("File " + path + " was successfuly deleted.");
        } else {
            System.out.println("Failed to delete file " + path +".");
        }
    }
}
