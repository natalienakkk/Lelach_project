package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Order;

import java.util.List;

public class CancelOrderEvent {
    private List<Order> MyOrdersList;

    public List<Order> getMyOrdersList() {
        return MyOrdersList;
    }

    public void setMyOrdersList(List<Order> myOrdersList) {
        MyOrdersList = myOrdersList;
    }

    public CancelOrderEvent(List<Order> myOrdersList) {
        MyOrdersList = myOrdersList;
    }
}
