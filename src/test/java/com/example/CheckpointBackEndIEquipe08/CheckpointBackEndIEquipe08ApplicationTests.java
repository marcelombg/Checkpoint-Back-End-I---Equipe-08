package com.example.CheckpointBackEndIEquipe08;

import com.example.CheckpointBackEndIEquipe08.entity.DentistaEntity;
import com.example.CheckpointBackEndIEquipe08.entity.dto.DentistaDTO;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CheckpointBackEndIEquipe08ApplicationTests {
    DentistaDTO dentista1 = new DentistaDTO("Dumith","Habib",12345);

    @Test
    void testenome(){
        Assert.assertEquals("Dumith", dentista1.getNome());}
}
