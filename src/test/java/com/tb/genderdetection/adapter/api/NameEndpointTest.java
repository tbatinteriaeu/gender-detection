package com.tb.genderdetection.adapter.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NameEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnCorrectGenderForMultipleToken() throws Exception {
        this.mockMvc.perform(get("/name/get-all"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Adam")))
                .andExpect(content().string(containsString("Jan")))
                .andExpect(content().string(containsString("Maria")))
                .andExpect(content().string(containsString("Anna")));
    }
}
