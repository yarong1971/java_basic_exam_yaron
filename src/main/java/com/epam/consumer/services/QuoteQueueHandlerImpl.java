package com.epam.consumer.services;

import com.epam.common.model.Quote;
import com.epam.infra.Singleton;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Singleton
public class QuoteQueueHandlerImpl implements QuoteQueueHandler {
    private BlockingQueue<Quote> queue;

    public QuoteQueueHandlerImpl() {
        this.queue = new ArrayBlockingQueue<>(100);
    }

    @Override
    public void addQuote(Quote quote) {
        queue.add(quote);
    }

    @Override
    public Quote getQuote() {
        return this.queue.poll();
    }
}