package com.example.CheckpointBackEndIEquipe08.repository;

import com.example.CheckpointBackEndIEquipe08.entity.DentistaEntitie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DentistaRepository {

    private static Map<Integer, DentistaEntitie> dentistaMap = new HashMap<>();

    private static int idDentista = 1;

    public DentistaEntitie registrar(DentistaEntitie dentistaEntitie) {
        dentistaEntitie.setId(idDentista++);
        dentistaMap.put(dentistaEntitie.getId(), dentistaEntitie);
        return dentistaEntitie;
    }

    public DentistaEntitie buscarId(int id) {
        return dentistaMap.get(id);
    }

    public String excluir(int id){
        dentistaMap.remove(id);
        return "Dentista deletado";
    }

    public DentistaEntitie modificar(DentistaEntitie dentistaEntitie){
        dentistaMap.put(dentistaEntitie.getId(), dentistaEntitie);
        return dentistaEntitie;
    }

    public List<DentistaEntitie> buscarTodos(){
        List<DentistaEntitie> dentistaEntities = new ArrayList<>(dentistaMap.values());
        return dentistaEntities;
//        return new ArrayList<>(dentistaMap.values());
    }

    public int buscarNome(String name) {
        for (DentistaEntitie dentistaEntitie : dentistaMap.values()) {
            String nomeDentista = dentistaEntitie.getNome();
            if (nomeDentista.equalsIgnoreCase(name))
                return dentistaEntitie.getId();
        }
        return 0;
    }
}
