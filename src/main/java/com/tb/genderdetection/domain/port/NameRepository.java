package com.tb.genderdetection.domain.port;

import com.tb.genderdetection.domain.Gender;

import java.util.List;
import java.util.stream.Stream;

public interface NameRepository {
    String findByName(String name);
    Stream<String> getAsStream();
    Gender getGender();
    Integer countNames(List<String> names);
}
