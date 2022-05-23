package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class HomepageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button GuestButton;

    @FXML
    private ImageView HomePageImage;

    @FXML
    private Button LoginButton;

    @FXML
    private AnchorPane Pane1;

    @FXML
    private Button SignUpButton;

    @FXML
    void GuestButton(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#opencatalog", "Guest"));
    }

    @FXML
    void LoginButton(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Pane1.getChildren().setAll(pane);
    }

    @FXML
    void SignUpButton(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("SingUp.fxml"));
        Pane1.getChildren().setAll(pane);
    }

    @FXML
    void initialize() {
        assert GuestButton != null : "fx:id=\"GuestButton\" was not injected: check your FXML file 'HomePage.fxml'.";
        assert HomePageImage != null : "fx:id=\"HomePageImage\" was not injected: check your FXML file 'HomePage.fxml'.";
        assert LoginButton != null : "fx:id=\"LoginButton\" was not injected: check your FXML file 'HomePage.fxml'.";
        assert Pane1 != null : "fx:id=\"Pane1\" was not injected: check your FXML file 'HomePage.fxml'.";
        assert SignUpButton != null : "fx:id=\"SignUpButton\" was not injected: check your FXML file 'HomePage.fxml'.";

    }

}
