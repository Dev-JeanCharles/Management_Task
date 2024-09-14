package com.challenge.supera.ManagementTask.repository.postgres.interfaces.adapter;

import com.challenge.supera.ManagementTask.application.web.builder.ItemBuilder;
import com.challenge.supera.ManagementTask.application.web.dto.requesties.TarefaRequest;
import com.challenge.supera.ManagementTask.application.web.dto.responses.ItemResponse;
import com.challenge.supera.ManagementTask.application.web.dto.responses.TarefaResponse;
import com.challenge.supera.ManagementTask.domain.model.Item;
import com.challenge.supera.ManagementTask.domain.model.Tarefa;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TarefaAdapter {

    public TarefaResponse toResponse(Tarefa tarefa) {
        List<ItemResponse> itemResponses = (tarefa.getItens() != null) ?
                tarefa.getItens().stream()
                        .map(this::convertToItemResponse)
                        .collect(Collectors.toList()) :
                new ArrayList<>();

        return TarefaResponse.builder()
                .id(tarefa.getId())
                .nome(tarefa.getNome())
                .itens(itemResponses)
                .build();
    }

    private ItemResponse convertToItemResponse(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .titulo(item.getTitulo())
                .descricao(item.getDescricao())
                .concluido(item.isConcluido())
                .destacado(item.isDestacado())
                .tarefa(item.getTarefa() != null ? item.getTarefa().getId() : null)
                .build();
    }
}
