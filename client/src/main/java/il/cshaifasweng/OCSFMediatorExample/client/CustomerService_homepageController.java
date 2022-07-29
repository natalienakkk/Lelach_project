package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Complain;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Registration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class CustomerService_homepageController {

    @FXML private static String username;

    @FXML public static String getUsername() { return username; }

    @FXML public static void setUsername(String username) { CustomerService_homepageController.username = username; }

    @FXML
    private Text name;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button complain;

    @FXML
    private Button logout;

    @FXML
    void logout1(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer(new Message("#LogOut" , username1));
            App.setRoot("homepage");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void complain1(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer(new Message("#complain list" ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Registration username1;

    public Registration getUsername1() { return username1; }

    public void setUsername1(Registration username1) { this.username1 = username1; }

    @FXML
    void initialize() {
        assert logout != null : "fx:id=\"logout\" was not injected: check your FXML file 'CustomerService_homepage.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'CustomerService_homepage.fxml'.";
        assert complain != null : "fx:id=\"complain\" was not injected: check your FXML file 'CustomerService_homepage.fxml'.";
        setUsername1(App.getUser1());
        name.setText("Welcome " + username1.getUserName());
    }

}
