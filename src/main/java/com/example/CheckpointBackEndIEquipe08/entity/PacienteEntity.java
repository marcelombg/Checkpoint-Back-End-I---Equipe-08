package com.example.CheckpointBackEndIEquipe08.entity;

import com.example.CheckpointBackEndIEquipe08.entity.dto.PacienteDTO;

import java.util.Date;


public class PacienteEntity {


    private Integer id;
    private String nome;
    private String sobrenome;
    /*@JsonIgnore*/  // Vai ser usado qdo tiver o endereço
    private Integer enderecoId;
    private String RG;
    private Date dataAlta;

    public PacienteEntity() {
    }

    public PacienteEntity(PacienteDTO pacienteDTO) {
        this.nome = pacienteDTO.getNome();
        this.sobrenome = pacienteDTO.getSobrenome();
        this.enderecoId = pacienteDTO.getEnderecoId();
        this.RG = pacienteDTO.getRG();
        this.dataAlta = pacienteDTO.getDataAlta();
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
