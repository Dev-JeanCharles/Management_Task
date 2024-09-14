package com.challenge.supera.ManagementTask.application.web.controllers;

import com.challenge.supera.ManagementTask.domain.model.Tarefa;
import com.challenge.supera.ManagementTask.service.interfaces.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
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
    public List<Tarefa> getAllTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getTaskById(@PathVariable String id)  {
        Tarefa tarefa = service.getTaskById(id);

        return ResponseEntity.ok(tarefa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable String id) {
        service.removeTask(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public Tarefa createTask(@RequestBody Tarefa tarefa) {
        return service.create(tarefa);
    }
}
