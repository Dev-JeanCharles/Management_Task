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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @Id
    @Column(name = "id")
    private String id = null;

    @NotEmpty(message = "Título não pode ser vazio")
    @Size(max = 100, message = "Título não pode ter mais de 100 caracteres")
    private String titulo;

    @Size(max = 255, message = "Descrição não pode ter mais de 255 caracteres")
    private String descricao;

    private boolean concluido;
    private boolean destacado;

    @ManyToOne
    @JoinColumn(name = "lista_id")
    private Lista lista;

    @PrePersist
    public void prePersist() {
        this.id = generatedItemId();
    }

    private String generatedItemId() {
        GeneratorId generator = new GeneratorIdImpl();
        return generator.generatedItemId();
    }
}
