package com.challenge.supera.ManagementTask.service.interfaces;

import com.challenge.supera.ManagementTask.application.web.dto.requesties.ItemRequest;
import com.challenge.supera.ManagementTask.application.web.dto.responses.ItemResponse;
import com.challenge.supera.ManagementTask.domain.model.Tarefa;

import java.util.List;

public interface ItemService {
    ItemResponse create(ItemRequest request, Tarefa tarefa);
    List<ItemResponse> getItensByList(String taskId);
    ItemResponse updatedItem(String id, ItemRequest request);
    void removeItem(String id);
}
