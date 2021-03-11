package com.tb.genderdetection.adapter.api;

import com.tb.genderdetection.adapter.repository.FemaleNameRepository;
import com.tb.genderdetection.adapter.repository.MaleNameRepository;
import com.tb.genderdetection.domain.Gender;
import com.tb.genderdetection.domain.GenderNamesService;
import com.tb.genderdetection.domain.port.GenderGuessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("name")
public class NameEndpoint {

    private GenderNamesService genderNamesService;

    public NameEndpoint(GenderNamesService genderNamesService){
        this.genderNamesService = genderNamesService;
    }

    @Autowired
    private FemaleNameRepository femaleRepository;

    @Autowired
    private MaleNameRepository maleNameRepository;

    @Autowired
    @Qualifier("genderGuesserSingleService")
    private GenderGuessing genderGuessingSingleService;

    @Autowired
    @Qualifier("genderGuesserMultipleService")
    private GenderGuessing genderGuessingMultipleService;

    @GetMapping("guess/{name}")
    Gender guessGender(@PathVariable("name") final String name) {
        return null;
    }

    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    Flux<String> getAll() {
        return  genderNamesService.getAvailableNames();
    }
}
