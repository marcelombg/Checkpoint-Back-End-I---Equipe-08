package com.example.CheckpointBackEndIEquipe08;
import com.example.CheckpointBackEndIEquipe08.controller.DentistaController;
import com.example.CheckpointBackEndIEquipe08.controller.EnderecoController;
import com.example.CheckpointBackEndIEquipe08.controller.PacienteController;
import com.example.CheckpointBackEndIEquipe08.entity.DentistaEntity;
import com.example.CheckpointBackEndIEquipe08.entity.EnderecoEntity;
import com.example.CheckpointBackEndIEquipe08.entity.dto.DentistaDTO;
import com.example.CheckpointBackEndIEquipe08.entity.dto.EnderecoDTO;
import com.example.CheckpointBackEndIEquipe08.entity.dto.PacienteDTO;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CheckpointBackEndIEquipe08ApplicationTests {

    DentistaDTO dentista = new DentistaDTO(1,"Ricardo", "Brito", 12345678);
    PacienteDTO paciente = new PacienteDTO(2,"Marcelo", "Garofalo", EnderecoEntity.getId(), "32145678", 20220502);
    EnderecoDTO endereco = new EnderecoDTO(3,"Gralhas", 34, "SP", "SP", "Brasil", "31234-213");


    @Autowired
    DentistaController dentistaController;
    PacienteController pacienteController;
    EnderecoController enderecoController;


    @Test
    void testeDumith() {
        assertTrue(dentistaController.buscarTodos().isEmpty());
    }


}

