package com.example.CheckpointBackEndIEquipe08.controller;

import com.example.CheckpointBackEndIEquipe08.entity.dto.EnderecoDTO;
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
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class EnderecoControllerTest {

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
    void registrar() throws Exception {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setRua("Endereço teste 1");
        enderecoDTO.setNumero(1);
        enderecoDTO.setCidade("Endereço teste 1");
        enderecoDTO.setEstado("Endereço teste 1");
        enderecoDTO.setPais("Endereço teste 1");
        enderecoDTO.setCEP("11111-111");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/endereco/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(enderecoDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        enderecoDTO = objectFromString(EnderecoDTO .class, responseBody);
        assertNotNull(enderecoDTO.getId());
    }

    @Test
    @WithMockUser(username = "Teste", password = "123456", roles = "ADMIN")
    void buscarId() throws Exception {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setRua("Endereço teste 1");
        enderecoDTO.setNumero(1);
        enderecoDTO.setCidade("Endereço teste 1");
        enderecoDTO.setEstado("Endereço teste 1");
        enderecoDTO.setPais("Endereço teste 1");
        enderecoDTO.setCEP("11111-111");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/endereco/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(enderecoDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        enderecoDTO = objectFromString(EnderecoDTO.class, responseBody);

        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/endereco/{id}", enderecoDTO.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        responseBody = mvcResult.getResponse().getContentAsString();

        EnderecoDTO enderecoDTO1 = objectFromString(EnderecoDTO.class, responseBody);

        assertEquals(enderecoDTO.getId(), enderecoDTO1.getId());
        assertEquals(enderecoDTO.getRua(), enderecoDTO1.getRua());
    }

    @Test
    @WithMockUser(username = "Teste", password = "123456", roles = "ADMIN")
    void buscarTodos() throws Exception {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setRua("Endereço teste 1");
        enderecoDTO.setNumero(1);
        enderecoDTO.setCidade("Endereço teste 1");
        enderecoDTO.setEstado("Endereço teste 1");
        enderecoDTO.setPais("Endereço teste 1");
        enderecoDTO.setCEP("11111-111");

        EnderecoDTO enderecoDTO1 = new EnderecoDTO();
        enderecoDTO1.setRua("Endereço teste 2");
        enderecoDTO1.setNumero(1);
        enderecoDTO1.setCidade("Endereço teste 2");
        enderecoDTO1.setEstado("Endereço teste 2");
        enderecoDTO1.setPais("Endereço teste 2");
        enderecoDTO1.setCEP("22222-222");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/endereco/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(enderecoDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MvcResult mvcResult2 = mockMvc.perform(MockMvcRequestBuilders.post("/endereco/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(enderecoDTO1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        enderecoDTO = objectFromString(EnderecoDTO.class, responseBody);
        enderecoDTO1 = objectFromString(EnderecoDTO.class, responseBody);

        mockMvc.perform(MockMvcRequestBuilders.get("/endereco/buscar", enderecoDTO.getId(), enderecoDTO1.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "Teste", password = "123456", roles = "ADMIN")
    void excluir() throws Exception{
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setRua("Endereço teste 1");
        enderecoDTO.setNumero(1);
        enderecoDTO.setCidade("Endereço teste 1");
        enderecoDTO.setEstado("Endereço teste 1");
        enderecoDTO.setPais("Endereço teste 1");
        enderecoDTO.setCEP("11111-111");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/endereco/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(enderecoDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        enderecoDTO = objectFromString(EnderecoDTO.class, responseBody);

        mockMvc.perform(MockMvcRequestBuilders.delete("/endereco/{id}", enderecoDTO.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}