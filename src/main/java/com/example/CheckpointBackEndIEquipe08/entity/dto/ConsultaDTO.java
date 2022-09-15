package com.example.CheckpointBackEndIEquipe08.entity.dto;

import com.example.CheckpointBackEndIEquipe08.entity.ConsultaEntity;
import com.example.CheckpointBackEndIEquipe08.entity.DentistaEntity;
import com.example.CheckpointBackEndIEquipe08.entity.PacienteEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.time.LocalTime;

/*@JsonIgnoreProperties(ignoreUnknown = true)*/
public class ConsultaDTO {
    private Integer id;
    private DentistaDTO dentista;
    private PacienteDTO paciente;
    private LocalDate data;
    private LocalTime hora;

    /*public ConsultaDTO(ConsultaEntity consultaEntity) {
        this.id = consultaEntity.getId();
        this.dentista = consultaEntity.getDentista();
        this.paciente = consultaEntity.getPaciente();
        this.data = consultaEntity.getData();
        this.hora = consultaEntity.getHora();
    }*/

    public ConsultaDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DentistaDTO getIdDentista() {
        return dentista;
    }

    public void setIdDentista(DentistaDTO dentista) {
        this.dentista = dentista;
    }

    public PacienteDTO getIdPaciente() {
        return paciente;
    }

    public void setIdPaciente(PacienteDTO paciente) {
        this.paciente = paciente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}
