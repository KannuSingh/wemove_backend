package com.wemove.wemove_backend.repositories;

import com.wemove.wemove_backend.entities.MoveRequest;
import com.wemove.wemove_backend.entities.MoveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MoveRequestRepository  extends JpaRepository<MoveRequest, Integer> {

    @Query("SELECT mr FROM MoveRequest mr WHERE mr.moveRequestOwner = :email ")
    List<MoveRequest> findAllMoveRequestByOwner(String email);

    @Query("SELECT mr FROM MoveRequest mr WHERE mr.moveRequestStatus = com.wemove.wemove_backend.entities.MoveStatus.CREATED OR mr.moveRequestStatus = com.wemove.wemove_backend.entities.MoveStatus.SUGGESTED")
    List<MoveRequest> findAllMoveRequestForStatusActive();



    @Modifying
    @Transactional
    @Query("update MoveRequest mr set mr.moveRequestStatus = :moveStatus where mr.moveRequestId= :moveRequestId")
    void updateMoveRequestStatus( int moveRequestId, MoveStatus moveStatus);

}
