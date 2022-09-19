package com.example.CheckpointBackEndIEquipe08.entity;

import com.example.CheckpointBackEndIEquipe08.entity.dto.ConsultaDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table (name = "Consulta")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ConsultaEntity<Consulta> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, targetEntity = DentistaEntity.class)
    @JoinColumn(name = "dentista_id")
    private DentistaEntity dentista;
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, targetEntity = PacienteEntity.class)
    @JoinColumn(name = "paciente_id")
    private PacienteEntity paciente;
    private Date data;
    private LocalTime hora;
    public ConsultaEntity() {
    }

    public ConsultaEntity(ConsultaDTO consultaDTO) {
        this.id = consultaDTO.getId();
//        this.dentista = consultaDTO.getIdDentista();
//        this.paciente = consultaDTO.getIdPaciente();
        this.data = consultaDTO.getData();
        this.hora = consultaDTO.getHora();
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

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}