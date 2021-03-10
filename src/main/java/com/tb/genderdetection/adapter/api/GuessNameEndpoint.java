package com.tb.genderdetection.adapter.api;

import com.tb.genderdetection.domain.Gender;
import com.tb.genderdetection.domain.GenderGuesserMultipleService;
import com.tb.genderdetection.domain.GenderGuesserSingleService;
import com.tb.genderdetection.domain.port.GenderGuessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("guess")
public class GuessNameEndpoint {

    @Autowired
    public GuessNameEndpoint(GenderGuessing genderGuesserSingleService, GenderGuessing genderGuesserMultipleService) {
        this.genderGuesserSingleService = genderGuesserSingleService;
        this.genderGuesserMultipleService = genderGuesserMultipleService;
    }


    GenderGuessing genderGuesserMultipleService;

    GenderGuessing genderGuesserSingleService;

    @GetMapping(path = "/single/{name}")
    Gender getGuessSingle(@PathVariable("name") final String name ) {
        return genderGuesserSingleService.guessGender(name);
    }

    @GetMapping(path = "/multi/{name}")
    Gender getGuessMulti(@PathVariable("name") final String name ) {
        return genderGuesserMultipleService.guessGender(name);
    }
}
