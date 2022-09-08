package com.example.CheckpointBackEndIEquipe08.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table (name = "Consulta")
@JsonIgnoreProperties()
public class ConsultaEntity<Consulta> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idDentista;
    private Integer idPaciente;
    private LocalDate data;
    private LocalTime hora;

   /* public RegistrarConsultaEntity (ConsultaDTO consultaDTO) {
        this.idDentista = consultaDTO.getDentistaId();
        this.idPaciente = consultaDTO.getPacienteId();
        this.data = consultaDTO.getData();
        this.hora = consultaDTO.getHora();
    }*/

    public ConsultaEntity() {


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