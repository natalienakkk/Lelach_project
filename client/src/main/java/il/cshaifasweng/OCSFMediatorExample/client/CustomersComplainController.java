package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;


import il.cshaifasweng.OCSFMediatorExample.entities.Complain;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Registration;
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
    private Text Name;

    @FXML
    private Text order1;

    @FXML
    private TextField complain;

    @FXML
    private Button complain_button;

    @FXML
    private ComboBox<String> complain_type;

    @FXML
    private TextField order_id;

    @FXML
    private Text welcome_username;

    LocalDate date = LocalDate.now();
    LocalTime time = LocalTime.now();
    Registration username;

    public Registration getUsername() {
        return username;
    }

    public void setUsername(Registration username) {
        this.username = username;
    }

    @FXML
    void complain_butt(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer(new Message("#client complain", username.getUserName(), complain.getText(), complain_type.getSelectionModel().getSelectedItem(), order_id.getText(), time, date));

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            App.setRoot("catalog");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void complain_type(ActionEvent event) {
        if(complain_type.getSelectionModel().getSelectedItem().equals("General Complain"))
        {
            order_id.setVisible(false);
            order1.setVisible(false);
        }
        else
        {
            order_id.setVisible(true);
            order1.setVisible(true);
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
        setUsername(App.getUser1());
        complain.clear();
        order_id.clear();
        Name.setText("Welcome "+username.getUserName());

    }

}
