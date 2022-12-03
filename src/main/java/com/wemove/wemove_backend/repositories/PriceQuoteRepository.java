package com.wemove.wemove_backend.repositories;

import com.wemove.wemove_backend.entities.MoveRequest;
import com.wemove.wemove_backend.entities.PriceQuote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PriceQuoteRepository extends JpaRepository<PriceQuote, Integer> {


    @Query("SELECT pq FROM PriceQuote pq WHERE pq.moveRequestId = :requestId")
    List<PriceQuote> findAllPriceQuoteByMoveRequestId(int requestId);

    @Query("SELECT pq FROM PriceQuote pq WHERE pq.movers.email = :moverEmail")
    List<PriceQuote> findPriceQuoteByMoverEmail(String moverEmail);
    @Modifying
    @Transactional
    @Query("update PriceQuote pq set pq.quoteStatus = com.wemove.wemove_backend.entities.QuoteStatus.ACCEPTED where pq.movers.email = :moverEmail AND pq.moveRequestId= :moveRequestId")
    void updatePriceQuoteStatusToAccepted(String moverEmail, int moveRequestId);

    @Modifying
    @Transactional
    @Query("update PriceQuote pq set pq.quoteStatus = com.wemove.wemove_backend.entities.QuoteStatus.DECLINED where pq.movers.email = :moverEmail AND pq.moveRequestId= :moveRequestId")
    void updatePriceQuoteStatusToDeclined(String moverEmail, int moveRequestId);
}
