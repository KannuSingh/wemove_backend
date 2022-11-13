package com.wemove.wemove_backend.controller;

import com.wemove.wemove_backend.entities.Credentials;
import com.wemove.wemove_backend.entities.MoveRequest;
import com.wemove.wemove_backend.entities.UserDetails;
import com.wemove.wemove_backend.exceptions.InvalidCredentials;
import com.wemove.wemove_backend.exceptions.MoveRequestCreationException;
import com.wemove.wemove_backend.services.MoveRequestService;
import com.wemove.wemove_backend.services.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class CustomerController {

    private final MoveRequestService moveRequestService;

    public CustomerController(MoveRequestService moveRequestService) {
        this.moveRequestService = moveRequestService;
    }


    @PostMapping("/createMoveRequest")
    public boolean createMoveRequest(@RequestBody MoveRequest moveRequest){
        MoveRequest _moveRequest = moveRequestService.createMoveRequest(moveRequest);
        if (_moveRequest == null) throw new MoveRequestCreationException("Error Occurred While creating move request.");
        return true;

    }

    @PostMapping("/getAllMoveRequest")
    public List<MoveRequest> createMoveRequest(@RequestParam String email){
        return moveRequestService.getAllMoveRequest(email);
    }
}
