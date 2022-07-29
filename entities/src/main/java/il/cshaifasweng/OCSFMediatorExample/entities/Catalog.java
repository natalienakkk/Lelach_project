package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "catalog")

public class Catalog implements Serializable {
    private static final long serialVersionUID = 4412085390748840478L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "items")
    private List<Item> items =new ArrayList<Item>();
    public Item getitem(int i){
        return items.get(i);
    }
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    private int amount=0;
    public Catalog(){}
    public void addIteam(Item item)
    {
        boolean flag= true;
        items.add(item);
        updateAmount(flag);;

    }
    public void updateAmount(boolean flag)
    {
        if(flag)
            amount++;
        else amount--;
    }
    public void removeIteam(Item item)
    {
        boolean flag=false;
        items.remove(item);
        updateAmount(flag);
    }
}
