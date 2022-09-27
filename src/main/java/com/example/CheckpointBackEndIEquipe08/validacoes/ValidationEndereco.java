package com.example.CheckpointBackEndIEquipe08.validacoes;

import com.example.CheckpointBackEndIEquipe08.entity.dto.EnderecoDTO;
import com.example.CheckpointBackEndIEquipe08.exception.VariableNullException;
import java.util.ArrayList;
import java.util.List;

public class ValidationEndereco {

        public Boolean validationEnderecoVariables (EnderecoDTO enderecoDTO) throws VariableNullException {
        List<String> variables = new ArrayList<>();

        if (enderecoDTO.getRua()==null || enderecoDTO.getRua().isEmpty()) {
            variables.add("Rua");
        }
        if (enderecoDTO.getCEP()==null || enderecoDTO.getCEP().isEmpty()) {
            variables.add("CEP");
        }
        if (enderecoDTO.getNumero()==null || enderecoDTO.getNumero()==0 ) {
            variables.add("Número");
        }
        if (enderecoDTO.getCidade()==null || enderecoDTO.getCidade().isEmpty()) {
            variables.add("Cidade");
        }
        if (enderecoDTO.getEstado()==null || enderecoDTO.getEstado().isEmpty()) {
            variables.add("Estado");
        }
        if (enderecoDTO.getPais()==null || enderecoDTO.getPais().isEmpty()) {
            variables.add("País");
        }
        if(!variables.isEmpty()) {
            throw new VariableNullException("Verifique as variáveis listadas: ", variables);
        }
        return true;
    }
}
