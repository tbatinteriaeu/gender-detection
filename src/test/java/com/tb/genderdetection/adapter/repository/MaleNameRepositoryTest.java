package com.tb.genderdetection.adapter.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class MaleNameRepositoryTest {

    @Autowired
    MaleNameRepository maleNameRepository;
    @Test
    public void shouldFindName() {
        //given
        String name = "Jan";
        //when
        String current = maleNameRepository.findByName(name);
        //then
        Assertions.assertEquals(name, current);
    }

    @Test
    public void shouldNotFindName() {
        //given
        String name = "Anna";
        //when
        String current = maleNameRepository.findByName(name);
        //then
        Assertions.assertNull(current);
    }

    @Test
    public void shouldReturnCountForName() {
        //given
        List<String> names = new ArrayList<>();
        names.add("Adam");
        names.add("Anna");
        names.add("Jan");
        //when
        Integer current = maleNameRepository.countNames(names);
        //then
        Assertions.assertEquals(2, current);
    }

    @Test
    public void shouldReturnStream() {
        //given
        List<String> names = new ArrayList<>();
        names.add("Adam");
        names.add("Jan");
        //when
        Stream<String> current = maleNameRepository.getAsStream();
        //then
        Assertions.assertTrue(Arrays.equals(current.toArray(), names.toArray()));
    }
}
