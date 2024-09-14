package com.challenge.supera.ManagementTask.application.web.controllers;

import com.challenge.supera.ManagementTask.domain.model.Item;
import com.challenge.supera.ManagementTask.service.imp.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService service;

    @Autowired
    public ItemController(ItemService service) {
        this.service = service;
    }

    @GetMapping("/task/{taskId}")
    public Optional<Item> getItensByList(@PathVariable String taskId) {
        return service.getItensByList(taskId);
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return service.create(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updatedItem(@PathVariable String id, @RequestBody Item newItem) {
        Item item = service.updatedItem(id, newItem);

        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Item> removeItem(@PathVariable String id) {
        service.removeItem(id);

        return ResponseEntity.noContent().build();
    }
}
