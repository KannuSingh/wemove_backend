package com.wemove.wemove_backend.entities;

import javax.persistence.*;

@Entity
@Table(name = "move_request_inventory")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int moveRequestId;
    @Column(name="item_category")
    private String category;
    @Column(name="item_name")
    private String itemName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoveRequestId() {
        return moveRequestId;
    }

    public void setMoveRequestId(int moveRequestId) {
        this.moveRequestId = moveRequestId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
