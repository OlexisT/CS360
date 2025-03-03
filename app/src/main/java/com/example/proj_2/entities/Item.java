package com.example.proj_2.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"itemName"}, unique = true)})
public class Item {
    @PrimaryKey(autoGenerate = true)
    public int id;
    private String itemName;
    private Integer itemQuantity;

    //constructor class


    public void setItemName(String itemName) throws IllegalArgumentException {
        if (itemName.trim().isEmpty()) {
            throw new IllegalArgumentException("Enter Valid Item");
        }
        this.itemName = itemName;
    }

    public String getItemName(){
        return itemName;
    }


    public void setItemQuantity(String itemQuantity) throws IllegalArgumentException {
        if (itemQuantity.trim().isEmpty()) {
            throw new IllegalArgumentException("Enter Valid quantity of Items");
        }
        this.itemQuantity = Integer.valueOf(itemQuantity);
    }

    public void setItemQuantity(Integer itemQuantity) throws IllegalArgumentException {
        this.itemQuantity = itemQuantity;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

}

