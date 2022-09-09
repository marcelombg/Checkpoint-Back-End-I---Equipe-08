package com.example.CheckpointBackEndIEquipe08.entity.dto;

import com.example.CheckpointBackEndIEquipe08.entity.ConsultaEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.time.LocalTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultaDTO {
    private Integer id;
    private Integer idDentista;
    private Integer idPaciente;
    private LocalDate data;
    private LocalTime hora;

    public ConsultaDTO(ConsultaEntity consultaEntity) {
        this.id = consultaEntity.getId();
        this.idDentista = consultaEntity.getIdDentista();
        this.idPaciente = consultaEntity.getIdPaciente();
        this.data = consultaEntity.getData();
        this.hora = consultaEntity.getHora();
    }

    public ConsultaDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdDentista() {
        return idDentista;
    }

    public void setIdDentista(Integer idDentista) {
        this.idDentista = idDentista;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
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
