package com.challenge.supera.ManagementTask.repository.postgres.interfaces.adapter;

import com.challenge.supera.ManagementTask.application.web.dto.requesties.ItemRequest;
import com.challenge.supera.ManagementTask.application.web.dto.responses.ItemResponse;
import com.challenge.supera.ManagementTask.domain.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemAdapter {

    public ItemResponse toResponse(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .titulo(item.getTitulo())
                .descricao(item.getDescricao())
                .concluido(item.isConcluido())
                .destacado(item.isDestacado())
                .tarefa(item.getLista().getId())
                .build();
    }

    public void updateItemFromRequest(Item item, ItemRequest request) {
        item.setTitulo(request.getTitulo());
        item.setDescricao(request.getDescricao());
        item.setConcluido(request.isConcluido());
        item.setDestacado(request.isDestacado());
    }
}
