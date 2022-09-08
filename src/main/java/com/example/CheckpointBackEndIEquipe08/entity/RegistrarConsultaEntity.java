package com.example.CheckpointBackEndIEquipe08.entity;

import com.example.CheckpointBackEndIEquipe08.entity.dto.ConsultaDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDate;
import java.time.LocalTime;

public class RegistrarConsultaEntity<Consulta> {

    private String dentista;
    private String paciente;
    private LocalDate data;
    private LocalTime hora;

    public ConsultaDTO (ConsultaDTO consultaDTO) {
        this.dentista = consultaDTO.getDentistaId().getNome();
        this.paciente = consultaDTO.getPacienteId().getNome();
        this.data = consultaDTO.getData();
        this.hora = consultaDTO.getHora();
    }

    public Object getData() {
    }

    public Object getHora() {
    }
}
