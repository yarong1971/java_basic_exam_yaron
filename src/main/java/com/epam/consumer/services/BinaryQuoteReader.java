package com.epam.consumer.services;

import com.epam.common.model.Quote;
import com.epam.infra.InjectValue;
import lombok.SneakyThrows;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class BinaryQuoteReader implements QuoteReader {

        @InjectValue("producer_output_location")
        String locationDir;

        @SneakyThrows
        @Override
        public Quote read() {
            Quote quote;

            //Optional<>
            Optional<File> file = Files.list(Paths.get(locationDir))
                                    .filter(Files::isRegularFile)
                                    .map(Path::toFile)
                                    .findFirst();

            if(file.isPresent()){
                ObjectInputStream oos = new ObjectInputStream(new FileInputStream(file.get()));
                quote = (Quote) oos.readObject();
                return quote;
            }

            return null;
        }
}
