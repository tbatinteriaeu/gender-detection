package com.tb.genderdetection.adapter.repository;

import com.tb.genderdetection.adapter.FileScanner;
import com.tb.genderdetection.domain.Gender;
import com.tb.genderdetection.domain.port.NameRepository;
import org.apache.tomcat.jni.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

@Repository
public class MaleNameRepository implements NameRepository {

    @Value("${app.db-flat.male}")
    private String fileName;

    private Logger logger = LoggerFactory.getLogger(MaleNameRepository.class);

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
        return Gender.MALE;
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
