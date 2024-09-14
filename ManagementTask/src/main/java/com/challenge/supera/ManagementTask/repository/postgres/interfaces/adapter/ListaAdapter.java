package com.challenge.supera.ManagementTask.repository.postgres.interfaces.adapter;

import com.challenge.supera.ManagementTask.application.web.dto.responses.ItemResponse;
import com.challenge.supera.ManagementTask.application.web.dto.responses.ListaResponse;
import com.challenge.supera.ManagementTask.domain.model.Item;
import com.challenge.supera.ManagementTask.domain.model.Lista;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListaAdapter {

    public ListaResponse toResponse(Lista lista) {
        List<ItemResponse> itemResponses = (lista.getItens() != null) ?
                lista.getItens().stream()
                        .map(this::convertToItemResponse)
                        .collect(Collectors.toList()) :
                new ArrayList<>();

        return ListaResponse.builder()
                .id(lista.getId())
                .nome(lista.getNome())
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
                .tarefa(item.getLista() != null ? item.getLista().getId() : null)
                .build();
    }
}
