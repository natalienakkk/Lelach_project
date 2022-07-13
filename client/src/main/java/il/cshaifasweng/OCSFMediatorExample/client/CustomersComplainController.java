package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;


import il.cshaifasweng.OCSFMediatorExample.entities.Complain;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CustomersComplainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back_button;

    @FXML
    private TextField complain;

    @FXML
    private Button complain_button;

    @FXML
    private ComboBox<String> complain_type;

    @FXML
    private TextField order_id;

    @FXML
    private TextField username;

    @FXML
    private Text welcome_username;

    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.now();

    @FXML
    void complain_butt(ActionEvent event) {
        System.out.println("hello"+order_id.getText());
        if(order_id.getText().equals(""))
            System.out.println("bye");
        try {
            SimpleClient.getClient().sendToServer(new Message("#client complain",username.getText(),complain.getText(),complain_type.getSelectionModel().getSelectedItem(),order_id.getText(),time,date));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void back(ActionEvent event) {
        try {
            App.setRoot("catalog");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void initialize() {
        assert complain != null : "fx:id=\"complain\" was not injected: check your FXML file 'CustomerComplain.fxml'.";
        assert complain_button != null : "fx:id=\"complain_button\" was not injected: check your FXML file 'CustomerComplain.fxml'.";
        assert complain_type != null : "fx:id=\"complain_type\" was not injected: check your FXML file 'CustomerComplain.fxml'.";
        assert order_id != null : "fx:id=\"order_id\" was not injected: check your FXML file 'CustomerComplain.fxml'.";
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'CustomerComplain.fxml'.";
        assert welcome_username != null : "fx:id=\"welcome_username\" was not injected: check your FXML file 'CustomerComplain.fxml'.";
        complain_type.getItems().add("Order Complain");
        complain_type.getItems().add("General Complain");
        username.clear();
        complain.clear();
        order_id.clear();

    }

}
