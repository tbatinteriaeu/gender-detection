package com.tb.genderdetection.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
class GenderGuesserSingleServiceTest {

    @Autowired
    GenderGuesserSingleService genderGuesserSingleService;

    @ParameterizedTest
    @MethodSource("nameProvider")
    @DisplayName("should correct guess a name by given token")
    public void shouldGuessName(String actual, Gender expected) {
        Assertions.assertEquals(expected, genderGuesserSingleService.guessGender(actual));
    }

    private static Stream<Arguments> nameProvider() {
        return Stream.of(
                Arguments.of("Jan", Gender.MALE),
                Arguments.of("Maria", Gender.FEMALE),
                Arguments.of("Jan Maria", Gender.MALE),
                Arguments.of("Jan Maria Anna", Gender.MALE),
                Arguments.of("Maria Jan Adam", Gender.FEMALE),
                Arguments.of("not-exist", Gender.INCONCLUSIVE)
        );
    }
}
