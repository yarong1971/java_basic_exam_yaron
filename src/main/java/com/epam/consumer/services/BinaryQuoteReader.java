package com.epam.consumer.services;

import com.epam.common.model.Quote;
import lombok.SneakyThrows;

import java.io.*;

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
