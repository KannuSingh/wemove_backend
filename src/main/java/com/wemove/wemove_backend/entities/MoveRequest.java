package com.wemove.wemove_backend.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "move_request")
public class MoveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int moveRequestId;
    @Column(name="status")
    private MoveStatus moveRequestStatus;
    @Column(name="owner_email")
    private String moveRequestOwner;
    @Column(name="created_on")
    private String createdOn;
    @Column(name="updated_on")
    private String updatedOn;
    @Column(name="title")
    private String moveTitle;
    @Column(name="type")
    private String moveType;
    @Column(name="move_date")
    private String moveDate;
    @Column(name="move_help")
    private MoveHelp moveHelp;
    @Column(name="pickup_floor_level")
    private String pickupFloorLevel;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pickup_address_id", referencedColumnName = "id")
    private Address pickupAddress;
    @Column(name="delivery_floor_level")
    private String deliveryFloorLevel;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_address_id", referencedColumnName = "id")
    private Address deliveryAddress;
    @Transient
    private List<InventoryItemGroup> itemInventory;
    @Column(name="other_details")
    private String otherDetails;

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    public String getMoveRequestOwner() {
        return moveRequestOwner;
    }

    public void setMoveRequestOwner(String moveRequestOwner) {
        this.moveRequestOwner = moveRequestOwner;
    }

    public int getMoveRequestId() {
        return moveRequestId;
    }

    public void setMoveRequestId(int moveRequestId) {
        this.moveRequestId = moveRequestId;
    }

    public MoveStatus getMoveRequestStatus() {
        return moveRequestStatus;
    }

    public void setMoveRequestStatus(MoveStatus moveRequestStatus) {
        this.moveRequestStatus = moveRequestStatus;
    }

    public String getMoveTitle() {
        return moveTitle;
    }

    public void setMoveTitle(String moveTitle) {
        this.moveTitle = moveTitle;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getMoveType() {
        return moveType;
    }

    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }

    public String getMoveDate() {
        return moveDate;
    }

    public void setMoveDate(String moveDate) {
        this.moveDate = moveDate;
    }

    public MoveHelp getMoveHelp() {
        return moveHelp;
    }

    public void setMoveHelp(MoveHelp moveHelp) {
        this.moveHelp = moveHelp;
    }

    public String getPickupFloorLevel() {
        return pickupFloorLevel;
    }

    public void setPickupFloorLevel(String pickupFloorLevel) {
        this.pickupFloorLevel = pickupFloorLevel;
    }

    public Address getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(Address pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getDeliveryFloorLevel() {
        return deliveryFloorLevel;
    }

    public void setDeliveryFloorLevel(String deliveryFloorLevel) {
        this.deliveryFloorLevel = deliveryFloorLevel;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<InventoryItemGroup> getItemInventory() {
        return itemInventory;
    }

    public void setItemInventory(List<InventoryItemGroup> itemInventory) {
        this.itemInventory = itemInventory;
    }

    @Override
    public String toString() {
        return "MoveRequest{" +
                "moveRequestId='" + moveRequestId + '\'' +
                ", moveRequestStatus=" + moveRequestStatus +
                ", moveRequestOwner='" + moveRequestOwner + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", updatedOn='" + updatedOn + '\'' +
                ", moveTitle='" + moveTitle + '\'' +
                ", moveType='" + moveType + '\'' +
                ", moveDate='" + moveDate + '\'' +
                ", moveHelp=" + moveHelp +
                ", pickupFloorLevel='" + pickupFloorLevel + '\'' +
                ", pickupAddress=" + pickupAddress +
                ", deliveryFloorLevel='" + deliveryFloorLevel + '\'' +
                ", deliveryAddress=" + deliveryAddress +
                ", itemInventory=" + itemInventory +
                ", otherDetails='" + otherDetails + '\'' +
                '}';
    }
}
