package com.tb.genderdetection.adapter;

import com.tb.genderdetection.adapter.repository.MaleNameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;


public class FileScanner {

    InputStream inputStream = null;
    Scanner scanner = null;

    private Logger logger = LoggerFactory.getLogger(FileScanner.class);


    public FileScanner build(String filename) throws IOException {
        inputStream = FileScanner.class.getClassLoader().getResourceAsStream(filename);
        if (inputStream == null)
            throw new IOException(String.format("File %s not found", filename));
        scanner = new Scanner(inputStream, StandardCharsets.UTF_8);

        return this;
    }

    public String findLine(String line) throws IOException {
        try {
            while (scanner.hasNextLine()) {
                if (scanner.nextLine().equals(line)) {
                    return line;
                }
            }
            if (scanner.ioException() != null) {
                throw scanner.ioException();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return null;
    }

    public Integer countTokens(List<String> tokens) {
        Integer count = 0;
        while (scanner.hasNextLine() && !tokens.isEmpty()) {
            String line = scanner.nextLine();
            if (tokens.contains(line)) {
                tokens.remove(line);
                count++;
            }
        }
        return count;
    }

    public static FileScanner getNewInstance(String fileName) throws IOException {
        FileScanner fileScanner = new FileScanner();
        fileScanner.build(fileName);
        return fileScanner;
    }
}
