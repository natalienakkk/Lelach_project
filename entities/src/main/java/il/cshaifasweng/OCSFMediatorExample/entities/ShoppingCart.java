
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
    private int itemsNum;
    private String usernamee;
//    @OneToMany(
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
//            targetEntity = Item.class
//    )
    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@ManyToMany(mappedBy = "cartList", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = Item.class)
    private List<Item> items= new ArrayList<Item>();

    @OneToOne (mappedBy = "cart")
    private Order order ;

    @ElementCollection private List<Double> amount = new ArrayList<Double>();
    public ShoppingCart() { }
    public int gettotalPrice(ShoppingCart cart)
    {
        int price = 0;
        for (int i = 0; i< items.size() ; i++) {
            price += items.get(i).getPrice() * amount.get(i);
        }
        this.totalPrice = price;
        return price;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void AddtoCart (Item item)
    {
        items.add(item);
        itemsNum++;
    }
    public void RemovefromCart (int i)
    {
        items.remove(items.get(i));
        amount.remove(amount.get(i));
        itemsNum--;
    }
    public void Addamount (double a){ amount.add(a); }

    public List<Double> getAmount() {
        return amount;
    }

    public void setAmount(List<Double> amount) {
        this.amount = amount;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getItemsNum() {
        return itemsNum;
    }

    public void setItemsNum(int itemsNum) {
        this.itemsNum = itemsNum;
    }

    public String getUsernamee() { return usernamee; }

    public void setUsernamee(String usernamee) { this.usernamee = usernamee; }


}
