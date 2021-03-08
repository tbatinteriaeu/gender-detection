package com.tb.genderdetection.domain;

import com.tb.genderdetection.domain.port.GenderGuessing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class GenderGuesserServiceTest {

    @Autowired
    GenderGuessing genderGuessing;

    @Test
    public void shouldGuessMaleBySingleName() {
        Assertions.assertEquals(Gender.MALE, genderGuessing.guessBySingleName("Jan"));
    }

    public void shouldGuessMaleByMultipleEvenNames() {
        Assertions.assertEquals(Gender.FEMALE, genderGuessing.guessByMultipleNames("Jan Maria"));
    }

    public void shouldNotGuessByMultipleEvenNames() {
        Assertions.assertEquals(Gender.INCONCLUSIVE, genderGuessing.guessByMultipleNames("Jan Maria"));
    }

    public void shouldGuessFemaleByMultipleOddNames() {
        Assertions.assertEquals(Gender.FEMALE, genderGuessing.guessByMultipleNames("Anna Zbigniew Gertruda"));

    }
}
