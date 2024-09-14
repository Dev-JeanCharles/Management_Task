package com.challenge.supera.ManagementTask;

import com.challenge.supera.ManagementTask.application.web.builder.TarefaBuilder;
import com.challenge.supera.ManagementTask.application.web.dto.requesties.TarefaRequest;
import com.challenge.supera.ManagementTask.application.web.dto.responses.TarefaResponse;
import com.challenge.supera.ManagementTask.domain.model.Tarefa;
import com.challenge.supera.ManagementTask.repository.postgres.interfaces.TarefaRepository;
import com.challenge.supera.ManagementTask.repository.postgres.interfaces.adapter.TarefaAdapter;
import com.challenge.supera.ManagementTask.service.imp.TarefaService;
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

public class TarefaServiceTest {

    @Mock
    private TarefaRepository repository;

    @Mock
    private TarefaBuilder builder;

    @Mock
    private TarefaAdapter adapter;

    @InjectMocks
    private TarefaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(adapter.toResponse(any(Tarefa.class))).thenAnswer(invocation -> {
            Tarefa tarefa = invocation.getArgument(0);
            return TarefaResponse.builder()
                    .id(tarefa.getId())
                    .nome(tarefa.getNome())
                    .itens(new ArrayList<>())
                    .build();
        });
    }

    @Nested
    class CreateTests {

        @Test
        void createShouldReturnTarefaResponse() {
            TarefaRequest request = new TarefaRequest();
            Tarefa tarefa = createTarefa();
            Tarefa savedTarefa = createTarefa();
            TarefaResponse tarefaResponse = new TarefaResponse();

            when(builder.toTarefaEntity(request)).thenReturn(tarefa);
            when(repository.save(tarefa)).thenReturn(savedTarefa);
            when(adapter.toResponse(savedTarefa)).thenReturn(tarefaResponse);

            TarefaResponse result = service.create(request);

            assertNotNull(result);
            verify(builder).toTarefaEntity(request);
            verify(repository).save(tarefa);
            verify(adapter).toResponse(savedTarefa);
        }
    }

    @Nested
    class GetAllTasksTests {

        @Test
        void getAllTasksShouldReturnListOfTarefaResponse() {
            Tarefa tarefa1 = createTarefa();
            Tarefa tarefa2 = createTarefa();
            TarefaResponse response1 = new TarefaResponse();
            TarefaResponse response2 = new TarefaResponse();

            when(repository.findAll()).thenReturn(List.of(tarefa1, tarefa2));
            when(adapter.toResponse(tarefa1)).thenReturn(response1);
            when(adapter.toResponse(tarefa2)).thenReturn(response2);

            List<TarefaResponse> result = service.getAllTasks();

            assertNotNull(result);
            assertEquals(2, result.size());

            verify(repository).findAll();
            verify(adapter, times(2)).toResponse(any(Tarefa.class));
        }
    }

    @Nested
    class GetTaskByIdTests {
        @Test
        void getTaskByIdShouldReturnTarefaResponse() {
            Tarefa tarefa = createTarefa();
            TarefaResponse tarefaResponse = new TarefaResponse();

            when(repository.findById("id")).thenReturn(Optional.of(tarefa));
            when(adapter.toResponse(tarefa)).thenReturn(tarefaResponse);

            TarefaResponse result = service.getTaskById("id");

            assertNotNull(result);
            verify(repository).findById("id");
            verify(adapter).toResponse(tarefa);
        }

        @Test
        void getTaskByIdShouldThrowExceptionWhenNotFound() {
            when(repository.findById("id")).thenReturn(Optional.empty());

            RuntimeException thrown = assertThrows(RuntimeException.class, () -> service.getTaskById("id"));

            assertEquals("Tarefa não encontrada", thrown.getMessage());
            verify(repository).findById("id");
        }
    }

    @Nested
    class RemoveTaskTests {

        @Test
        void removeTaskShouldCallDeleteById() {
            doNothing().when(repository).deleteById("id");

            service.removeTask("id");

            verify(repository).deleteById("id");
        }
    }

    private Tarefa createTarefa() {
        return new Tarefa();
    }
}
