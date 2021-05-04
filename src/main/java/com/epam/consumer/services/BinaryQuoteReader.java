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

    @SneakyThrows
    @Override
    public Quote read(File file) {
        Quote quote;
        ObjectInputStream oos = new ObjectInputStream(new FileInputStream(file));
        quote = (Quote) oos.readObject();
        return quote;
    }
}
