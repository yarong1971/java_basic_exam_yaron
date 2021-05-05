package com.epam.consumer.services;

import com.epam.common.model.Quote;

import java.io.File;

public interface QuoteConsumer {
    File getFile();
    //Quote readQuote(File file);
    void readQuote(File file);
    //void saveQuote(Quote quote);
    void saveQuote();
}
