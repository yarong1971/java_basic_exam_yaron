package com.epam.consumer.services;

import com.epam.common.model.Quote;

/**
 * @author Evgeny Borisov
 */
public interface QuoterSaver {
    void save(Quote quote);
}
