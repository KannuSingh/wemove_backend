package com.wemove.wemove_backend.repositories;

import com.wemove.wemove_backend.entities.MoveRequest;
import com.wemove.wemove_backend.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MoveRequestRepository  extends JpaRepository<MoveRequest, Integer> {

    @Query("SELECT mr FROM MoveRequest mr WHERE mr.moveRequestOwner = :email ")
    List<MoveRequest> findAllMoveRequestByOwner(String email);
}
