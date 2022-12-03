package com.wemove.wemove_backend.entities;

import java.util.List;

public class MoveRequestDto {

    private MoveRequest moveRequest;
    private List<PriceQuote> priceQuotes;

    public MoveRequest getMoveRequest() {
        return moveRequest;
    }

    public void setMoveRequest(MoveRequest moveRequest) {
        this.moveRequest = moveRequest;
    }

    public List<PriceQuote> getPriceQuotes() {
        return priceQuotes;
    }

    public void setPriceQuotes(List<PriceQuote> priceQuotes) {
        this.priceQuotes = priceQuotes;
    }
}
