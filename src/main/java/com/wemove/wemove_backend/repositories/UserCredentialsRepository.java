package com.wemove.wemove_backend.repositories;

import com.wemove.wemove_backend.entities.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials,Integer> {

    @Query("SELECT uc FROM UserCredentials uc WHERE uc.email = :email ")
    Optional<UserCredentials> findUserCredentialsByEmail(String email);
}
