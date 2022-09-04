package com.example.CheckpointBackEndIEquipe08.entity.dto;

import com.example.CheckpointBackEndIEquipe08.entity.PacienteEntitie;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.Date;
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDTO {
    private String nome;
    private String sobrenome;
    private Integer enderecoId;
    private String RG;
    private Date dataAlta;

    public PacienteDTO(PacienteEntitie pacienteEntitie) {
        this.nome = pacienteEntitie.getNome();
        this.sobrenome = pacienteEntitie.getSobrenome();
        this.enderecoId = pacienteEntitie.getEnderecoId();
        this.RG = pacienteEntitie.getRG();
        this.dataAlta = pacienteEntitie.getDataAlta();
    }

    public PacienteDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Integer getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Integer enderecoId) {
        this.enderecoId = enderecoId;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    public Date getDataAlta() {
        return dataAlta;
    }

    public void setDataAlta(Date dataAlta) {
        this.dataAlta = dataAlta;
    }
}
