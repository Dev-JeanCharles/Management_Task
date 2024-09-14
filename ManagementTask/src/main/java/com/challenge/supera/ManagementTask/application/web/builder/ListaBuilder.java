package com.challenge.supera.ManagementTask.application.web.builder;

import com.challenge.supera.ManagementTask.application.web.dto.requesties.ListaRequest;
import com.challenge.supera.ManagementTask.domain.model.Lista;
import org.springframework.stereotype.Component;

@Component
public class ListaBuilder {

    public Lista toListEntity(ListaRequest request) {
        return Lista.builder()
                .nome(request.getNome())
                .build();
    }
}
