package com.challenge.supera.ManagementTask.domain.model;

import com.challenge.supera.ManagementTask.repository.postgres.interfaces.GeneratorId;
import com.challenge.supera.ManagementTask.repository.postgres.interfaces.imp.GeneratorIdImpl;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
public class Lista {
    @Id
    @Column(name = "id")
    private String id = null;

    @NotEmpty(message = "Nome não pode ser vazio")
    @Size(max = 100, message = "Nome não pode ter mais de 100 caracteres")
    private String nome;

    @OneToMany(mappedBy = "lista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itens = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.id = generatedTarefaId();
    }

    private String generatedTarefaId() {
        GeneratorId generator = new GeneratorIdImpl();
        return generator.generatedListId();
    }
}
