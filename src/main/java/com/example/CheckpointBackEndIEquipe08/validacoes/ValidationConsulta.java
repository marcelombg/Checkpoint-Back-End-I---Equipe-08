package com.example.CheckpointBackEndIEquipe08.validacoes;

import com.example.CheckpointBackEndIEquipe08.entity.dto.ConsultaDTO;
import com.example.CheckpointBackEndIEquipe08.exception.VariableNullException;

import java.util.ArrayList;
import java.util.List;

public class ValidationConsulta {

    public Boolean validationConsultaVariables(ConsultaDTO consultaDTO) throws VariableNullException {
        List<String> variables = new ArrayList<>();

        if (consultaDTO.getDentista().getId() == null || consultaDTO.getDentista().getId()==0) {
            variables.add("Dentista");
        }
        if (consultaDTO.getPaciente().getId() == null || consultaDTO.getPaciente().getId()==0) {
            variables.add("Dentista");
        }
        if (consultaDTO.getData()==null) {
            variables.add("Data");
        }
        if (consultaDTO.getHora()==null) {
            variables.add("Hora");
        }
        if(!variables.isEmpty()) {
            throw new VariableNullException("Verifique as variáveis listadas: ", variables);
        }
        return true;
    }
}
