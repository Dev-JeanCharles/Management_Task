package com.challenge.supera.ManagementTask.application.web.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListaResponse {
    private String id;
    private String nome;
    private List<ItemResponse> itens;
}
