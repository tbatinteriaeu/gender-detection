package com.tb.genderdetection.domain;

import com.tb.genderdetection.adapter.repository.FemaleNameRepository;
import com.tb.genderdetection.adapter.repository.MaleNameRepository;
import com.tb.genderdetection.domain.port.GenderGuessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderGuesserService implements GenderGuessing {

    private final MaleNameRepository maleNameRepository;
    private final FemaleNameRepository femaleNameRepository;

    @Autowired
    public GenderGuesserService(MaleNameRepository maleRepository, FemaleNameRepository femaleRepository) {
        maleNameRepository = maleRepository;
        femaleNameRepository = femaleRepository;
    }

    @Override
    public Gender guessGender(String names) {
        return null;
    }

}
