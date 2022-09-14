package com.example.CheckpointBackEndIEquipe08.entity.dto;

import com.example.CheckpointBackEndIEquipe08.entity.EnderecoEntity;
import com.example.CheckpointBackEndIEquipe08.entity.PacienteEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
//@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDTO {

    private Integer id;
    private String nome;
    private String sobrenome;
    private EnderecoDTO endereco;
    private String RG;
    private Date dataAlta;

//    public PacienteDTO(PacienteEntity pacienteEntity) {
//        this.id = pacienteEntity.getId();
//        this.nome = pacienteEntity.getNome();
//        this.sobrenome = pacienteEntity.getSobrenome();
//        this.endereco = pacienteEntity.getEndereco();
//        this.RG = pacienteEntity.getRG();
//        this.dataAlta = pacienteEntity.getDataAlta();
//    }

    public PacienteDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
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
