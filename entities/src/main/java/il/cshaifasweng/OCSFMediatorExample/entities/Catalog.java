package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "catalog")

public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "items")
    private List<Item> items =new ArrayList<Item>();
    private int amount=0;
    Catalog(){}
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
