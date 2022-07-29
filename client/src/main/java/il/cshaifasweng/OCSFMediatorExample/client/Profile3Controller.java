package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Complain;
import il.cshaifasweng.OCSFMediatorExample.entities.Registration;
import il.cshaifasweng.OCSFMediatorExample.entities.SystemManagers_Messages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Profile3Controller {

    @FXML private static List<SystemManagers_Messages> message_list;

    public static List<SystemManagers_Messages> getMessage_list() { return message_list; }

    public static void setMessage_list(List<SystemManagers_Messages> message_list) { Profile3Controller.message_list = message_list; }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<SystemManagers_Messages, String> messages;

    @FXML
    private TableView<SystemManagers_Messages> table;

    @FXML
    private Button back_button;


//    @FXML
//    void back(ActionEvent event) {
//        try {
//            App.setRoot("catalog");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    Registration username;

    public Registration getUsername() { return username; }

    public void setUsername(Registration username) { this.username = username; }

    public ObservableList<SystemManagers_Messages> getList2() {
        ObservableList<SystemManagers_Messages> List = FXCollections.observableArrayList();
        for (int i = 0; i < message_list.size(); i++) {
            if (message_list.get(i).getClient_username().equals(username.getUserName())) {
                List.add(new SystemManagers_Messages(message_list.get(i).getMessage()));
            }
        }
        return List;
    }
    @FXML
    void initialize() {
       // assert back_button != null : "fx:id=\"back_button\" was not injected: check your FXML file 'Profile3.fxml'.";
        assert messages != null : "fx:id=\"messages\" was not injected: check your FXML file 'Profile3.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'Profile3.fxml'.";
        setMessage_list(App.getMessage_list());
        setUsername(App.getUser1());
        messages.setCellValueFactory(new PropertyValueFactory<SystemManagers_Messages, String>("message"));
        table.setItems(getList2());
        table.getColumns().addAll();

    }

    public void back(ActionEvent actionEvent) {
                try {
            App.setRoot("catalog");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
