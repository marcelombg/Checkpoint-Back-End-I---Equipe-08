package com.example.CheckpointBackEndIEquipe08.controller;

import com.example.CheckpointBackEndIEquipe08.entity.DentistaEntity;
import com.example.CheckpointBackEndIEquipe08.entity.EnderecoEntity;
import com.example.CheckpointBackEndIEquipe08.entity.PacienteEntity;
import com.example.CheckpointBackEndIEquipe08.entity.dto.ConsultaDTO;
import com.example.CheckpointBackEndIEquipe08.entity.dto.DentistaDTO;
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
import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import static com.example.CheckpointBackEndIEquipe08.utils.Utils.asJsonString;
import static com.example.CheckpointBackEndIEquipe08.utils.Utils.objectFromString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
@AutoConfigureMockMvc
class ConsultaControllerTest {

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
    void registrar1() throws Exception {
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
    void registrar2() throws Exception {
        EnderecoEntity endereco = new EnderecoEntity(new EnderecoDTO(1,"Rua teste 1",1, "Rua cidade 1", "Rua estado 1", "Rua pais 1", "11111-111"));

        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNome("Paciente nome teste");
        pacienteDTO.setSobrenome("Paciente Sobrenome teste");
        pacienteDTO.setEndereco(endereco);
        pacienteDTO.setDataAlta(java.util.Date.from(Instant.now()));
        pacienteDTO.setRG("12345654");

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
    void registrar3() throws Exception{
        DentistaDTO dentistaDTO = new DentistaDTO();

        dentistaDTO.setNome("Nome teste 1");
        dentistaDTO.setSobrenome("Sobrenome teste 1");
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
    void registrar4() throws Exception {
        EnderecoEntity endereco = new EnderecoEntity(new EnderecoDTO(1,"Endereço teste 1",1, "Endereço teste 1", "Endereço teste 1", "Endereço teste 1", "11111-111"));
        DentistaEntity dentista = new DentistaEntity(new DentistaDTO(1, "Dentista teste 1", "Dentista teste 1", 123456));
        PacienteEntity paciente = new PacienteEntity(new PacienteDTO(1, "Paciente teste 1", "Paciente teste 1", endereco, "11111111-1", Date.from(Instant.now())));
        ConsultaDTO consultaDTO = new ConsultaDTO();

        consultaDTO.setPaciente(paciente);
        consultaDTO.setDentista(dentista);
        consultaDTO.setData(Date.valueOf(LocalDate.now()));
        consultaDTO.setHora(Time.valueOf(LocalTime.now()));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/consulta/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(consultaDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();
        consultaDTO = objectFromString(ConsultaDTO.class, responseBody);

        assertNotNull(consultaDTO.getId());
    }

    @Test
    @WithMockUser(username = "Teste", password = "123456", roles = "ADMIN")
    void excluir() throws Exception{
        EnderecoEntity endereco = new EnderecoEntity(new EnderecoDTO(1,"Endereço teste 1",1, "Endereço teste 1", "Endereço teste 1", "Endereço teste 1", "11111-111"));
        DentistaEntity dentista = new DentistaEntity(new DentistaDTO(1, "Dentista teste 1", "Dentista teste 1", 123456));
        PacienteEntity paciente = new PacienteEntity(new PacienteDTO(1, "Paciente teste 1", "Paciente teste 1", endereco, "11111111-1", Date.from(Instant.now())));
        ConsultaDTO consultaDTO = new ConsultaDTO();

        consultaDTO.setPaciente(paciente);
        consultaDTO.setDentista(dentista);
        consultaDTO.setData(Date.valueOf(LocalDate.now()));
        consultaDTO.setHora(Time.valueOf(LocalTime.now()));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/consulta/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(consultaDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        consultaDTO = objectFromString(ConsultaDTO.class, responseBody);

        mockMvc.perform(MockMvcRequestBuilders.delete("/consulta/{id}", consultaDTO.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}