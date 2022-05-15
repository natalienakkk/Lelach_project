package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class HomepageController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Guest_btn;

    @FXML
    private Button Manager_btn;

    @FXML
    private Button Client_btn1;

    @FXML
    private AnchorPane Pane1;

    @FXML
    private Text welcome;

    @FXML
    void Client_btn(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#opencatalog", "Client"));
        App.setRoot("catalog");
    }

    @FXML
    void Guest_btn(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#opencatalog", "Guest"));
        App.setRoot("catalog");

    }

    @FXML
    void Manager_btn(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#opencatalog", "Manager"));
        App.setRoot("catalog");
    }

    @FXML
    void initialize() {
        assert Guest_btn != null : "fx:id=\"Guest_btn\" was not injected: check your FXML file 'homepage.fxml'.";
        assert Manager_btn != null : "fx:id=\"Manager_btn\" was not injected: check your FXML file 'homepage.fxml'.";
        assert Pane1 != null : "fx:id=\"Pane1\" was not injected: check your FXML file 'homepage.fxml'.";
        assert welcome != null : "fx:id=\"welcome\" was not injected: check your FXML file 'homepage.fxml'.";
        assert Client_btn1 != null : "fx:id=\"Client_btn1\" was not injected: check your FXML file 'homepage.fxml'.";

    }

}
