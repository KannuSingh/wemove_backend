package com.wemove.wemove_backend.entities;

import java.util.List;

public class InventoryItemGroup {

    private String category;
    private List<String> items;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }


}
