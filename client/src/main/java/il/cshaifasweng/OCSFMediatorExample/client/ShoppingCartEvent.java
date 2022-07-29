package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.ShoppingCart;

import java.util.List;

public class ShoppingCartEvent {
    public ShoppingCart cart ;
    public List<Item> items;

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public ShoppingCartEvent(ShoppingCart cart) {
        this.cart = cart;
    }

    public ShoppingCartEvent(List<Item> items) {
        this.items = items;
    }
}
