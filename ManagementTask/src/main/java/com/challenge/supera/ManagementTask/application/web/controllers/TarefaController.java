package com.challenge.supera.ManagementTask.application.web.controllers;

import com.challenge.supera.ManagementTask.application.web.dto.requesties.TarefaRequest;
import com.challenge.supera.ManagementTask.application.web.dto.responses.TarefaResponse;
import com.challenge.supera.ManagementTask.service.interfaces.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TarefaController {

    private final TarefaService service;

    @Autowired
    public TarefaController(TarefaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TarefaResponse>> getAllTasks() {
        List<TarefaResponse> tarefas = service.getAllTasks();

        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponse> getTaskById(@PathVariable String id) {
        TarefaResponse tarefa = service.getTaskById(id);

        return ResponseEntity.ok(tarefa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        service.removeTask(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<TarefaResponse> createTask(@RequestBody TarefaRequest request) {
        TarefaResponse tarefaResponse = service.create(request);

        return ResponseEntity.ok(tarefaResponse);
    }
}
