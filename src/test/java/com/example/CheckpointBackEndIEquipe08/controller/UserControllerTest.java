package com.example.CheckpointBackEndIEquipe08.controller;

import com.example.CheckpointBackEndIEquipe08.entity.dto.UserDTO;
import com.example.CheckpointBackEndIEquipe08.enums.UserRoles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static com.example.CheckpointBackEndIEquipe08.utils.Utils.asJsonString;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void create1() throws Exception{
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Teste");
        userDTO.setUsername("Teste");
        userDTO.setEmail("teste@teste.com");
        userDTO.setPassword("123456");
        userDTO.setUserRoles(UserRoles.ROLE_ADMIN);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void create2() throws Exception{
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Teste2");
        userDTO.setUsername("Teste2");
        userDTO.setEmail("teste2@teste2.com");
        userDTO.setPassword("1234567");
        userDTO.setUserRoles(UserRoles.ROLE_PACIENTE);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void create3() throws Exception{
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Teste3");
        userDTO.setUsername("Teste3");
        userDTO.setEmail("teste3@teste3.com");
        userDTO.setPassword("1234568");
        userDTO.setUserRoles(UserRoles.ROLE_DENTISTA);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDTO)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}