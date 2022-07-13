package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.PasswordField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController {
    @FXML public static String type;
    @FXML public static String getType() { return type; }
    @FXML public static void setType(String type) { LoginController.type = type; }
    @FXML public static List<Item> itemList = new ArrayList<Item>();
    @FXML public static List<Item> getItemList() { return itemList; }
    @FXML public static void setItemList(List<Item> itemList) { CatalogController.itemList = itemList; }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Back;

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane Pane1;

    @FXML
    private PasswordField PasswordButton;

    @FXML
    private TextField UserNameButton;

    @FXML
    void Back(ActionEvent event) throws IOException {
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
//        Pane1.getChildren().setAll(pane);
        try {
            App.setRoot("homepage");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void loginButton(ActionEvent event) {
        User User = new User(UserNameButton.getText(), PasswordButton.getText());
        try {
            SimpleClient.getClient().sendToServer(new Message("#LoginRequest", User));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'Login.fxml'.";
        assert loginButton != null : "fx:id=\"LoginClick\" was not injected: check your FXML file 'Login.fxml'.";
        assert Pane1 != null : "fx:id=\"Pane1\" was not injected: check your FXML file 'Login.fxml'.";
        assert PasswordButton != null : "fx:id=\"PasswordButton\" was not injected: check your FXML file 'Login.fxml'.";
        assert UserNameButton != null : "fx:id=\"UserNameButton\" was not injected: check your FXML file 'Login.fxml'.";


    }

}
