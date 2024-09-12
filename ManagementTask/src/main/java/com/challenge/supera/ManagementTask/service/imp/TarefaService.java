package com.challenge.supera.ManagementTask.service.imp;

import com.challenge.supera.ManagementTask.domain.model.Tarefa;
import com.challenge.supera.ManagementTask.repository.postgres.interfaces.TarefaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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
}
