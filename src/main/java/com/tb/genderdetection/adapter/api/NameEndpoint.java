package com.tb.genderdetection.adapter.api;

import com.tb.genderdetection.adapter.FileScanner;
import com.tb.genderdetection.adapter.repository.FemaleNameRepository;
import com.tb.genderdetection.adapter.repository.MaleNameRepository;
import com.tb.genderdetection.domain.Gender;
import com.tb.genderdetection.domain.GenderNamesService;
import com.tb.genderdetection.domain.port.GenderGuessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @GetMapping(path = "/get-all-flux", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    Flux<String> getAllFlux() throws IOException {

        Flux<String> fnameFlux = Flux.just("Ramesh","Amit","Vijay");
        Flux<String> lnameFlux = Flux.just("Sharma","Kumar","Lamba");
        Flux<String> deptFlux = Flux.just("Admin","IT","Acc.");

        Flux<String> userFlux = Flux.zip(fnameFlux, lnameFlux, deptFlux)
                .flatMap(dFlux ->
                        Flux.just(dFlux.getT1(), dFlux.getT2(), dFlux.getT3())
                );

        userFlux.subscribe(x -> System.out.println(x));


        return  userFlux;


//        return Flux.from(fileScanner.getLines());
//        return fileScanner.getLines().map(e -> {
//            Flux.just(e);
//            return e;
//        });
    }

}
