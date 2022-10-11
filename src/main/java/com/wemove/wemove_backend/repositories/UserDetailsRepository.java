package com.wemove.wemove_backend.repositories;

import com.wemove.wemove_backend.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {


}
