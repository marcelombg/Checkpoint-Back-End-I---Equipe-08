package com.example.CheckpointBackEndIEquipe08.repository;

import com.example.CheckpointBackEndIEquipe08.entity.DentistaEntitie;
import com.example.CheckpointBackEndIEquipe08.entity.PacienteEntitie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PacienteRepository {

    private static Map<Integer, PacienteEntitie> pacienteMap = new HashMap<>();

    private static int idPaciente = 1;

    public PacienteEntitie registrar(PacienteEntitie pacienteEntitie) {
        pacienteEntitie.setId(idPaciente++);
        pacienteMap.put(pacienteEntitie.getId(), pacienteEntitie);
        return pacienteEntitie;
    }

    public PacienteEntitie buscarID(int id) {
        return pacienteMap.get(id);
    }

    public String excluir(int id){
        pacienteMap.remove(id);
        return "Paciente deletado";
    }

    public PacienteEntitie modificar(PacienteEntitie pacienteEntitie){
        pacienteMap.put(pacienteEntitie.getId(), pacienteEntitie);
        return pacienteEntitie;
    }

    public List<PacienteEntitie> buscarTodos(){
        List<PacienteEntitie> pacienteEntitie = new ArrayList<>(pacienteMap.values());
        return pacienteEntitie;
//        return new ArrayList<>(pacienteMap.values());
    }

    public int buscarNome(String name) {
        for (PacienteEntitie pacienteEntitie : pacienteMap.values()) {
            String nomeDentista = pacienteEntitie.getNome();
            if (nomeDentista.equalsIgnoreCase(name))
                return pacienteEntitie.getId();
        }
        return 0;
    }
}
