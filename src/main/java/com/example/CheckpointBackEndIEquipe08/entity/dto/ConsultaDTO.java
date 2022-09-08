package com.example.CheckpointBackEndIEquipe08.entity.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class ConsultaDTO {
    private long id;
    private long dentistaId;
    private long pacienteId;
    private LocalDate data;
    private LocalTime hora;

    public ConsultaDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDentistaId() {
        return dentistaId;
    }

    public void setDentistaId(long dentistaId) {
        this.dentistaId = dentistaId;
    }

    public long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(long pacienteId) {
        this.pacienteId = pacienteId;
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
