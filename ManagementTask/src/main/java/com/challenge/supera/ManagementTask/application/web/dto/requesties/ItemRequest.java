package com.challenge.supera.ManagementTask.application.web.dto.requesties;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ItemRequest {

    @NotEmpty(message = "Título não pode ser vazio")
    @Size(max = 100, message = "Título não pode ter mais de 100 caracteres")
    private String titulo;

    @Size(max = 255, message = "Descrição não pode ter mais de 255 caracteres")
    private String descricao;

    private boolean concluido;
    private boolean destacado;

    @NotEmpty(message = "ID da lista não pode ser vazio")
    private String listaId;
}
