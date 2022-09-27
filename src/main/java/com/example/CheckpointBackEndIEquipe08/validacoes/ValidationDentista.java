package com.example.CheckpointBackEndIEquipe08.validacoes;

import com.example.CheckpointBackEndIEquipe08.entity.dto.DentistaDTO;
import com.example.CheckpointBackEndIEquipe08.exception.VariableNullException;
import java.util.ArrayList;
import java.util.List;

public class ValidationDentista {
    public Boolean validationDentistaVariables (DentistaDTO dentistaDTO) throws VariableNullException {
        List<String> variables = new ArrayList<>();

        if (dentistaDTO.getNome()==null || dentistaDTO.getNome().isEmpty()) {
            variables.add("Nome");
        }
        if (dentistaDTO.getSobrenome()==null || dentistaDTO.getSobrenome().isEmpty()) {
            variables.add("Sobrenome");
        }
        if (dentistaDTO.getMatriculaCadastro()==null || dentistaDTO.getMatriculaCadastro()==0 ) {
            variables.add("Matrícula");
        }
        if(!variables.isEmpty()) {
            throw new VariableNullException("Verifique as variáveis listadas: ", variables);
        }
        return true;
    }
}
