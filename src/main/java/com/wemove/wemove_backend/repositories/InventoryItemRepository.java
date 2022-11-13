package com.wemove.wemove_backend.repositories;

import com.wemove.wemove_backend.entities.InventoryItem;
import com.wemove.wemove_backend.entities.MoveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Integer> {

    @Query("SELECT i FROM InventoryItem i WHERE i.moveRequestId = :id ")
    List<InventoryItem> findAllInventoryItemByMoveRequest(int id);
}
