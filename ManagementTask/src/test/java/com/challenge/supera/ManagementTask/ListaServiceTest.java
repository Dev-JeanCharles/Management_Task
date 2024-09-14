package com.challenge.supera.ManagementTask;

import com.challenge.supera.ManagementTask.application.web.builder.ListaBuilder;
import com.challenge.supera.ManagementTask.application.web.dto.requesties.ListaRequest;
import com.challenge.supera.ManagementTask.application.web.dto.responses.ListaResponse;
import com.challenge.supera.ManagementTask.domain.model.Lista;
import com.challenge.supera.ManagementTask.repository.postgres.interfaces.ListaRepository;
import com.challenge.supera.ManagementTask.repository.postgres.interfaces.adapter.ListaAdapter;
import com.challenge.supera.ManagementTask.service.imp.ListaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ListaServiceTest {

    @Mock
    private ListaRepository repository;

    @Mock
    private ListaBuilder builder;

    @Mock
    private ListaAdapter adapter;

    @InjectMocks
    private ListaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(adapter.toResponse(any(Lista.class))).thenAnswer(invocation -> {
            Lista lista = invocation.getArgument(0);
            return ListaResponse.builder()
                    .id(lista.getId())
                    .nome(lista.getNome())
                    .itens(new ArrayList<>())
                    .build();
        });
    }

    @Nested
    class CreateTests {

        @Test
        void createShouldReturnListResponse() {
            ListaRequest request = new ListaRequest();
            Lista lista = createList();
            Lista savedLista = createList();
            ListaResponse listaResponse = new ListaResponse();

            when(builder.toListEntity(request)).thenReturn(lista);
            when(repository.save(lista)).thenReturn(savedLista);
            when(adapter.toResponse(savedLista)).thenReturn(listaResponse);

            ListaResponse result = service.createList(request);

            assertNotNull(result);
            verify(builder).toListEntity(request);
            verify(repository).save(lista);
            verify(adapter).toResponse(savedLista);
        }
    }

    @Nested
    class GetAllListTests {

        @Test
        void getAllListsShouldReturnListResponse() {
            Lista lista1 = createList();
            Lista lista2 = createList();
            ListaResponse response1 = new ListaResponse();
            ListaResponse response2 = new ListaResponse();

            when(repository.findAll()).thenReturn(List.of(lista1, lista2));
            when(adapter.toResponse(lista1)).thenReturn(response1);
            when(adapter.toResponse(lista2)).thenReturn(response2);

            List<ListaResponse> result = service.getAllList();

            assertNotNull(result);
            assertEquals(2, result.size());

            verify(repository).findAll();
            verify(adapter, times(2)).toResponse(any(Lista.class));
        }
    }

    @Nested
    class GetListByIdTests {
        @Test
        void getListByIdShouldReturnListResponse() {
            Lista lista = createList();
            ListaResponse listaResponse = new ListaResponse();

            when(repository.findById("id")).thenReturn(Optional.of(lista));
            when(adapter.toResponse(lista)).thenReturn(listaResponse);

            ListaResponse result = service.getListById("id");

            assertNotNull(result);
            verify(repository).findById("id");
            verify(adapter).toResponse(lista);
        }

        @Test
        void getListByIdShouldThrowExceptionWhenNotFound() {
            when(repository.findById("id")).thenReturn(Optional.empty());

            RuntimeException thrown = assertThrows(RuntimeException.class, () -> service.getListById("id"));

            assertEquals("Lista não encontrada", thrown.getMessage());
            verify(repository).findById("id");
        }
    }

    @Nested
    class RemoveLIstTests {

        @Test
        void removeListShouldCallDeleteById() {
            doNothing().when(repository).deleteById("id");

            service.removeList("id");

            verify(repository).deleteById("id");
        }
    }

    private Lista createList() {
        return new Lista();
    }
}
