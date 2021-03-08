package com.tb.genderdetection.domain;

import com.tb.genderdetection.domain.port.GenderGuessing;
import org.springframework.stereotype.Service;

@Service
public class GenderGuesserService implements GenderGuessing {

    @Override
    public Gender guessBySingleName(String name) {
        return null;
    }

    @Override
    public Gender guessByMultipleNames(String names) {
        return null;
    }
}
