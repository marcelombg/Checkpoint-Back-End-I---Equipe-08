package com.example.CheckpointBackEndIEquipe08.controller;

import com.example.CheckpointBackEndIEquipe08.entity.dto.DentistaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static com.example.CheckpointBackEndIEquipe08.utils.Utils.asJsonString;
import static com.example.CheckpointBackEndIEquipe08.utils.Utils.objectFromString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@AutoConfigureMockMvc
class DentistaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void init(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(username = "Teste", password = "123456", roles = "ADMIN")
    void registrar() throws Exception{
        DentistaDTO dentistaDTO = new DentistaDTO();

        dentistaDTO.setNome("Dentista teste 1");
        dentistaDTO.setSobrenome("Dentista teste 1");
        dentistaDTO.setMatriculaCadastro(123456);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/dentista/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dentistaDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        dentistaDTO = objectFromString(DentistaDTO.class, responseBody);
        assertNotNull(dentistaDTO.getId());
    }

    @Test
    @WithMockUser(username = "Teste", password = "123456", roles = "ADMIN")
    void buscarId() throws Exception {
        DentistaDTO dentistaDTO = new DentistaDTO();

        dentistaDTO.setNome("Dentista teste 1");
        dentistaDTO.setSobrenome("Dentista teste 1");
        dentistaDTO.setMatriculaCadastro(123456);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/dentista/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dentistaDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        dentistaDTO = objectFromString(DentistaDTO.class, responseBody);

        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/dentista/{id}", dentistaDTO.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        responseBody = mvcResult.getResponse().getContentAsString();

        DentistaDTO dentistaDTO1 = objectFromString(DentistaDTO.class, responseBody);

        assertEquals(dentistaDTO.getId(), dentistaDTO1.getId());
        assertEquals(dentistaDTO.getNome(), dentistaDTO1.getNome());
    }

    @Test
    @WithMockUser(username = "Teste", password = "123456", roles = "ADMIN")
    void buscarTodos() throws Exception {
        DentistaDTO dentistaDTO = new DentistaDTO();

        dentistaDTO.setNome("Dentista teste 1");
        dentistaDTO.setSobrenome("Dentista teste 1");
        dentistaDTO.setMatriculaCadastro(123456);

        DentistaDTO dentistaDTO1 = new DentistaDTO();

        dentistaDTO1.setNome("Dentista teste 2");
        dentistaDTO1.setSobrenome("Dentista teste 2");
        dentistaDTO1.setMatriculaCadastro(7890);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/dentista/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dentistaDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MvcResult mvcResult2 = mockMvc.perform(MockMvcRequestBuilders.post("/dentista/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dentistaDTO1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        dentistaDTO = objectFromString(DentistaDTO.class, responseBody);
        dentistaDTO1 = objectFromString(DentistaDTO.class, responseBody);

        mockMvc.perform(MockMvcRequestBuilders.get("/dentista/buscar", dentistaDTO.getId(), dentistaDTO1.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "Teste", password = "123456", roles = "ADMIN")
    void excluir() throws Exception{
        DentistaDTO dentistaDTO = new DentistaDTO();

        dentistaDTO.setNome("Dentista teste 1");
        dentistaDTO.setSobrenome("Dentista teste 1");
        dentistaDTO.setMatriculaCadastro(123456);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/dentista/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dentistaDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        dentistaDTO = objectFromString(DentistaDTO.class, responseBody);

        mockMvc.perform(MockMvcRequestBuilders.delete("/dentista/{id}", dentistaDTO.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}