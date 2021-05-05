package com.epam.consumer;

import com.epam.infra.ApplicationContext;
import com.epam.infra.InjectValue;
import com.epam.infra.JavaConfig;
import com.epam.consumer.flow.QuoterConsumerFlowManager;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class MainConsumer {
    @SneakyThrows
    public static void main(String[] args) {

        ApplicationContext context = new ApplicationContext(JavaConfig.builder().packagesToScan("com.epam").build());
        QuoterConsumerFlowManager flowManager = context.getObject(QuoterConsumerFlowManager.class);
        flowManager.consumeQuote();

        /*while(true){
            flowManager.consumeQuote();
            Thread.sleep(3000);
        }*/
    }
}
