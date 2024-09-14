package com.challenge.supera.ManagementTask;

import com.challenge.supera.ManagementTask.application.web.builder.ItemBuilder;
import com.challenge.supera.ManagementTask.application.web.dto.requesties.ItemRequest;
import com.challenge.supera.ManagementTask.application.web.dto.responses.ItemResponse;
import com.challenge.supera.ManagementTask.domain.model.Item;
import com.challenge.supera.ManagementTask.domain.model.Tarefa;
import com.challenge.supera.ManagementTask.repository.postgres.interfaces.ItemRepository;
import com.challenge.supera.ManagementTask.repository.postgres.interfaces.adapter.ItemAdapter;
import com.challenge.supera.ManagementTask.service.imp.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ItemServiceTest {

    @Mock
    private ItemRepository repository;

    @Mock
    private ItemBuilder builder;

    @Mock
    private ItemAdapter adapter;

    @InjectMocks
    private ItemService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createShouldReturnItemResponse() {
        ItemRequest request = new ItemRequest();
        Tarefa tarefa = new Tarefa();
        Item item = new Item();
        ItemResponse itemResponse = new ItemResponse();

        when(builder.toItemEntity(any(ItemRequest.class), any(Tarefa.class))).thenReturn(item);
        when(repository.save(any(Item.class))).thenReturn(item);
        when(adapter.toResponse(any(Item.class))).thenReturn(itemResponse);

        ItemResponse result = service.create(request, tarefa);

        assertNotNull(result);
        verify(builder).toItemEntity(any(ItemRequest.class), any(Tarefa.class));
        verify(repository).save(any(Item.class));
        verify(adapter).toResponse(any(Item.class));
    }

    @Test
    void updatedItemShouldReturnItemResponse() {
        ItemRequest request = new ItemRequest();
        Item item = new Item();
        ItemResponse itemResponse = new ItemResponse();

        when(repository.findById(anyString())).thenReturn(Optional.of(item));
        doNothing().when(adapter).updateItemFromRequest(any(Item.class), any(ItemRequest.class));
        when(repository.save(any(Item.class))).thenReturn(item);
        when(adapter.toResponse(any(Item.class))).thenReturn(itemResponse);

        ItemResponse result = service.updatedItem("id", request);

        assertNotNull(result);
        verify(repository).findById(anyString());
        verify(adapter).updateItemFromRequest(any(Item.class), any(ItemRequest.class));
        verify(repository).save(any(Item.class));
        verify(adapter).toResponse(any(Item.class));
    }

    @Test
    void removeItemShouldCallDeleteById() {
        doNothing().when(repository).deleteById(anyString());

        service.removeItem("id");

        verify(repository).deleteById(anyString());
    }
}
