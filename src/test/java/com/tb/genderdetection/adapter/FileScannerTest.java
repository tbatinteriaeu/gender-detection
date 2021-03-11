package com.tb.genderdetection.adapter;

import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileScannerTest {

    @Test
    public void shouldThrowExceptionWhenReturningNewInstance() {
        Exception exception = assertThrows(IOException.class, () -> {
            FileScanner.getNewInstance("not-exists.txt");
        });

        String expectedMessage = "File not-exists.txt not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
