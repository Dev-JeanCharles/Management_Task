package com.challenge.supera.ManagementTask.application.web.controllers;

import com.challenge.supera.ManagementTask.domain.model.Tarefa;
import com.challenge.supera.ManagementTask.service.interfaces.TarefaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/task")
public class TarefaController {

    private final TarefaService service;

    @Autowired
    public TarefaController(TarefaService service) {
        this.service = service;
    }

    @PostMapping
    public Tarefa createTask(@RequestBody Tarefa tarefa) {
        log.info("[CREATE-TAREFA]-[Controller] Starting task creation for request: [{}]", tarefa);
        return service.create(tarefa);
    }
}
