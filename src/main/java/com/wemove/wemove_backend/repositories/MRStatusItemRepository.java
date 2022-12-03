package com.wemove.wemove_backend.repositories;

import com.wemove.wemove_backend.entities.MRStatusItem;
import com.wemove.wemove_backend.entities.MoveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MRStatusItemRepository extends JpaRepository<MRStatusItem, Integer> {


    @Query("SELECT statusItem FROM MRStatusItem statusItem WHERE statusItem.moveRequestId = :moveRequestId order by statusItem.createdOn ASC" )
    List<MRStatusItem> findAllForMoveRequest(int moveRequestId);
}
