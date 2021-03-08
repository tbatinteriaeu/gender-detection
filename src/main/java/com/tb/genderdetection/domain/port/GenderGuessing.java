package com.tb.genderdetection.domain.port;

import com.tb.genderdetection.domain.Gender;

public interface GenderGuessing {
    Gender guessBySingleName(String name);
    Gender guessByMultipleNames(String names);
}
