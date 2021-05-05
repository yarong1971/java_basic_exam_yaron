package com.epam.consumer.flow;

import com.epam.common.model.Quote;
import com.epam.consumer.services.QuoteConsumer;
import lombok.AllArgsConstructor;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@AllArgsConstructor
public class QuoterConsumerFlowManager {
    private QuoteConsumer consumer;


    public void consumeQuote(){
        // Consuming quote with single thread
        /*Quote quote = null;
        File file = consumer.getFile();
        if(file != null){
            System.out.println("Thread " + Thread.currentThread().getId() + ": Reading quote...");
            quote = consumer.readQuote(file);
        } else {
            System.out.println("File not found. directory is empty");
        }

        System.out.println("Thread " + Thread.currentThread().getId() + ": Saving quote...");
        consumer.saveQuote(quote);*/


        // Consuming quote with multiple threads
        new Thread(() -> {
            File file = consumer.getFile();
            if(file != null){
                System.out.println("Thread " + Thread.currentThread().getId() + ": Reading quote...");
                consumer.readQuote(file);
           }
         }).start();

        new Thread(() -> {
            System.out.println("Thread " + Thread.currentThread().getId() + ": Saving quote...");
            consumer.saveQuote();
        }).start();
    }
}
