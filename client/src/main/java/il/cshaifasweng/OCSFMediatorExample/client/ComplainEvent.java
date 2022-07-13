package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Complain;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import il.cshaifasweng.OCSFMediatorExample.entities.Report;
import il.cshaifasweng.OCSFMediatorExample.entities.ShoppingCart;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ComplainEvent {

    private List<Complain> complain_list;
    private List<ShoppingCart>shoppingCartList;
    private List<Order>orderList;
    //private List<Order>order_list;


    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<ShoppingCart> getShoppingCartList() {
        return shoppingCartList;
    }

    public void setShoppingCartList(List<ShoppingCart> shoppingCartList) {
        this.shoppingCartList = shoppingCartList;
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

   // public ComplainEvent(List<Complain> complain_list) { this.complain_list = complain_list; }

    public List<Complain> getComplain_list() { return complain_list; }

    public void setComplain_list(List<Complain> complain_list) { this.complain_list = complain_list; }

}
