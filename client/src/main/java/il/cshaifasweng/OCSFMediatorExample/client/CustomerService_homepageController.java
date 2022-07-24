package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Complain;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
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
    void complain1(ActionEvent event) {
        try {
            System.out.println("1");
            SimpleClient.getClient().sendToServer(new Message("#complain list" ));
            System.out.println("2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Back(ActionEvent event) throws IOException {
        App.setRoot("homepage");
    }

    @FXML
    void initialize() {
        assert complain != null : "fx:id=\"complain\" was not injected: check your FXML file 'CustomerService_homepage.fxml'.";
        setUsername(App.getUsername());
        name.setText("Welcome " + username);
    }

}
