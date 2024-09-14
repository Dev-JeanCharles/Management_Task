package com.challenge.supera.ManagementTask.service.imp;

import com.challenge.supera.ManagementTask.domain.model.Item;
import com.challenge.supera.ManagementTask.repository.postgres.interfaces.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService implements com.challenge.supera.ManagementTask.service.interfaces.ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public Item create(Item tarefa) {
        return repository.save(tarefa);
    }

    @Override
    public Optional<Item> getItensByList(String taskId) {
        return repository.findById(taskId);
    }

    @Override
    public Item updatedItem(String id, Item newItem) {
        Item item = repository.findById(id).orElseThrow(() -> new RuntimeException("Item não encontrado"));
        item.setTitulo(newItem.getTitulo());
        item.setDescricao(newItem.getDescricao());
        item.setConcluido(newItem.isConcluido());
        item.setDestacado(newItem.isDestacado());
        return repository.save(item);
    }

    @Override
    public void removeItem(String id) {
        repository.deleteById(id);
    }
}
