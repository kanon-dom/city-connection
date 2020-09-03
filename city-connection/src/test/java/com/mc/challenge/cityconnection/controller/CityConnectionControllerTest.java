package com.mc.challenge.cityconnection.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CityConnectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void connectedCitiesShouldReturnYes() throws Exception{
        this.mockMvc
                .perform(get("/connected?origin=Boston&destination=Newark"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("yes")));
    }

    @Test
    public void nonConnectedCitiesShouldReturnNo() throws Exception{
        this.mockMvc
                .perform(get("/connected?origin=Boston&destination=zzz"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("no")));
    }
}
