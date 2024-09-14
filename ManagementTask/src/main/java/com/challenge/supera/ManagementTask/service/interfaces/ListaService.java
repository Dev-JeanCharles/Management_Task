package com.challenge.supera.ManagementTask.service.interfaces;

import com.challenge.supera.ManagementTask.application.web.dto.requesties.ListaRequest;
import com.challenge.supera.ManagementTask.application.web.dto.responses.ListaResponse;
import com.challenge.supera.ManagementTask.domain.model.Lista;

import java.util.List;

public interface ListaService {
    ListaResponse createList(ListaRequest request);
    List<ListaResponse> getAllList();
    ListaResponse getListById(String id);
    Lista getListByIdEntity(String id);
    void removeList(String id);
}
