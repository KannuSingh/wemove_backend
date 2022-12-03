package com.wemove.wemove_backend.entities;

import javax.persistence.*;
@Entity
@Table(name = "move_request_quotes")
public class PriceQuote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    private int moveRequestId;

    @JoinColumn(name = "mover_email", referencedColumnName = "email")
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private UserDetails movers;
    private QuoteStatus quoteStatus;
    @Column(name="price_quote")
    private String price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QuoteStatus getQuoteStatus() {
        return quoteStatus;
    }

    public void setQuoteStatus(QuoteStatus quoteStatus) {
        this.quoteStatus = quoteStatus;
    }

    public int getMoveRequestId() {
        return moveRequestId;
    }

    public void setMoveRequestId(int moveRequestId) {
        this.moveRequestId = moveRequestId;
    }

    public UserDetails getMovers() {
        return movers;
    }

    public void setMovers(UserDetails movers) {
        this.movers = movers;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
