package com.epam.consumer.services;

import com.epam.common.model.Quote;
import lombok.AllArgsConstructor;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;

@AllArgsConstructor
public class QuoteConsumerImpl implements QuoteConsumer {
    private FileHandler handler;
    private QuoteReader reader;
    private QuoterSaver saver;

    @Override
    public Optional<String> getFile() {
        return handler.getFile();

        /*if(file.isPresent()){
            return file.get();
        }
        return null;*/
    }

    @Override
    public Quote readQuote(String path) {
        Quote quote;
        quote =  reader.read(path);
        System.out.println("Quote: " + quote);
        handler.deleteFile(path);
        return quote;
    }
    /*@Override
    public void readQuote(File file , BlockingQueue<Quote> queue) {
        Quote quote;
        quote =  reader.read(file);
        System.out.println("Quote: " + quote);
        queue.add(quote);
    }*/

    @Override
    public void saveQuote(Quote quote) {
        if(quote != null) {
            System.out.println(quote + " is saved");
            saver.save(quote);
        }
    }
    /*@Override
    public void saveQuote(BlockingQueue<Quote> queue) {
        Quote quote = queue.poll();
        if(quote != null) {
            System.out.println(quote + " is saved");
            saver.save(quote);
        }
    }*/
}
