package com.example.CheckpointBackEndIEquipe08.model;

public class Dentista {

    private Integer id;
    private String nome;
    private String email;
    private Integer numMatrícula;
    private String atendeConvenio;

    public Dentista(Integer id, String nome, String email, Integer numMatrícula, String atendeConvenio) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.numMatrícula = numMatrícula;
        this.atendeConvenio = atendeConvenio;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumMatrícula() {
        return numMatrícula;
    }

    public void setNumMatrícula(Integer numMatrícula) {
        this.numMatrícula = numMatrícula;
    }

    public String getAtendeConvenio() {
        return atendeConvenio;
    }

    public void setAtendeConvenio(String atendeConvenio) {
        this.atendeConvenio = atendeConvenio;
    }
}
