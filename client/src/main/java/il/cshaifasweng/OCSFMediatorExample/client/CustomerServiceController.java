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
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerServiceController {

    @FXML private static List<Complain> list;

    @FXML public static void setList(List<Complain> list) { CustomerServiceController.list = list; }

    @FXML public static List<Complain> getList() { return list; }

    @FXML public static List<Order> list3;

    @FXML public static List<Order> getList3() { return list3; }

    @FXML public static void setList3(List<Order> list3) { CustomerServiceController.list3 = list3; }


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back_button;

    @FXML
    private TableView<Complain> complain_table;

    @FXML
    private TableColumn<Complain, LocalDate> date;

    @FXML
    private TableColumn<Complain, Long> order_id;

    @FXML
    private TableColumn<Complain, String> status;

    @FXML
    private TableColumn<Complain, LocalTime> time;

    @FXML
    private TableColumn<Complain, String> username;

    @FXML
    private TableColumn<Complain, Long> complain_id;

    @FXML
    private Button continue_button;

    @FXML
    private TableColumn<Complain, LocalDate> deadline_date;

    @FXML
    private TableColumn<Complain, LocalTime> deadline_time;


    public static Complain complain;
    public static Order order1;

    public static Order getOrder1() {
        return order1;
    }

    public static void setOrder1(Order order1) {
        CustomerServiceController.order1 = order1;
    }

    @FXML
    private TableColumn<Complain, String> type;

    @FXML
    void continue1(ActionEvent event) {
        ObservableList<Complain> complaint=complain_table.getSelectionModel().getSelectedItems();
        if(!(complaint.isEmpty()))
        {
            for(int i=0;i<list.size();i++) {
                if(list.get(i).getId().equals(complaint.get(0).getId())) {
                    complain=complaint.get(0);
                }
            }

            for(int i=0;i<list3.size();i++) {
                if(list3.get(i).getId().equals(complaint.get(0).getOrderID())) {
                    setOrder1(list3.get(i));
                }
            }
            try {
            App.setRoot("CustomerService2");
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

    }

    public static Complain getComplain() {
        return complain;
    }

    public static void setComplain(Complain complain) {
        CustomerServiceController.complain = complain;
    }


    @FXML
    void back(ActionEvent event) {
        try {
            App.setRoot("CustomerService_homepage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ObservableList<Complain> getList2() {
        ObservableList<Complain> List = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getStatus().equals("new complain"))
                List.add(new Complain(list.get(i).getComplain_type(), list.get(i).getDate(), list.get(i).getTime(), list.get(i).getStatus(),list.get(i).getUsername(),list.get(i).getId(),list.get(i).getOrderID(),list.get(i).getMessage(),list.get(i).getDate().plusDays(1),list.get(i).getTime().plusHours(24)));
        }
        return List;
    }


    @FXML
    void initialize() {
        assert back_button != null : "fx:id=\"back_button\" was not injected: check your FXML file 'CustomerComplain.fxml'.";
        assert complain_table != null : "fx:id=\"complain_table\" was not injected: check your FXML file 'CustomerService.fxml'.";
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'CustomerService.fxml'.";
        assert order_id != null : "fx:id=\"order_id\" was not injected: check your FXML file 'CustomerService.fxml'.";
        assert status != null : "fx:id=\"status\" was not injected: check your FXML file 'CustomerService.fxml'.";
        assert time != null : "fx:id=\"time\" was not injected: check your FXML file 'CustomerService.fxml'.";
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'CustomerService.fxml'.";
        assert complain_id != null : "fx:id=\"complain_id\" was not injected: check your FXML file 'CustomerService.fxml'.";
        setList(App.getComplain_list());
        setList3(App.getOrderList());

        username.setCellValueFactory(new PropertyValueFactory<Complain,String>("username"));
        time.setCellValueFactory(new PropertyValueFactory<Complain,LocalTime>("time"));
        date.setCellValueFactory(new PropertyValueFactory<Complain,LocalDate>("date"));
        status.setCellValueFactory(new PropertyValueFactory<Complain,String>("status"));
        type.setCellValueFactory(new PropertyValueFactory<Complain,String>("complain_type"));
        order_id.setCellValueFactory(new PropertyValueFactory<Complain,Long>("orderID"));
        complain_id.setCellValueFactory(new PropertyValueFactory<Complain,Long>("id"));
        deadline_time.setCellValueFactory(new PropertyValueFactory<Complain,LocalTime>("deadline_time"));
        deadline_date.setCellValueFactory(new PropertyValueFactory<Complain,LocalDate>("deadline_date"));
        complain_table.setItems(getList2());
        complain_table.getColumns().addAll();



    }

}
