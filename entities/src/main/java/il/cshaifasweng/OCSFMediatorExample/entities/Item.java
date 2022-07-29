package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "items")
public class Item implements Serializable {
    private static final long serialVersionUID = 7030377024343093717L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String color;
    private String picture;
    private double price;

//    @ManyToOne
//    private Catalog items;

//    @ManyToMany(mappedBy = "items",
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
//            targetEntity = ShoppingCart.class
//    )
    //@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //private ShoppingCart cartList;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
//    @JoinTable(
//            name = "items_shoppingcart",
//            joinColumns = {@JoinColumn(name = "item_id")},
//            inverseJoinColumns = {@JoinColumn(name = "shoppingcart_id")}
//    )
    //@JoinColumn(name = "Shoppingcart_id")
    @ManyToMany(mappedBy = "items", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = ShoppingCart.class)
    private List<ShoppingCart> cartList= new ArrayList<ShoppingCart>();

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER,targetEntity = Image.class)
//    private Image image;

    public Item() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Item(String name, String type, String color, String picture, double price) {
        this.name = name;
        this.type = type;
        this.color = color;
        this.picture = picture;
        this.price = price;
    }

    public Item(String name , double price) {
        this.name = name;
        this.price = price;
    }


//    public Catalog getItems() {
//        return items;
//    }
//
//    public void setItems(Catalog items) {
//        this.items = items;
//    }

    public List<ShoppingCart> getCartList() {
        return cartList;
    }

    public void setCartList(List<ShoppingCart> cartList) {
        this.cartList = cartList;
    }
}
