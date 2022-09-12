package com.example.CheckpointBackEndIEquipe08.repository;

import com.example.CheckpointBackEndIEquipe08.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IEnderecoRepository extends JpaRepository<EnderecoEntity, Integer> {

   /* //Hibernate - Ele já vai em enderecoEntity e busca a coluna rua. O nome deve estar corretamente escrito em findBy___.
    EnderecoEntity findByRua(String rua);

    //HQL -
    @Query("FROM PacienteEntity paciente where paciente.endereco.rua = :rua")
    List<PacienteEntity> getByEnderecoRua(String rua)

    EnderecoEntity getByName(String name);

    //SQL Native
    @Query(value = "SELECT * FROM Paciente p JOIN Endereco end ON p.endereco_id = end.id WHERE end.rua = :rua", nativeQuery = true)
    List<PacienteEntity> getByEnderecoRuaSQL(String name)

    EnderecoEntity getByNameSQL(String name);*/

}