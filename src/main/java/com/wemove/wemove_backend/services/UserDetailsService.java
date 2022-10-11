package com.wemove.wemove_backend.services;

import com.wemove.wemove_backend.entities.UserDetails;
import com.wemove.wemove_backend.repositories.UserDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    public UserDetailsService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    public UserDetails registerUser(UserDetails userDetails){
        System.out.println("UserDetailsService : "+userDetails);
        return userDetailsRepository.save(userDetails);
    }
}
