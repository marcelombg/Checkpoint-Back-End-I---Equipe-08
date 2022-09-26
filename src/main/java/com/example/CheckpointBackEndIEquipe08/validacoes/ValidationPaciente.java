package com.example.CheckpointBackEndIEquipe08.validacoes;

import com.example.CheckpointBackEndIEquipe08.entity.dto.PacienteDTO;
import com.example.CheckpointBackEndIEquipe08.exception.VariableNullException;
import com.example.CheckpointBackEndIEquipe08.repository.IEnderecoRepository;
import com.example.CheckpointBackEndIEquipe08.repository.IPacienteRepository;
import com.example.CheckpointBackEndIEquipe08.service.impl.EnderecoServiceImpl;
import com.example.CheckpointBackEndIEquipe08.service.impl.PacienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

public class ValidationPaciente {

    @Autowired
    EnderecoServiceImpl enderecoService;

    @Autowired
    PacienteServiceImpl pacienteService;

    @Autowired
    IPacienteRepository pacienteRepository;

    @Autowired
    IEnderecoRepository enderecoRepository;

    public Boolean validationPacienteVariables (PacienteDTO pacienteDTO) throws VariableNullException {
        List<String> variables = new ArrayList<>();

        if (pacienteDTO.getNome()==null || pacienteDTO.getNome().isEmpty()) {
            variables.add("Nome");
        }

        if (pacienteDTO.getSobrenome()==null || pacienteDTO.getSobrenome().isEmpty()) {
            variables.add("Sobrenome");
        }

        if (pacienteDTO.getRG()==null || pacienteDTO.getRG().isEmpty()) {
            variables.add("RG");
        }

        if (pacienteDTO.getDataAlta()==null) {
            variables.add("Número");
        }

        if (pacienteDTO.getEndereco().getId()==null || pacienteDTO.getEndereco().getId()<=0 ) {
            variables.add("Endereço");
        }

        if(!variables.isEmpty()) {
            throw new VariableNullException("Verifique as variáveis listadas: ", variables);
        }
        return true;
    }
}
