package com.wemove.wemove_backend.repositories;

import com.wemove.wemove_backend.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {

    @Query("SELECT ud FROM UserDetails ud WHERE ud.email = :email ")
    Optional<UserDetails> findUserDetailsByEmail(String email);

}
