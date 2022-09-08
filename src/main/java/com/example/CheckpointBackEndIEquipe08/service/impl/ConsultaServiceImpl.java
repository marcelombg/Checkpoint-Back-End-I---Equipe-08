package com.example.CheckpointBackEndIEquipe08.service.impl;

import com.example.CheckpointBackEndIEquipe08.entity.DentistaEntity;
import com.example.CheckpointBackEndIEquipe08.entity.PacienteEntity;
import com.example.CheckpointBackEndIEquipe08.repository.IDentistaRepository;
import com.example.CheckpointBackEndIEquipe08.repository.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.CheckpointBackEndIEquipe08.entity.RegistrarConsultaEntity;
import com.example.CheckpointBackEndIEquipe08.entity.dto.ConsultaDTO;
import com.example.CheckpointBackEndIEquipe08.repository.IConsultaRepository;
import com.example.CheckpointBackEndIEquipe08.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

public class ConsultaServiceImpl {
    @Autowired
    IConsultaRepository iConsultaRepository;

    @Autowired
    IDentistaRepository iDentistaRepository;

    @Autowired
     IPacienteRepository iPacienteRepository;

    public ConsultaDTO salvarConsulta(RegistrarConsultaEntity registrarConsultaEntity) {
        DentistaEntity dentista = findById(IDentistaRepository.getDentistaId());
        PacienteEntity paciente = findById(IPacienteRepository.getPacienteId());

        DoubleStream Consulta;
        ConsultaDTO consulta = Consulta.builder()
                .dentista(dentista)
                .paciente(paciente)
                .data(registrarConsultaEntity.getData())
                .hora(registrarConsultaEntity.getHora())
                .build();

        return new ConsultaDTO(consulta);
    }

    public ConsultaDTO findById(long id){
        return IConsultaRepository.findById(id).orElseThrow((){
            throw new ChangeSetPersister.NotFoundException("consulta não encontrada");
        });
    }
}

}
