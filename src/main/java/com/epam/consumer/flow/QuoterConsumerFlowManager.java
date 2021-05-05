package com.epam.consumer.flow;

import com.epam.common.model.Quote;
import com.epam.consumer.services.QuoteConsumer;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class QuoterConsumerFlowManager {
    private QuoteConsumer consumer;


    @SneakyThrows
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
        Thread t1 = new Thread(() -> ReadAndLoadQuoteToQueue());
        /*{
            File file = consumer.getFile();
            if(file != null){
                System.out.println("Thread " + Thread.currentThread().getId() + ": Reading quote...");
                consumer.readQuote(file);
           }
         });*/

        Thread t2 = new Thread(() ->  GetQuoteFromQueueAndSaveToJson());
/*
                {
                    System.out.println("Thread " + Thread.currentThread().getId() + ": Saving quote...");
                    consumer.saveQuote();
                });
*/

        t1.start();
        t2.start();
        Thread.currentThread().join();
    }

    @SneakyThrows
    private void ReadAndLoadQuoteToQueue() {
        do {
            File file = consumer.getFile();
            if (file != null) {
                System.out.println("Thread " + Thread.currentThread().getId() + ": Reading quote...");
                consumer.readQuote(file);
            }
            TimeUnit.SECONDS.sleep(3);
        } while(true);
    }

    @SneakyThrows
    private void GetQuoteFromQueueAndSaveToJson(){
        do {
            System.out.println("Thread " + Thread.currentThread().getId() + ": Saving quote...");
            consumer.saveQuote();
            TimeUnit.SECONDS.sleep(3);
        } while(true);
    }
}
