package com.example.CheckpointBackEndIEquipe08.entity;

import com.example.CheckpointBackEndIEquipe08.entity.dto.DentistaDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
@Table(name = "Dentista")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DentistaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String sobrenome;
    private Integer matriculaCadastro;

    public DentistaEntity() {
    }

    public DentistaEntity(DentistaDTO dentistaDTO) {
        this.id = dentistaDTO.getId();
        this.nome = dentistaDTO.getNome();
        this.sobrenome = dentistaDTO.getSobrenome();
        this.matriculaCadastro = dentistaDTO.getMatriculaCadastro();
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
