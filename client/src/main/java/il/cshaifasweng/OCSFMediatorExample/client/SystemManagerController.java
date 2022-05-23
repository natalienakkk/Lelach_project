package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class SystemManagerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField username_txt;

    @FXML
    private Button Type_button;

    @FXML
    private TextField block_text;

    @FXML
    private Button blockaccount_button;

    @FXML
    private Text manager_name;

    @FXML
    private TextField message;

    @FXML
    private Button back;


    @FXML
    private Button message_button;

    @FXML
    private TextField status_text1;

    @FXML
    private AnchorPane pane1;

    @FXML
    private TextField status_text2;

    String username;
    String worker_username;
    //String clientmessage;
    //String client_username;
    String new_status;

    @FXML
    void blockaccount_butt(ActionEvent event) throws IOException {
        username=block_text.getText();
        System.out.println(username);
        SimpleClient.getClient().sendToServer(new Message("#block account",username));
    }

    @FXML
    void message_butt(ActionEvent event) throws IOException{
//        client_username=username_txt.getText();
//        clientmessage=message.getText();
        //System.out.println("info"+client_username+clientmessage);
        SimpleClient.getClient().sendToServer(new Message("#send message",username_txt.getText(),message.getText()));
    }

    @FXML
    void Type_butt(ActionEvent event) throws IOException{
        worker_username=status_text1.getText();
        new_status=status_text2.getText();
        System.out.println("info"+ worker_username+new_status);
        SimpleClient.getClient().sendToServer(new Message("#update worker type",worker_username,new_status));
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        pane1.getChildren().setAll(pane);
    }



    @FXML
    void initialize() {
        assert Type_button != null : "fx:id=\"Type_button\" was not injected: check your FXML file 'SystemManager.fxml'.";
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'SystemManager.fxml'.";
        assert block_text != null : "fx:id=\"block_text\" was not injected: check your FXML file 'SystemManager.fxml'.";
        assert blockaccount_button != null : "fx:id=\"blockaccount_button\" was not injected: check your FXML file 'SystemManager.fxml'.";
        assert manager_name != null : "fx:id=\"manager_name\" was not injected: check your FXML file 'SystemManager.fxml'.";
        assert pane1 != null : "fx:id=\"pane1\" was not injected: check your FXML file 'SystemManager.fxml'.";
        assert message != null : "fx:id=\"message\" was not injected: check your FXML file 'SystemManager.fxml'.";
        assert message_button != null : "fx:id=\"message_button\" was not injected: check your FXML file 'SystemManager.fxml'.";
        assert status_text1 != null : "fx:id=\"status_text1\" was not injected: check your FXML file 'SystemManager.fxml'.";
        assert status_text2 != null : "fx:id=\"status_text2\" was not injected: check your FXML file 'SystemManager.fxml'.";
        assert username_txt != null : "fx:id=\"username_txt\" was not injected: check your FXML file 'SystemManager.fxml'.";
    }

}
