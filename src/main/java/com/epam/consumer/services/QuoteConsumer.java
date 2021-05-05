package com.epam.consumer.services;

import com.epam.common.model.Quote;

import java.io.File;

public interface QuoteConsumer {
    File getFile();
    void readQuote(File file);
    void saveQuote();
}
