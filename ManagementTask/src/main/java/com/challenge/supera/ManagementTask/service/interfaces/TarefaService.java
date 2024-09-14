package com.challenge.supera.ManagementTask.service.interfaces;

import com.challenge.supera.ManagementTask.domain.model.Tarefa;

import java.util.List;

public interface TarefaService {
    Tarefa create(Tarefa tarefa);
    List<Tarefa> getAllTasks();
    Tarefa getTaskById(String id);
    void removeTask(String id);
}
