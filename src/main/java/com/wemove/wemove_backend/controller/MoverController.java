package com.wemove.wemove_backend.controller;

import com.wemove.wemove_backend.entities.*;
import com.wemove.wemove_backend.services.MoveRequestService;
import com.wemove.wemove_backend.services.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController()
public class MoverController {

    private final MoveRequestService moveRequestService;

    private final ReviewService reviewService;

    public MoverController(MoveRequestService moveRequestService, ReviewService reviewService) {
        this.moveRequestService = moveRequestService;
        this.reviewService = reviewService;
    }


    @PostMapping("/submitPriceQuote")
    public boolean submitPriceQuote(@RequestBody PriceQuote priceQuote){
        return moveRequestService.submitPriceQuote(priceQuote);
    }

    @PostMapping("/getAllPriceQuote")
    public List<PriceQuote> getAllPriceQuote(@RequestParam int requestId){
        return moveRequestService.getAllPriceQuote(requestId);
    }

    @PostMapping("/getPriceQuoteByMoverEmail")
    public List<PriceQuote> getPriceQuoteByMoverEmail(@RequestParam String moverEmail){
        return moveRequestService.getPriceQuoteByMoverEmail(moverEmail);
    }
    @GetMapping("/getReviews")
    public List<Review> getReviews( @RequestParam String moverEmail){
        return reviewService.getAllReviews(moverEmail);
    }



    @PostMapping("/getAllMoveRequestDeliveries")
    public List<MoveRequestDto> getAllMoveRequestDeliveries(){
        return moveRequestService.getAllMoveRequestDeliveries();
    }
    @PostMapping("/getMyMoveRequestDeliveries")
    public List<MoveRequestDto> getAllMoveRequestDeliveries( @RequestParam String moverEmail){
        System.out.println("getMyMoveRequestDeliveries"+moverEmail);
        return moveRequestService.getMyAllMoveRequestDeliveries(moverEmail);
    }

    @PostMapping("/getAllCompletedMoveRequest")
    public List<MoveRequestDto> getAllCompletedMoveRequest(@RequestParam String moverEmail){
        System.out.println("getAllCompletedMoveRequest: "+moverEmail);
        return moveRequestService.getAllCompletedMoveRequest(moverEmail);
    }


    @PostMapping("/updateMoveRequestStatus")
    public void updateMoveRequestStatus(@RequestParam int moveRequestId, @RequestParam MoveStatus moveStatus){
        moveRequestService.updateMoveRequestStatus(moveRequestId, moveStatus);
    }




}
