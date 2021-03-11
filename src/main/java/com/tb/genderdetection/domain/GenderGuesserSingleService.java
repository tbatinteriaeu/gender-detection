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
public class GenderGuesserSingleService implements GenderGuessing {

    private final MaleNameRepository maleNameRepository;
    private final FemaleNameRepository femaleNameRepository;

    @Autowired
    public GenderGuesserSingleService(MaleNameRepository maleRepository, FemaleNameRepository femaleRepository) {
        maleNameRepository = maleRepository;
        femaleNameRepository = femaleRepository;
    }

    @Override
    public Gender guessGender(String name)  {
        String token = new Tokenizer().tokenize(name).getFirstToken();
        List<NameRepository> repositories = List.of(maleNameRepository, femaleNameRepository);
        for (NameRepository repository: repositories) {
            if (repository.findByName(token) != null) {
                return repository.getGender();
            }
        }

        return Gender.INCONCLUSIVE;
    }
}
