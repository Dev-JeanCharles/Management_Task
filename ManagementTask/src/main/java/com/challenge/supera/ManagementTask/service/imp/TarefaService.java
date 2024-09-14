package com.challenge.supera.ManagementTask.service.imp;

import com.challenge.supera.ManagementTask.domain.model.Tarefa;
import com.challenge.supera.ManagementTask.repository.postgres.interfaces.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService implements com.challenge.supera.ManagementTask.service.interfaces.TarefaService {
    private final TarefaRepository repository;

    @Autowired
    public TarefaService(TarefaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Tarefa create(Tarefa tarefa) {
        return repository.save(tarefa);
    }

    @Override
    public List<Tarefa> getAllTasks() {
        return repository.findAll();
    }

    @Override
    public Tarefa getTaskById(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Lista não encontrada"));
    }

    @Override
    public void removeTask(String id) {
        repository.deleteById(id);
    }
}
