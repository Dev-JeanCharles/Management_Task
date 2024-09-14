package com.challenge.supera.ManagementTask.application.web.dto.requesties;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ListaRequest {

    @NotEmpty(message = "Nome não pode ser vazio")
    @Size(max = 100, message = "Nome não pode ter mais de 100 caracteres")
    private String nome;
}
