package com.example.CheckpointBackEndIEquipe08.entity;

public class Dentista {

    private Integer id;
    private String nome;
    private String sobrenome;
    private Integer matriculaCadastro;

    public Dentista(Integer id, String nome, String sobrenome, Integer matriculaCadastro) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.matriculaCadastro = matriculaCadastro;
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
