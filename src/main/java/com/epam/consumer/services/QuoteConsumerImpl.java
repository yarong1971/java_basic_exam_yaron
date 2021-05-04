package com.epam.consumer.services;

import com.epam.common.model.Quote;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.File;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QuoteConsumerImpl implements QuoteConsumer {
    private FileHandler handler;
    private QuoteReader reader;
    private QuoterSaver saver;

    @Override
    public File getFile() {
        Optional<File> file = handler.handleFile();
        if(file.isPresent()){
            return file.get();
        }
        return null;
    }

    @Override
    public void readQuote(File file, BlockingQueue<Quote> queue) {
        Quote quote;
        quote =  reader.read(file);
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
