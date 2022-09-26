package com.example.CheckpointBackEndIEquipe08.entity.dto;

import com.example.CheckpointBackEndIEquipe08.entity.ConsultaEntity;
import com.example.CheckpointBackEndIEquipe08.entity.DentistaEntity;
import com.example.CheckpointBackEndIEquipe08.entity.PacienteEntity;
import java.sql.Time;
import java.util.Date;

public class ConsultaDTO {
    private Integer id;
    private DentistaEntity dentista;
    private PacienteEntity paciente;
    private Date data;
    private Time hora;

    public ConsultaDTO(ConsultaEntity consultaEntity) {
        this.id = consultaEntity.getId();
        this.dentista = consultaEntity.getDentista();
        this.paciente = consultaEntity.getPaciente();
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

    public DentistaEntity getDentista() {
        return dentista;
    }

    public void setDentista(DentistaEntity dentista) {
        this.dentista = dentista;
    }

    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }
}
