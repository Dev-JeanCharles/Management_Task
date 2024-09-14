package com.challenge.supera.ManagementTask.service.interfaces;

import com.challenge.supera.ManagementTask.domain.model.Item;

import java.util.Optional;

public interface ItemService {
    Item create(Item tarefaEntity);
    Optional<Item> getItensByList(String taskId);
    Item updatedItem(String id, Item newItem);
    void removeItem(String id);
}
