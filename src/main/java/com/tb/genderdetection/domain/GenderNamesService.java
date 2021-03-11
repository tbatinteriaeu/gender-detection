package com.tb.genderdetection.domain;

import com.tb.genderdetection.adapter.repository.FemaleNameRepository;
import com.tb.genderdetection.adapter.repository.MaleNameRepository;
import com.tb.genderdetection.domain.port.GenderNames;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GenderNamesService implements GenderNames {

    private MaleNameRepository maleNameRepository;
    private FemaleNameRepository femaleNameRepository;

    public GenderNamesService(MaleNameRepository maleNameRepository, FemaleNameRepository femaleNameRepository) {
        this.maleNameRepository = maleNameRepository;
        this.femaleNameRepository = femaleNameRepository;
    }
    @Override
    public Flux<String> getAvailableNames() {
        String lineSeparator = System.getProperty("line.separator");
        Flux<String> maleNames = Flux.fromStream(maleNameRepository.getAsStream());
        Flux<String> femaleNames = Flux.fromStream(femaleNameRepository.getAsStream());
        Flux<String> tokensFlux = Flux.zip(maleNames, femaleNames)
                .flatMap(dFlux ->
                        Flux.just(dFlux.getT1() + lineSeparator, dFlux.getT2() + lineSeparator));

        return tokensFlux;
    }
}
