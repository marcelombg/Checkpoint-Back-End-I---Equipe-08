package com.example.CheckpointBackEndIEquipe08.entity.dto;

import com.example.CheckpointBackEndIEquipe08.entity.EnderecoEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoDTO {
    private Integer id;
    private String rua;
    private Integer numero;
    private String cidade;
    private String estado;
    private String pais;
    private String CEP;

    public EnderecoDTO(EnderecoEntity enderecoEntity) {
        this.id = enderecoEntity.getId();
        this.rua = enderecoEntity.getRua();
        this.numero = enderecoEntity.getNumero();
        this.cidade = enderecoEntity.getCidade();
        this.estado = enderecoEntity.getEstado();
        this.pais = enderecoEntity.getPais();
        this.CEP = enderecoEntity.getCEP();
    }

    public EnderecoDTO(Integer id, String rua, Integer numero, String cidade, String estado, String pais, String CEP) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.CEP = CEP;
    }

    public EnderecoDTO() {
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
