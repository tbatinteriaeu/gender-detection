package com.tb.genderdetection.domain;

import com.tb.genderdetection.adapter.Tokenizer;
import com.tb.genderdetection.adapter.repository.FemaleNameRepository;
import com.tb.genderdetection.adapter.repository.MaleNameRepository;
import com.tb.genderdetection.domain.port.GenderGuessing;
import com.tb.genderdetection.domain.port.NameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GenderGuesserMultipleService implements GenderGuessing {

    private final MaleNameRepository maleNameRepository;
    private final FemaleNameRepository femaleNameRepository;

    @Autowired
    public GenderGuesserMultipleService(MaleNameRepository maleRepository, FemaleNameRepository femaleRepository) {
        maleNameRepository = maleRepository;
        femaleNameRepository = femaleRepository;
    }

    @Override
    public Gender guessGender(String name) {
        List<String> tokens = new Tokenizer().tokenize(name).getTokens();
        List<String> names = new ArrayList(tokens);
        List<NameRepository> repositories = List.of(maleNameRepository, femaleNameRepository);
        Iterator<NameRepository> repositoriesIterator = repositories.iterator();
        NameRepository repository;
        Map<Gender, Integer> counts = new HashMap<>();
        while(repositoriesIterator.hasNext() && !names.isEmpty()) {
            repository = repositoriesIterator.next();
            counts.put(repository.getGender(), repository.countNames(names));
        }

        return resolveGenderByHits(counts);
    }

    private Gender resolveGenderByHits(Map<Gender, Integer> collection) {
        Integer male = collection.getOrDefault(Gender.MALE, 0);
        Integer female = collection.getOrDefault(Gender.FEMALE, 0);
        if (male > female) {
            return Gender.MALE;
        } else if (male.equals(female))
            return Gender.INCONCLUSIVE;
        else
            return Gender.FEMALE;

    }

}
