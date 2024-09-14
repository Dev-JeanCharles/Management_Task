package com.challenge.supera.ManagementTask.application.web.builder;

import com.challenge.supera.ManagementTask.application.web.dto.requesties.TarefaRequest;
import com.challenge.supera.ManagementTask.domain.model.Tarefa;
import org.springframework.stereotype.Component;

@Component
public class TarefaBuilder {

    public Tarefa toTarefaEntity(TarefaRequest request) {
        return Tarefa.builder()
                .nome(request.getNome())
                .build();
    }
}
