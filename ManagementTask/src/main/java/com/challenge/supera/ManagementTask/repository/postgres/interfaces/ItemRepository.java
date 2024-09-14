package com.challenge.supera.ManagementTask.repository.postgres.interfaces;

import com.challenge.supera.ManagementTask.domain.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, String> {
    List<Item> findByListId(String listId);
}
