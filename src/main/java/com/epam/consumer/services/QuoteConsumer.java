package com.epam.consumer.services;

import com.epam.common.model.Quote;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public interface QuoteConsumer {
    void readQuote(BlockingQueue<Quote> queue);
    void saveQuote(BlockingQueue<Quote> queue);

}
