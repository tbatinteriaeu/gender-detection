package com.tb.genderdetection.adapter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TokenizerTest {

    Tokenizer tokenizer;

    @BeforeEach
     void setUp() {
        tokenizer = new Tokenizer();
    }
    @Test
    public void shouldReturnFirstTokenFromSingle() {
        //given
        String tokens = "Jan";
        String expected = "Jan";
        //when
        String actual = tokenizer.tokenize(tokens).getFirstToken();
        //then
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void shouldReturnFirstTokenFromMultiple() {
        //given
        String tokens = "Jan Anna Maria";
        String expected = "Jan";
        //when
        String actual = tokenizer.tokenize(tokens).getFirstToken();
        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTokensFromSingle() {
        //given
        String tokens = "Jan";
        List<String> expected = List.of("Jan");
        //when
        List<String> actual = tokenizer.tokenize(tokens).getTokens();
        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnTokensFromMultiple() {
        //given
        String tokens = "Jan Anna Maria";
        List<String> expected = List.of("Jan", "Anna", "Maria");
        //when
        List<String> actual = tokenizer.tokenize(tokens).getTokens();
        //then
        Assertions.assertEquals(expected, actual);
    }
}
