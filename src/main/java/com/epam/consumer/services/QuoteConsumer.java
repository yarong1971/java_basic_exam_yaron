package com.epam.consumer.services;

import com.epam.common.model.Quote;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public interface QuoteConsumer {
    File getFile();
    void readQuote(File file, BlockingQueue<Quote> queue);
    void saveQuote(BlockingQueue<Quote> queue);

}
