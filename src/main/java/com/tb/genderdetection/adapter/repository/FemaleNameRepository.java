package com.tb.genderdetection.adapter.repository;

import com.tb.genderdetection.adapter.FileScanner;
import com.tb.genderdetection.domain.Gender;
import com.tb.genderdetection.domain.port.NameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

@Repository
public class FemaleNameRepository implements NameRepository {

    private Logger logger = LoggerFactory.getLogger(FemaleNameRepository.class);

    @Value("${app.db-flat.female}")
    private String fileName;

    @Override
    public String findByName(String name) {
        String found = null;
        try {
            found = FileScanner.getNewInstance(fileName).findLine(name);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return found;
    }

    public Stream<String> getAsStream() {
        String fname;
        try {
            fname = new ClassPathResource(fileName).getFile().getAbsoluteFile().getPath();
            return Files.lines(Path.of(fname));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public Gender getGender() {
        return Gender.FEMALE;
    }

    @Override
    public Integer countNames(List<String> names) {
        Integer count = 0;
        try {
            count = FileScanner.getNewInstance(fileName).countTokens(names);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return count;
    }
}
