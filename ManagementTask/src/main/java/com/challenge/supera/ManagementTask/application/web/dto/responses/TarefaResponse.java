package com.challenge.supera.ManagementTask.application.web.dto.responses;

import lombok.Data;

import java.util.List;

@Data
public class TarefaResponse {
    private String id;
    private String nome;
    private List<ItemResponse> itens;
}
