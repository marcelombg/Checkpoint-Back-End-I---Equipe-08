package com.example.CheckpointBackEndIEquipe08;
import com.example.CheckpointBackEndIEquipe08.controller.DentistaController;
import com.example.CheckpointBackEndIEquipe08.entity.DentistaEntity;
import com.example.CheckpointBackEndIEquipe08.entity.dto.DentistaDTO;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(DentistaController.class)
class CheckpointBackEndIEquipe08ApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DentistaController dentistaController;

    @Test
    void testeDumith() throws Exception {
//        this.mockMvc.perform(get("/buscar").queryParam("howMany","1")).andExpect(MockMvcResultMatchers.status().isOk());
    }
}

