package il.cshaifasweng.OCSFMediatorExample.client;

public class TableViewSC {
    String name;
    Double Price;
    Double Amount;
    Double Total_Price;

    public TableViewSC(String name, Double price, Double amount, Double total_Price) {
        this.name = name;
        Price = price;
        Amount = amount;
        Total_Price = total_Price;
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
}
