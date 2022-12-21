package com.wemove.wemove_backend.entities;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    private String review;
    private String moveRequestId;
    private String customerEmail;
    private String moverEmail;
    private float rating;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getMoveRequestId() {
        return moveRequestId;
    }

    public void setMoveRequestId(String moveRequestId) {
        this.moveRequestId = moveRequestId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getMoverEmail() {
        return moverEmail;
    }

    public void setMoverEmail(String moverEmail) {
        this.moverEmail = moverEmail;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
