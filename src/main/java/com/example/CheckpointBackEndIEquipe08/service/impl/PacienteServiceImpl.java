package com.example.CheckpointBackEndIEquipe08.service.impl;

import com.example.CheckpointBackEndIEquipe08.model.Paciente;
import com.example.CheckpointBackEndIEquipe08.service.IService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PacienteServiceImpl implements IService<Paciente> {

    private static Map<Integer, Paciente> usuarioMap = new HashMap<>();

    public Paciente salvar(Paciente usuario) {
        usuarioMap.put(usuario.getId(), usuario);
        return usuario;
    }

    @Override
    public List<Paciente> buscarTodos() {
        return new ArrayList<>(usuarioMap.values());
    }

    @Override
    public String excluir(Integer id) {
        usuarioMap.remove(id);
        return "Usuário removido";
    }
}
