package com.example.CheckpointBackEndIEquipe08.controller;

import com.example.CheckpointBackEndIEquipe08.entity.EnderecoEntity;
import com.example.CheckpointBackEndIEquipe08.entity.dto.EnderecoDTO;
import com.example.CheckpointBackEndIEquipe08.entity.dto.PacienteDTO;
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
import java.time.Instant;
import java.util.Date;

import static com.example.CheckpointBackEndIEquipe08.utils.Utils.asJsonString;
import static com.example.CheckpointBackEndIEquipe08.utils.Utils.objectFromString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@AutoConfigureMockMvc
class PacienteControllerTest {

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
    void registrarEndereco() throws Exception {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setRua("Rua teste 1");
        enderecoDTO.setNumero(1);
        enderecoDTO.setCidade("Cidade teste 1");
        enderecoDTO.setEstado("Estado teste 1");
        enderecoDTO.setPais("Pais teste 1");
        enderecoDTO.setCEP("CEP teste 1");

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
    void registrarPaciente() throws Exception {
        EnderecoEntity endereco = new EnderecoEntity(new EnderecoDTO(1,"A",1, "A", "A", "A", "A"));

        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNome("Paciente teste 1");
        pacienteDTO.setSobrenome("Paciente teste 1");
        pacienteDTO.setEndereco(endereco);
        pacienteDTO.setDataAlta(Date.from(Instant.now()));
        pacienteDTO.setRG("11111111-1");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/paciente/registrar")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(asJsonString(pacienteDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        pacienteDTO = objectFromString(PacienteDTO.class, responseBody);
        assertNotNull(pacienteDTO.getId());
    }
}