package com.challenge.supera.ManagementTask.repository.postgres.interfaces.adapter;

import com.challenge.supera.ManagementTask.application.web.builder.ItemBuilder;
import com.challenge.supera.ManagementTask.application.web.dto.requesties.TarefaRequest;
import com.challenge.supera.ManagementTask.application.web.dto.responses.TarefaResponse;
import com.challenge.supera.ManagementTask.domain.model.Tarefa;
import org.springframework.stereotype.Component;

@Component
public class TarefaAdapter {
    public TarefaResponse toResponse(Tarefa tarefa) {
        TarefaResponse response = new TarefaResponse();
        response.setId(tarefa.getId());
        response.setNome(tarefa.getNome());
        response.setItens(tarefa.getItens().stream()
                .map(item -> new ItemBuilder().toItemResponse(item))
                .toList());
        return response;
    }

    public void updateTarefaFromRequest(Tarefa tarefa, TarefaRequest request) {
        tarefa.setNome(request.getNome());
    }
}
