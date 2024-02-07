package com.shipmonk.testingday;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
class ExchangeApiTest {
    @Test
    void contextLoads(@Autowired MockMvc mockMvc) throws Exception {
        //Use something like JSONAssert?
        mockMvc.perform(get("/api/v1/rates/2013-12-24"))
            .andExpect(status().isOk());
    }
}
