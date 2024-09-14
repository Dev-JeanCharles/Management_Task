package com.challenge.supera.ManagementTask.repository.postgres.interfaces;

import com.challenge.supera.ManagementTask.domain.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository  extends JpaRepository<Tarefa, String> {
}
