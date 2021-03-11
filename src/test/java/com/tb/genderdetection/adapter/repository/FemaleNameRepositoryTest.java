package com.tb.genderdetection.adapter.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

@SpringBootTest
public class FemaleNameRepositoryTest {

    @Autowired
    FemaleNameRepository femaleNameRepository;
    @Test
    public void shouldFindName() {
        //given
        String name = "Maria";
        //when
        String current = femaleNameRepository.findByName(name);
        //then
        Assertions.assertEquals(name, current);
    }

    @Test
    public void shouldNotFindName() {
        //given
        String name = "Jan";
        //when
        String current = femaleNameRepository.findByName(name);
        //then
        Assertions.assertNull(current);
    }

    @Test
    public void shouldReturnCountForName() {
        //given
        List<String> names = new ArrayList<>();
        names.add("Anna");
        names.add("Maria");
        names.add("Jan");
        //when
        Integer current = femaleNameRepository.countNames(names);
        //then
        Assertions.assertEquals(2, current);
    }

    @Test
    public void shouldReturnStream() {
        //given
        List<String> names = new ArrayList<>();
        names.add("Anna");
        names.add("Maria");
        //when
        Stream<String> current = femaleNameRepository.getAsStream();
        //then
        Assertions.assertTrue(Arrays.equals(current.toArray(), names.toArray()));
    }
}
