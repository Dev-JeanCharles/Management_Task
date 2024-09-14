package com.challenge.supera.ManagementTask.application.web.dto.requesties;

import lombok.Data;

@Data
public class ItemRequest {
    private String titulo;
    private String descricao;
    private boolean concluido;
    private boolean destacado;
    private String tarefaId;
}
