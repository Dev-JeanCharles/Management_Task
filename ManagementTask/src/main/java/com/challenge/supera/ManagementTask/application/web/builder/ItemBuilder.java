package com.challenge.supera.ManagementTask.application.web.builder;

import com.challenge.supera.ManagementTask.application.web.dto.requesties.ItemRequest;
import com.challenge.supera.ManagementTask.application.web.dto.responses.ItemResponse;
import com.challenge.supera.ManagementTask.domain.model.Item;
import com.challenge.supera.ManagementTask.domain.model.Tarefa;
import org.springframework.stereotype.Component;

@Component
public class ItemBuilder {

    public Item toItemEntity(ItemRequest request, Tarefa tarefa) {
        return Item.builder()
                .titulo(request.getTitulo())
                .descricao(request.getDescricao())
                .concluido(request.isConcluido())
                .destacado(request.isDestacado())
                .tarefa(tarefa)
                .build();
    }

    public ItemResponse toItemResponse(Item item) {
        ItemResponse response = new ItemResponse();
        response.setId(item.getId());
        response.setTitulo(item.getTitulo());
        response.setDescricao(item.getDescricao());
        response.setConcluido(item.isConcluido());
        response.setDestacado(item.isDestacado());
        return response;
    }
}
