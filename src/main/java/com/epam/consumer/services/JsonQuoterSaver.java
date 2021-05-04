package com.epam.consumer.services;

import com.epam.common.model.Quote;
import com.epam.infra.InjectValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonQuoterSaver implements QuoterSaver {

    @InjectValue("consumer_output_location")
    private String locationDir;

    @SneakyThrows
    @Override
    public void save(Quote quote) {

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String filename = locationDir + "quote_" + timeStamp + ".json";

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new FileOutputStream(filename), quote);
    }
}
