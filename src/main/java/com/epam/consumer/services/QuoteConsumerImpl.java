package com.epam.consumer.services;

import com.epam.common.model.Quote;
import lombok.AllArgsConstructor;

import java.io.File;

@AllArgsConstructor
public class QuoteConsumerImpl implements QuoteConsumer {
    private FileHandler handler;
    private QuoteQueueHandler queueHandler;
    private QuoteReader reader;
    private QuoterSaver saver;

    @Override
    public File getFile() {
        return handler.getFile();
    }

    //@Override
    /*public Quote readQuote(File file) {
        Quote quote;
        quote =  reader.read(file);
        System.out.println("Quote: " + quote);
        handler.deleteFile(file);
        return quote;
    }*/
    @Override
    public void readQuote(File file) {
        Quote quote;
        quote =  reader.read(file);
        System.out.println("Quote: " + quote);
        queueHandler.addQuote(quote);
    }

    //@Override
    /*public void saveQuote(Quote quote) {
        if(quote != null) {
            System.out.println(quote + " is saved");
            saver.save(quote);
        }
    }*/

    @Override
    public void saveQuote() {
        Quote quote = queueHandler.getQuote();
        if(quote != null) {
            System.out.println(quote + " is saved");
            saver.save(quote);
        }
    }
}
