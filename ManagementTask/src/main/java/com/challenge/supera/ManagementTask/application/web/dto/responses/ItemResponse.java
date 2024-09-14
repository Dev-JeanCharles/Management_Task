package com.challenge.supera.ManagementTask.application.web.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {
    private String id;
    private String titulo;
    private String descricao;
    private boolean concluido;
    private boolean destacado;
    private String tarefaId;
}
