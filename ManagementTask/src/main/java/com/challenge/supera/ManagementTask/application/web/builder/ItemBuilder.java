package com.challenge.supera.ManagementTask.application.web.builder;

import com.challenge.supera.ManagementTask.application.web.dto.requesties.ItemRequest;
import com.challenge.supera.ManagementTask.application.web.dto.responses.ItemResponse;
import com.challenge.supera.ManagementTask.domain.model.Item;
import com.challenge.supera.ManagementTask.domain.model.Lista;
import org.springframework.stereotype.Component;

@Component
public class ItemBuilder {

    public Item toItemEntity(ItemRequest request, Lista lista) {
        return Item.builder()
                .titulo(request.getTitulo())
                .descricao(request.getDescricao())
                .concluido(request.isConcluido())
                .destacado(request.isDestacado())
                .lista(lista)
                .build();
    }
}
