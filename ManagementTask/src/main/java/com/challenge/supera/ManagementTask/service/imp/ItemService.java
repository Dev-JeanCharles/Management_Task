package com.challenge.supera.ManagementTask.service.imp;

import com.challenge.supera.ManagementTask.domain.model.Item;
import com.challenge.supera.ManagementTask.repository.postgres.interfaces.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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
}
