package com.challenge.supera.ManagementTask.application.web.controllers;

import com.challenge.supera.ManagementTask.domain.model.Item;
import com.challenge.supera.ManagementTask.service.imp.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/itens")
public class ItemController {

    private final ItemService service;

    @Autowired
    public ItemController(ItemService service) {
        this.service = service;
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        log.info("[CREATE-TAREFA]-[Controller] Iniciando a criação do item: [{}]", item);
        return service.create(item);
    }
}
