package com.wemove.wemove_backend.services;

import com.wemove.wemove_backend.repositories.UserCredentialsRepository;
import com.wemove.wemove_backend.security.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserCredentialsService implements UserDetailsService {


    private final UserCredentialsRepository userCredentialsRepository;

    public JpaUserCredentialsService(UserCredentialsRepository userCredentialsRepository) {
        this.userCredentialsRepository = userCredentialsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("JpaUserDetailsService: "+ email);
        var uc = userCredentialsRepository.findUserCredentialsByEmail(email);
        return uc.map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found "+email));

    }
}
