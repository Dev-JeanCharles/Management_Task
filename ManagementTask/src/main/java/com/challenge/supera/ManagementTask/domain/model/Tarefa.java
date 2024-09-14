package com.challenge.supera.ManagementTask.domain.model;

import com.challenge.supera.ManagementTask.repository.postgres.interfaces.GeneratorId;
import com.challenge.supera.ManagementTask.repository.postgres.interfaces.imp.GeneratorIdImpl;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarefa {
    @Id
    @Column(name = "id")
    private String id = null;

    private String nome;

    @OneToMany(mappedBy = "tarefa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itens = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.id = generatedTarefaId();
    }

    private String generatedTarefaId() {
        GeneratorId generator = new GeneratorIdImpl();
        return generator.generatedTarefaId();
    }
}
