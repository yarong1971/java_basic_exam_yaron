package com.epam.consumer.services;

import com.epam.common.model.Quote;

import java.nio.file.Path;
import java.util.Optional;

public interface QuoteConsumer {
    Optional<String> getFile();
    Quote readQuote(String path);
    //void readQuote(File file, BlockingQueue<Quote> queue);
    void saveQuote(Quote quote);
    //void saveQuote(BlockingQueue<Quote> queue);
}
