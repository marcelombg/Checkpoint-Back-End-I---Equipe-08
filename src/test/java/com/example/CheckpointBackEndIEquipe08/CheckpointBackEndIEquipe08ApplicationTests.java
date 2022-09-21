package com.example.CheckpointBackEndIEquipe08;
import com.example.CheckpointBackEndIEquipe08.controller.DentistaController;
import com.example.CheckpointBackEndIEquipe08.entity.DentistaEntity;
import com.example.CheckpointBackEndIEquipe08.entity.dto.DentistaDTO;
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

    DentistaDTO dentista1 = new DentistaDTO(new DentistaEntity());

    @Autowired
    DentistaController dentistaController;

    @Test
    void name() {

        //assertEquals(Boolean,dentistaController.buscarTodos().isEmpty());

    }

    @Test
    void testeDumith(){
        assertTrue(dentistaController.buscarTodos().isEmpty());

    }


    @Test
    void testenome2(){
//        assertEquals(dentistaController.buscarTodos().isEmpty());
    }
}

