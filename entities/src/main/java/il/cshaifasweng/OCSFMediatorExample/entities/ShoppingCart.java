
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
//    @OneToMany(
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
//            targetEntity = Item.class
//    )
    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ManyToMany(mappedBy = "cartList", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    //@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = Item.class)
    private List<Item> items= new ArrayList<Item>();

    @OneToOne (mappedBy = "cart" , cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
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
}
