package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.ShoppingCart;

import java.util.List;

public class catalogEvent {
    public String type;
    public List<Item> itemList;
    public ShoppingCart cart ;
    public String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public catalogEvent(String type, List<Item> itemList, String message) {
        this.type = type;
        this.itemList = itemList;
        this.message = message;
    }

    public catalogEvent(String type, List<Item> itemList) {
        this.type = type;
        this.itemList = itemList;
        if(itemList==null){
            System.out.println("event list is null");
        }
    }
    public catalogEvent(String type, List<Item> itemList, ShoppingCart cart) {
        this.type = type;
        this.itemList = itemList;
        this.cart = cart;
        if(itemList==null){
            System.out.println("event list is null");
        }
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart1) {
        this.cart = cart1;
    }
}
