package com.example.CheckpointBackEndIEquipe08.entity.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ConsultaDTO {
    private long id;
    private long dentistaId;
    private long pacienteId;
    private LocalDate data;
    private LocalTime hora;

    public ConsultaDTO(ConsultaDTO consulta) {
    }
}
