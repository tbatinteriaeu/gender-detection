package com.tb.genderdetection.adapter;

import java.util.Arrays;
import java.util.List;

public class Tokenizer {

    private List<String> tokens = null;

    public Tokenizer tokenize(String text) {
        tokens = Arrays.asList(text.split("\\s+"));
        return this;
    }

    public String getFirstToken() {
        if (!tokens.isEmpty()) {
            return tokens.get(0);
        }
        return null;
    }

    public List<String> getTokens() {
        return tokens;
    }

}
