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
        EnderecoEntity endereco = new EnderecoEntity(new EnderecoDTO(1,"Rua teste 1",1, "Rua cidade 1", "Rua estado 1", "Rua pais 1", "11111-111"));

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

    @Test
    @WithMockUser(username = "Teste", password = "123456", roles = "ADMIN")
    void buscarId() throws Exception {
        EnderecoEntity endereco = new EnderecoEntity(new EnderecoDTO(1,"Rua teste 1",1, "Rua cidade 1", "Rua estado 1", "Rua pais 1", "11111-111"));

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

        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/paciente/{id}", pacienteDTO.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        responseBody = mvcResult.getResponse().getContentAsString();

        PacienteDTO pacienteDTO1 = objectFromString(PacienteDTO.class, responseBody);

        assertEquals(pacienteDTO.getId(), pacienteDTO1.getId());
        assertEquals(pacienteDTO.getNome(), pacienteDTO1.getNome());
    }

    @Test
    @WithMockUser(username = "Teste", password = "123456", roles = "ADMIN")
    void buscarTodos() throws Exception {
        EnderecoEntity endereco = new EnderecoEntity(new EnderecoDTO(1,"Rua teste 1",1, "Rua cidade 1", "Rua estado 1", "Rua pais 1", "11111-111"));

        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNome("Paciente teste 1");
        pacienteDTO.setSobrenome("Paciente teste 1");
        pacienteDTO.setEndereco(endereco);
        pacienteDTO.setDataAlta(Date.from(Instant.now()));
        pacienteDTO.setRG("11111111-1");

        PacienteDTO pacienteDTO1 = new PacienteDTO();
        pacienteDTO1.setNome("Paciente teste 2");
        pacienteDTO1.setSobrenome("Paciente teste 2");
        pacienteDTO1.setEndereco(endereco);
        pacienteDTO1.setDataAlta(Date.from(Instant.now()));
        pacienteDTO1.setRG("22222222-2");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/paciente/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pacienteDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MvcResult mvcResult2 = mockMvc.perform(MockMvcRequestBuilders.post("/paciente/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pacienteDTO1)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        pacienteDTO = objectFromString(PacienteDTO.class, responseBody);
        pacienteDTO1 = objectFromString(PacienteDTO.class, responseBody);

        mockMvc.perform(MockMvcRequestBuilders.get("/paciente/buscar", pacienteDTO.getId(), pacienteDTO1.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @WithMockUser(username = "Teste", password = "123456", roles = "ADMIN")
    void excluir() throws Exception{
        EnderecoEntity endereco = new EnderecoEntity(new EnderecoDTO(1,"Rua teste 1",1, "Rua cidade 1", "Rua estado 1", "Rua pais 1", "11111-111"));

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

        mockMvc.perform(MockMvcRequestBuilders.delete("/paciente/{id}", pacienteDTO.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}