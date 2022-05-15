
package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ShoppingCart")

public class ShoppingCart implements Serializable {
    @Serial
    private static final long serialVersionUID = -7298179656588461445L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double totalPrice;
    private int discount;
    private int itemsNum =0;
    private boolean deliveryOp;
    @ManyToMany
    private List<Item> items= new ArrayList<Item>();
    @ElementCollection
    private List<Integer> amount = new ArrayList<Integer>();

    public ShoppingCart() {
    }

    public int gettotalPrice(){
        int price = 0;
        for (int i = 0; i< items.size() ; i++)
        {
            price += items.get(i).getPrice()* amount.get(i);
        }
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean isDeliveryOp() {
        return deliveryOp;
    }

    public void setDeliveryOp(boolean deliveryOp) {
        this.deliveryOp = deliveryOp;
    }

    public void AddtoCart (Item item){
        items.add(item);
        itemsNum++;
    }

    public void RemovefromCart (Item item) {
        items.remove(item);
        itemsNum--;
    }

    public void Addamount (int a){
        amount.add(a);
    }

}
