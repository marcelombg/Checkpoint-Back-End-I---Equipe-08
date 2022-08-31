package com.example.CheckpointBackEndIEquipe08.entity.dto;

import com.example.CheckpointBackEndIEquipe08.entity.DentistaEntitie;

public class DentistaDTO {

    private String nome;
    private String sobrenome;
    private Integer matriculaCadastro;

    public DentistaDTO(DentistaEntitie dentistaEntitie) {
        this.nome = dentistaEntitie.getNome();
        this.sobrenome = dentistaEntitie.getSobrenome();
        this.matriculaCadastro = dentistaEntitie.getMatriculaCadastro();
    }

    public DentistaDTO() {
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
