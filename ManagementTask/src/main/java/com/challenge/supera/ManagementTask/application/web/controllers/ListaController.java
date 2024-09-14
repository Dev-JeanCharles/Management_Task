package com.challenge.supera.ManagementTask.application.web.controllers;

import com.challenge.supera.ManagementTask.application.web.dto.requesties.ListaRequest;
import com.challenge.supera.ManagementTask.application.web.dto.responses.ListaResponse;
import com.challenge.supera.ManagementTask.service.interfaces.ListaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lista")
public class ListaController {

    private final ListaService service;

    @Autowired
    public ListaController(ListaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ListaResponse>> getAllList() {
        List<ListaResponse> listas = service.getAllList();

        return ResponseEntity.ok(listas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaResponse> getListById(@PathVariable String id) {
        ListaResponse lista = service.getListById(id);

        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteList(@PathVariable String id) {
        service.removeList(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ListaResponse> createList(@Valid @RequestBody ListaRequest request) {
        ListaResponse listaResponse = service.createList(request);

        return ResponseEntity.ok(listaResponse);
    }
}
