package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ComplainEvent {

    private List<Complain> complain_list;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ComplainEvent(List<Complain> complain_list, String type) {
        this.complain_list = complain_list;
        this.type = type;
    }

    public ComplainEvent(List<Complain> complain_list) {
        this.complain_list = complain_list;
    }

    public ComplainEvent(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    private List<Order>orderList;
    private Order order;
    //private List<Order>order_list;


    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

//    public List<Order> getOrder_list() {
//        return order_list;
//    }

//    public void setOrder_list(List<Order> order_list) {
//        this.order_list = order_list;
//    }


    public ComplainEvent(List<Complain> complain_list, List<Order> orderList) {
        this.complain_list = complain_list;
        this.orderList = orderList;
    }

    public ComplainEvent( String type,List<Order> orderList) {
        this.type = type;
        this.orderList = orderList;
    }
// public ComplainEvent(List<Complain> complain_list) { this.complain_list = complain_list; }

    public List<Complain> getComplain_list() { return complain_list; }

    public void setComplain_list(List<Complain> complain_list) { this.complain_list = complain_list; }

}
