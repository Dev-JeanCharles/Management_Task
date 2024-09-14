package com.challenge.supera.ManagementTask.service.interfaces;

import com.challenge.supera.ManagementTask.application.web.dto.requesties.TarefaRequest;
import com.challenge.supera.ManagementTask.application.web.dto.responses.TarefaResponse;
import com.challenge.supera.ManagementTask.domain.model.Tarefa;

import java.util.List;

public interface TarefaService {
    TarefaResponse create(TarefaRequest request);
    List<TarefaResponse> getAllTasks();
    TarefaResponse getTaskById(String id);
    Tarefa getTaskByIdEntity(String id);
    void removeTask(String id);
}
