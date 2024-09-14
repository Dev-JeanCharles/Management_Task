package com.challenge.supera.ManagementTask.service.imp;

import com.challenge.supera.ManagementTask.application.web.builder.ListaBuilder;
import com.challenge.supera.ManagementTask.application.web.dto.requesties.ListaRequest;
import com.challenge.supera.ManagementTask.application.web.dto.responses.ListaResponse;
import com.challenge.supera.ManagementTask.domain.model.Lista;
import com.challenge.supera.ManagementTask.repository.postgres.interfaces.ListaRepository;
import com.challenge.supera.ManagementTask.repository.postgres.interfaces.adapter.ListaAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListaService implements com.challenge.supera.ManagementTask.service.interfaces.ListaService {
    private final ListaRepository repository;
    private final ListaBuilder builder;
    private final ListaAdapter adapter;

    @Autowired
    public ListaService(ListaRepository repository, ListaBuilder builder, ListaAdapter adapter) {
        this.repository = repository;
        this.builder = builder;
        this.adapter = adapter;
    }

    @Override
    public ListaResponse createList(ListaRequest request) {
        Lista lista = builder.toListEntity(request);
        Lista savedLista = repository.save(lista);

        return adapter.toResponse(savedLista);
    }

    @Override
    public List<ListaResponse> getAllList() {
        List<Lista> listas = repository.findAll();
        return listas.stream()
                .map(adapter::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ListaResponse getListById(String id) {
        Lista lista = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lista não encontrada"));

        return adapter.toResponse(lista);
    }

    @Override
    public Lista getListByIdEntity(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lista não encontrada"));
    }

    @Override
    public void removeList(String id) {
        repository.deleteById(id);
    }
}
