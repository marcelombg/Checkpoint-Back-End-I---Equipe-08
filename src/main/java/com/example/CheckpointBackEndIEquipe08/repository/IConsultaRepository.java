package com.example.CheckpointBackEndIEquipe08.repository;

import com.example.CheckpointBackEndIEquipe08.entity.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IConsultaRepository extends JpaRepository <ConsultaEntity, Integer> {
}
