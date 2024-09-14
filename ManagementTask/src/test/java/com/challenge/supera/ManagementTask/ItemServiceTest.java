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
import org.junit.jupiter.api.Nested;
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
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class CreateTests {

        @Test
        void createShouldReturnItemResponse() {
            ItemRequest request = new ItemRequest();
            Tarefa tarefa = new Tarefa();
            Item item = createItem();
            ItemResponse itemResponse = createItemResponse();

            when(builder.toItemEntity(request, tarefa)).thenReturn(item);
            when(repository.save(item)).thenReturn(item);
            when(adapter.toResponse(item)).thenReturn(itemResponse);

            ItemResponse result = service.create(request, tarefa);

            assertNotNull(result);
            verify(builder).toItemEntity(request, tarefa);
            verify(repository).save(item);
            verify(adapter).toResponse(item);
        }
    }

    @Nested
    class UpdateTests {

        @Test
        void updatedItemShouldReturnItemResponse() {
            ItemRequest request = new ItemRequest();
            Item item = createItem();
            ItemResponse itemResponse = createItemResponse();

            when(repository.findById("id")).thenReturn(Optional.of(item));
            doNothing().when(adapter).updateItemFromRequest(item, request);
            when(repository.save(item)).thenReturn(item);
            when(adapter.toResponse(item)).thenReturn(itemResponse);

            ItemResponse result = service.updatedItem("id", request);

            assertNotNull(result);
            verify(repository).findById("id");
            verify(adapter).updateItemFromRequest(item, request);
            verify(repository).save(item);
            verify(adapter).toResponse(item);
        }
    }

    @Nested
    class RemoveTests {

        @Test
        void removeItemShouldCallDeleteById() {
            doNothing().when(repository).deleteById("id");

            service.removeItem("id");

            verify(repository).deleteById("id");
        }
    }

    private Item createItem() {
        return new Item();
    }

    private ItemResponse createItemResponse() {
        return new ItemResponse();
    }
}
