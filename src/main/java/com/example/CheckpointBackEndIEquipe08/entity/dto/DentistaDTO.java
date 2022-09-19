package com.example.CheckpointBackEndIEquipe08.entity.dto;

import com.example.CheckpointBackEndIEquipe08.entity.DentistaEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DentistaDTO {
    private Integer id;
    private String nome;
    private String sobrenome;
    private Integer matriculaCadastro;

    public DentistaDTO(DentistaEntity dentistaEntity) {
        this.id = dentistaEntity.getId();
        this.nome = dentistaEntity.getNome();
        this.sobrenome = dentistaEntity.getSobrenome();
        this.matriculaCadastro = dentistaEntity.getMatriculaCadastro();
    }

    public DentistaDTO() {
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

    public Integer getMatriculaCadastro() {
        return matriculaCadastro;
    }

    public void setMatriculaCadastro(Integer matriculaCadastro) {
        this.matriculaCadastro = matriculaCadastro;
    }
}
