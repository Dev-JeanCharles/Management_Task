package com.challenge.supera.ManagementTask.repository.postgres.interfaces;

import com.challenge.supera.ManagementTask.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
