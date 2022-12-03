package com.wemove.wemove_backend.controller;

import com.wemove.wemove_backend.entities.*;
import com.wemove.wemove_backend.exceptions.MoveRequestCreationException;
import com.wemove.wemove_backend.services.MoveRequestService;
import com.wemove.wemove_backend.services.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class CustomerController {

    private final MoveRequestService moveRequestService;
    private final ReviewService reviewService;

    public CustomerController(MoveRequestService moveRequestService, ReviewService reviewService) {
        this.moveRequestService = moveRequestService;
        this.reviewService = reviewService;
    }


    @PostMapping("/createMoveRequest")
    public boolean createMoveRequest(@RequestBody MoveRequest moveRequest){
        MoveRequest _moveRequest = moveRequestService.createMoveRequest(moveRequest);
        if (_moveRequest == null) throw new MoveRequestCreationException("Error Occurred While creating move request.");
        return true;

    }

    @PostMapping("/getAllCustomerMoveRequest")
    public List<MoveRequestDto> getAllCustomerMoveRequest(@RequestParam String email){
        return moveRequestService.getAllCustomerMoveRequest(email);
    }

    @PostMapping("/acceptPriceQuote")
    public boolean acceptPriceQuote(@RequestParam String moverEmail, @RequestParam int moveRequestId){
        moveRequestService.acceptPriceQuote(moverEmail,moveRequestId);
        moveRequestService.updateMoveRequestStatus(moveRequestId,MoveStatus.ACCEPTED);
        return true;
    }
    @PostMapping("/declinePriceQuote")
    public boolean declinePriceQuote(@RequestParam String moverEmail, @RequestParam int moveRequestId){
        moveRequestService.declinePriceQuote(moverEmail,moveRequestId);
        return true;
    }

    @PostMapping("/addReview")
    public Review addReview(@RequestBody Review review){
        return this.reviewService.addReview(review);
    }


    @GetMapping("/getStatusTimeline")
    public List<MRStatusItem> getStatusTimeline( @RequestParam int moveRequestId){
        return moveRequestService.getMoveStatusTimeline(moveRequestId);
    }


}
