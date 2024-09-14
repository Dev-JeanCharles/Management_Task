package com.challenge.supera.ManagementTask.application.web.controllers;

import com.challenge.supera.ManagementTask.application.web.dto.requesties.ItemRequest;
import com.challenge.supera.ManagementTask.application.web.dto.responses.ItemResponse;
import com.challenge.supera.ManagementTask.domain.model.Tarefa;
import com.challenge.supera.ManagementTask.service.imp.ItemService;
import com.challenge.supera.ManagementTask.service.imp.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;
    private final TarefaService tarefaService;

    @Autowired
    public ItemController(ItemService service, TarefaService tarefaService) {
        this.itemService = service;
        this.tarefaService = tarefaService;
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<ItemResponse>> getItensByList(@PathVariable String taskId) {
        List<ItemResponse> response = itemService.getItensByList(taskId);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ItemResponse> createItem(@RequestBody ItemRequest request) {
        Tarefa tarefa = tarefaService.getTaskById(request.getTarefaId());
        ItemResponse itemResponse = itemService.create(request, tarefa);

        return ResponseEntity.ok(itemResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponse> updatedItem(@PathVariable String id, @RequestBody ItemRequest request) {
        ItemResponse updatedItem = itemService.updatedItem(id, request);

        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeItem(@PathVariable String id) {
        itemService.removeItem(id);

        return ResponseEntity.noContent().build();
    }
}
