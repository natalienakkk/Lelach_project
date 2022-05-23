package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;

import java.util.List;

public class catalogEvent {
    public String type;
    public List<Item> itemList;

    public catalogEvent(String type, List<Item> itemList) {
        System.out.println("in catalogEvent");
        this.type = type;
        this.itemList = itemList;
        if(itemList==null){
            System.out.println("event list is null");
        }
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
