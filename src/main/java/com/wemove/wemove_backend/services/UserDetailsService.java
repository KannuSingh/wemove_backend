package com.wemove.wemove_backend.services;

import com.wemove.wemove_backend.entities.Credentials;
import com.wemove.wemove_backend.entities.ResetPassword;
import com.wemove.wemove_backend.entities.UserCredentials;
import com.wemove.wemove_backend.entities.UserDetails;
import com.wemove.wemove_backend.repositories.UserCredentialsRepository;
import com.wemove.wemove_backend.repositories.UserDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;
    private final UserCredentialsRepository userCredentialsRepository;

    public UserDetailsService(UserDetailsRepository userDetailsRepository,
                              UserCredentialsRepository userCredentialsRepository) {
        this.userDetailsRepository = userDetailsRepository;
        this.userCredentialsRepository = userCredentialsRepository;
    }

    public UserDetails registerUser(UserDetails userDetails){
        System.out.println("UserDetailsService : "+userDetails);
        UserCredentials uc = new UserCredentials();
        uc.setEmail(userDetails.getEmail());
        uc.setPassword(userDetails.getPassword());
        uc.setUserType(userDetails.getUserType());
        userCredentialsRepository.save(uc);
        return userDetailsRepository.save(userDetails);
    }

    public UserDetails checkCredentials(Credentials _userCredentials) {
        Optional<UserCredentials> optionalUserCredentials = userCredentialsRepository.findUserCredentialsByEmail(_userCredentials.getEmail());
        UserCredentials usercredentials = optionalUserCredentials.isPresent() ? optionalUserCredentials.get():null;
        if(usercredentials!=null && usercredentials.getPassword().equals(_userCredentials.getPassword()) ) {
            Optional<UserDetails> optionalUserDetails = userDetailsRepository.findUserDetailsByEmail(_userCredentials.getEmail());
            return optionalUserDetails.isPresent() ?optionalUserDetails.get():null;
        }
        return null;
    }

    public String getSecurityQuestion(String email) {
        Optional<UserDetails> optionalUserDetails = userDetailsRepository.findUserDetailsByEmail(email);
        return optionalUserDetails.isPresent() ?optionalUserDetails.get().getSecurityQuestion():"User Not Found";

    }

    public boolean forgotPassword(ResetPassword resetPasswordBody) {
        Optional<UserDetails> optionalUserDetails = userDetailsRepository.findUserDetailsByEmail(resetPasswordBody.getEmail());
        UserDetails userDetails = optionalUserDetails.isPresent() ? optionalUserDetails.get():null;
        if(userDetails!=null && userDetails.getSecurityQuestion().equals(resetPasswordBody.getSecurityQuestion())
                && userDetails.getSecurityAnswer().equals(resetPasswordBody.getSecretAnswer())  ){
            UserCredentials uc = new UserCredentials();
            uc.setEmail(resetPasswordBody.getEmail());
            uc.setPassword(resetPasswordBody.getNewPassword());
            uc.setUserType(userDetails.getUserType());
            userCredentialsRepository.save(uc);
            return true;
        }
        return false;
    }

    public UserDetails getUser(String email) {
        return userDetailsRepository.findUserDetailsByEmail(email).get();

    }
}
