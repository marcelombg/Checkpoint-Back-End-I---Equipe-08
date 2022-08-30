package com.example.CheckpointBackEndIEquipe08.service.impl;

import com.example.CheckpointBackEndIEquipe08.entity.Paciente;
import com.example.CheckpointBackEndIEquipe08.entity.dto.PacienteDTO;
import com.example.CheckpointBackEndIEquipe08.repository.PacienteRepository;
import com.example.CheckpointBackEndIEquipe08.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PacienteServiceImpl implements IService<PacienteDTO> {

//    private static Map<Integer, Paciente> usuarioMap = new HashMap<>();

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public PacienteDTO registrar(PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente(pacienteDTO);
        pacienteRepository.create(paciente);
        return pacienteDTO;
    }

    @Override
    public List<Paciente> buscarTodos() {
        return new ArrayList<>(usuarioMap.values());
    }

    @Override
    public String excluir(Integer id) {
        usuarioMap.remove(id);
        return "Paciente removido";
    }

    @Override
    public Paciente modificar(Paciente paciente, int id) {
        return null;
    }
}
