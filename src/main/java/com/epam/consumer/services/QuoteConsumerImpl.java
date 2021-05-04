package com.epam.consumer.services;

import com.epam.common.model.Quote;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QuoteConsumerImpl implements QuoteConsumer {
    private QuoteReader reader;
    private QuoterSaver saver;

    @Override
    public void readQuote(BlockingQueue<Quote> queue) {
        Quote quote;
        quote =  reader.read();
        System.out.println("Quote: " + quote);
        queue.add(quote);
    }

    @Override
    public void saveQuote(BlockingQueue<Quote> queue) {
        Quote quote = queue.poll();
        if(quote != null) {
            System.out.println(quote + " is saved");
            saver.save(quote);
        }
    }
}
