package com.challenge.supera.ManagementTask.service.imp;

import com.challenge.supera.ManagementTask.application.web.builder.TarefaBuilder;
import com.challenge.supera.ManagementTask.application.web.dto.requesties.TarefaRequest;
import com.challenge.supera.ManagementTask.application.web.dto.responses.TarefaResponse;
import com.challenge.supera.ManagementTask.domain.model.Tarefa;
import com.challenge.supera.ManagementTask.repository.postgres.interfaces.TarefaRepository;
import com.challenge.supera.ManagementTask.repository.postgres.interfaces.adapter.TarefaAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarefaService implements com.challenge.supera.ManagementTask.service.interfaces.TarefaService {
    private final TarefaRepository repository;
    private final TarefaBuilder builder;
    private final TarefaAdapter adapter;

    @Autowired
    public TarefaService(TarefaRepository repository, TarefaBuilder builder, TarefaAdapter adapter) {
        this.repository = repository;
        this.builder = builder;
        this.adapter = adapter;
    }

    @Override
    public TarefaResponse create(TarefaRequest request) {
        Tarefa tarefa = builder.toTarefaEntity(request);
        Tarefa savedTarefa = repository.save(tarefa);

        return adapter.toResponse(savedTarefa);
    }

    @Override
    public List<TarefaResponse> getAllTasks() {
        List<Tarefa> tarefas = repository.findAll();
        return tarefas.stream()
                .map(adapter::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TarefaResponse getTaskById(String id) {
        Tarefa tarefa = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        return adapter.toResponse(tarefa);
    }

    @Override
    public Tarefa getTaskByIdEntity(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    @Override
    public void removeTask(String id) {
        repository.deleteById(id);
    }
}
