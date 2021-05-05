package com.epam.consumer.flow;

import com.epam.common.model.Quote;
import com.epam.consumer.services.QuoteConsumer;
import com.epam.consumer.services.QuoterSaver;
import com.epam.consumer.services.QuoteReader;
import lombok.AllArgsConstructor;

import java.io.File;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@AllArgsConstructor
public class QuoterConsumerFlowManager {
    private QuoteConsumer consumer;
    //private BlockingQueue<Quote> queue = new ArrayBlockingQueue<>(1000);

    public void consumeQuote(){

        // Consuming quote without threads
        Quote quote = null;
        Optional<String> file = consumer.getFile();
        if(file.isPresent()){
            System.out.println("Thread " + Thread.currentThread().getId() + ": Reading quote...");
            quote = consumer.readQuote(file.get());

        }

        System.out.println("Thread " + Thread.currentThread().getId() + ": Saving quote...");
        consumer.saveQuote(quote);


        // Consuming quote with threads
        /*new Thread(() -> {
        File file = consumer.getFile();
        if(file != null){
            System.out.println("Thread " + Thread.currentThread().getId() + ": Reading quote...");
            consumer.readQuote(file, queue);
           }
         });

        new Thread(() -> {
            System.out.println("Thread " + Thread.currentThread().getId() + ": Saving quote...");
            consumer.saveQuote(quote); //queue);
            consumer.deleteFile()
        });*/
    }
}
