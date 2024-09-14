package com.challenge.supera.ManagementTask.repository.postgres.interfaces;

import com.challenge.supera.ManagementTask.domain.model.Lista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaRepository extends JpaRepository<Lista, String> {
}
