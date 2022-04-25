package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import javax.xml.namespace.QName;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String color;
    private String picture;
    private double price;

    @ManyToOne
    @JoinColumn(name = "items_id")
    private Catalog items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
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


    public Catalog getItems() {
        return items;
    }

    public void setItems(Catalog items) {
        this.items = items;
    }


}
