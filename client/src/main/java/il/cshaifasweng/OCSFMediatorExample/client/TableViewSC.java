package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.scene.control.Button;

import java.awt.*;

public class TableViewSC {
    String name;
    Double Price;
    Double Amount;
    Double Total_Price;
    javafx.scene.control.Button button;

    public TableViewSC(String name, Double price, Double amount, Double total_Price , javafx.scene.control.Button button) {
        this.name = name;
        Price = price;
        Amount = amount;
        Total_Price = total_Price;
        this.button = button;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return Price;
    }

    public Double getAmount() {
        return Amount;
    }

    public Double getTotal_Price() {
        return Total_Price;
    }

    public Button getButton() {
        return button;
    }
}
