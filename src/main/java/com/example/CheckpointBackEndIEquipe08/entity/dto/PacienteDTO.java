package com.example.CheckpointBackEndIEquipe08.entity.dto;

import com.example.CheckpointBackEndIEquipe08.entity.PacienteEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDTO {
    private String nome;
    private String sobrenome;
    private Integer enderecoId;
    private String RG;
    private Date dataAlta;

    public PacienteDTO(PacienteEntity pacienteEntity) {
        this.nome = pacienteEntity.getNome();
        this.sobrenome = pacienteEntity.getSobrenome();
        this.enderecoId = pacienteEntity.getEnderecoId();
        this.RG = pacienteEntity.getRG();
        this.dataAlta = pacienteEntity.getDataAlta();
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
