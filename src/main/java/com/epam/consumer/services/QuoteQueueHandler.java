package com.epam.consumer.services;

import com.epam.common.model.Quote;

public interface QuoteQueueHandler {
    void addQuote(Quote quote);
    Quote getQuote();
}
