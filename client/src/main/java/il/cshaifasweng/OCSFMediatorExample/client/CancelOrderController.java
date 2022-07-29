package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.greenrobot.eventbus.EventBus;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;


public class CancelOrderController {
    @FXML private static List<Order> MyOrdersList;
    private static Registration unregClient;
    private static int status = 0;
    private Order MyOrder;

    public Order getMyOrder() {
        return MyOrder;
    }

    public void setMyOrder(Order myOrder) {
        MyOrder = myOrder;
    }

    @FXML public static List<Order> getMyOrdersList() {
        return MyOrdersList;
    }

    @FXML public static void setMyOrdersList(List<Order> myOrdersList) {
        MyOrdersList = myOrdersList;
    }

    public static Registration getUnregClient() {
        return unregClient;
    }

    public static void setUnregClient(Registration unregClient) {
        CancelOrderController.unregClient = unregClient;
    }

    public static int getStatus() {
        return status;
    }

    public static void setStatus(int status) {
        CancelOrderController.status = status;
    }

    @FXML
    private Button BackBtn;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Order> HistoryTable;

    @FXML
    private TableColumn<Order, String> ClientID_col;

    @FXML
    private TableColumn<Order, Long> OrderID_col;

    @FXML
    private TableColumn<Order, String> Price_col;

    @FXML
    private TableColumn<Order, String> Status_col;

    @FXML
    private TableColumn<Order, String> Time_col;

    @FXML
    private TableColumn<Order, String> Date_col;

    @FXML
    private Button CancelBtn;

    @FXML
    private TextArea Policy;

    @FXML
    void Back(ActionEvent event) throws IOException{
        try{
            App.setRoot("catalog");
        } catch (IOException e){
            e.printStackTrace();
    }
}

//    @FXML
//    void CancelPurchase (ActionEvent event)
//    {
//        Order order = HistoryTable.getSelectionModel().getSelectedItem();
//        List<Order> orderList= getMyOrdersList();
//        for(int i=0; i< orderList.size();i++)
//        {
//            if(orderList.get(i).getId() == order.getId())
//            {
//                order = orderList.get(i);
//                break;
//            }
//        }
//        if(order == null)
//        {
//            Warning New_Warning = new Warning("You Didnt Select An Order!");
//            EventBus.getDefault().post(new WarningEvent((Warning) New_Warning));
//            return;
//        }
//        else
//        {
//            if (order.getStatus().equalsIgnoreCase("Canceled"))
//            {
//                Warning New_Warning = new Warning("This Order Cannot Be canceled!");
//                EventBus.getDefault().post(new WarningEvent((Warning) New_Warning));
//                return;
//            }
//            else
//            {
//               order.setStatus("Canceled");
//               HistoryTable.refresh();
//                try {
//                    SimpleClient.getClient().sendToServer(new Message("#CancelOrder", order, unregClient));
//                } catch (IOException e){
//                    e.printStackTrace();
//                }
//            }
//        }
////        ObservableList<Order> orders = HistoryTable.getSelectionModel().getSelectedItems();
////        if(orders.isEmpty())
////        {
////            Warning New_Warning = new Warning("You're Order History Is Empty!");
////            EventBus.getDefault().post(new WarningEvent((Warning) New_Warning));
////            return;
////        }
////        else if(orders == null)
////        {
////            Warning New_Warning = new Warning("You Didnt Select An Order!");
////            EventBus.getDefault().post(new WarningEvent((Warning) New_Warning));
////            return;
////        }
////        else
////        {
////            for(int i=0 ; i < MyOrdersList.size() ; i++)
////            {
////                if(MyOrdersList.get(i).getId().equals(orders.get(0).getId()))
////                {
////                    MyOrder = orders.get(0);
////                }
////            }
////            if (MyOrder.getStatus().contains("Canceled"))
////            {
////                Warning New_Warning = new Warning("This Order Cannot Be canceled!");
////                EventBus.getDefault().post(new WarningEvent((Warning) New_Warning));
////                return;
////            }
////            else
////            {
////                try {
////                    SimpleClient.getClient().sendToServer(new Message("#CancelOrder", MyOrder.getClientid()));
////                } catch (IOException e){
////                    e.printStackTrace();
////                }
////            }
////        }
//    }

    @FXML
    void CancelPurchase (ActionEvent event)
    {
        Order order1 = HistoryTable.getSelectionModel().getSelectedItem();
        Order order= new Order();
        List<Order> orderList= getMyOrdersList();
        for(int i=0; i< orderList.size();i++)
        {
            if(orderList.get(i).getClientid() == order1.getClientid())
            {
                order = orderList.get(i);
                break;
            }
        }
        System.out.println(order.getTotalprice());
        System.out.println(order.getRecievetime());
        System.out.println(order.getRecievedate());
        if(order == null)
        {
            Warning New_Warning = new Warning("You Didnt Select An Order!");
            EventBus.getDefault().post(new WarningEvent((Warning) New_Warning));
            return;
        }
        else
        {
            if (order1.getStatus().equals("Canceled") || order1.getStatus().equals("Delivered"))
            {
                Warning New_Warning = new Warning("This Order Cannot Be canceled!");
                EventBus.getDefault().post(new WarningEvent((Warning) New_Warning));
                return;
            }
            else
            {
                order1.setStatus("Canceled");
                order.setStatus("Canceled");
                HistoryTable.refresh();
                try {
                    SimpleClient.getClient().sendToServer(new Message("#CancelOrder", order, unregClient));
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
//        ObservableList<Order> orders = HistoryTable.getSelectionModel().getSelectedItems();
//        if(orders.isEmpty())
//        {
//            Warning New_Warning = new Warning("You're Order History Is Empty!");
//            EventBus.getDefault().post(new WarningEvent((Warning) New_Warning));
//            return;
//        }
//        else if(orders == null)
//        {
//            Warning New_Warning = new Warning("You Didnt Select An Order!");
//            EventBus.getDefault().post(new WarningEvent((Warning) New_Warning));
//            return;
//        }
//        else
//        {
//            for(int i=0 ; i < MyOrdersList.size() ; i++)
//            {
//                if(MyOrdersList.get(i).getId().equals(orders.get(0).getId()))
//                {
//                    MyOrder = orders.get(0);
//                }
//            }
//            if (MyOrder.getStatus().contains("Canceled"))
//            {
//                Warning New_Warning = new Warning("This Order Cannot Be canceled!");
//                EventBus.getDefault().post(new WarningEvent((Warning) New_Warning));
//                return;
//            }
//            else
//            {
//                try {
//                    SimpleClient.getClient().sendToServer(new Message("#CancelOrder", MyOrder.getClientid()));
//                } catch (IOException e){
//                    e.printStackTrace();
//                }
//            }
//        }
    }
    public ObservableList<Order> populateTable()
    {
        ObservableList<Order> PurchaseList = FXCollections.observableArrayList();
        for(int i = 0 ; i < MyOrdersList.size() ; i++)
        {
            if(MyOrdersList.get(i).getClientid().equals(unregClient.getClient_ID()))
            {
                if(MyOrdersList.get(i).getStatus().equals("pending") ||MyOrdersList.get(i).getStatus().equals("Delivered") )
                {
                    PurchaseList.add(new Order(MyOrdersList.get(i).getId(), MyOrdersList.get(i).getRecievetime(), MyOrdersList.get(i).getRecievedate(), MyOrdersList.get(i).getClientid(), MyOrdersList.get(i).getTotalprice(), MyOrdersList.get(i).getStatus()));
                }
            }
        }

        return PurchaseList;
    }

    @FXML
    void initialize() {
        assert CancelBtn != null : "fx:id=\"Cancelbtn\" was not injected: check your FXML file 'CancelOrder.fxml'.";
        assert ClientID_col != null : "fx:id=\"ClientID_col\" was not injected: check your FXML file 'CancelOrder.fxml'.";
        assert HistoryTable != null : "fx:id=\"HistoryTable\" was not injected: check your FXML file 'CancelOrder.fxml'.";
        assert OrderID_col != null : "fx:id=\"OrderID_col\" was not injected: check your FXML file 'CancelOrder.fxml'.";
        assert Price_col != null : "fx:id=\"Price_col\" was not injected: check your FXML file 'CancelOrder.fxml'.";
        assert Status_col != null : "fx:id=\"Status_col\" was not injected: check your FXML file 'CancelOrder.fxml'.";
        assert Time_col != null : "fx:id=\"Time_col\" was not injected: check your FXML file 'CancelOrder.fxml'.";
        assert Date_col != null : "fx:id=\"Date_col\" was not injected: check your FXML file 'CancelOrder.fxml'.";
        setMyOrdersList(App.getOrderList());
        setUnregClient(App.getUser1());

        Price_col.setCellValueFactory(new PropertyValueFactory<Order, String>("totalprice"));
        OrderID_col.setCellValueFactory(new PropertyValueFactory<Order , Long>("id"));
        Status_col.setCellValueFactory(new PropertyValueFactory<Order , String>("status"));
        Time_col.setCellValueFactory(new PropertyValueFactory<Order , String>("recievetime"));
        Date_col.setCellValueFactory(new PropertyValueFactory<Order , String>("recievedate"));
        ClientID_col.setCellValueFactory(new PropertyValueFactory<Order , String>("clientid"));
        HistoryTable.setItems(populateTable());
        HistoryTable.getColumns().addAll();
    }
}