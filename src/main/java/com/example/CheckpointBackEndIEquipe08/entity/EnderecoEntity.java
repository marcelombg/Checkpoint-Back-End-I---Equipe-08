package com.example.CheckpointBackEndIEquipe08.entity;

import com.example.CheckpointBackEndIEquipe08.entity.dto.EnderecoDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Endereco")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String rua;
    private Integer numero;
    private String cidade;
    private String estado;
    private String pais;
    private String CEP;

    @JsonIgnore
    @OneToMany(mappedBy = "endereco", fetch = FetchType.LAZY, targetEntity = PacienteEntity.class)
    private List<PacienteEntity> pacienteEntities = new ArrayList<>();

    public EnderecoEntity(EnderecoDTO enderecoDTO) {
        this.id = enderecoDTO.getId();
        this.rua = enderecoDTO.getRua();
        this.numero = enderecoDTO.getNumero();
        this.cidade = enderecoDTO.getCidade();
        this.estado = enderecoDTO.getEstado();
        this.pais = enderecoDTO.getPais();
        this.CEP = enderecoDTO.getCEP();
    }

    public EnderecoEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<PacienteEntity> getPacienteEntities() {
        return pacienteEntities;
    }

    public void setPacienteEntities(List<PacienteEntity> pacienteEntities) {
        this.pacienteEntities = pacienteEntities;
    }
}
