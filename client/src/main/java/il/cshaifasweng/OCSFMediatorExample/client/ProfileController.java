package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Complain;
import il.cshaifasweng.OCSFMediatorExample.entities.Registration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class ProfileController {

    @FXML private static List<Complain> complain_list;

    @FXML public static List<Complain> getComplain_list() { return complain_list; }

    @FXML public static void setComplain_list(List<Complain> complain_list) { ProfileController.complain_list = complain_list; }

    @FXML public static String type;

    @FXML public static String getType() { return type; }

    @FXML public static void setType(String type) { ProfileController.type = type; }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Complain, String> answer;

    @FXML
    private Button back_button;

    @FXML
    private TableColumn<Complain, String> message;

    @FXML
    private TableColumn<Complain, Double> refund;

    @FXML
    private TableColumn<Complain, String> status;

    @FXML
    private TableView<Complain> table;

    @FXML
    private Text title;

    Registration username;

    public Registration getUsername() { return username; }

    public void setUsername(Registration username) { this.username = username; }

    @FXML
    void back(ActionEvent event) {
        try {
            App.setRoot("catalog");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Complain> getList2() {
        ObservableList<Complain> List = FXCollections.observableArrayList();
        for (int i = 0; i < complain_list.size(); i++) {
            if (complain_list.get(i).getUsername().equals(username.getUserName())) {
                List.add(new Complain(complain_list.get(i).getMessage(), complain_list.get(i).getStatus(), complain_list.get(i).getRefund(), complain_list.get(i).getAnswer()));
            }
        }
        return List;
    }

    @FXML
    void initialize() {
        assert answer != null : "fx:id=\"answer\" was not injected: check your FXML file 'Profile.fxml'.";
        assert back_button != null : "fx:id=\"back_button\" was not injected: check your FXML file 'Profile.fxml'.";
        assert message != null : "fx:id=\"message\" was not injected: check your FXML file 'Profile.fxml'.";
        assert refund != null : "fx:id=\"refund\" was not injected: check your FXML file 'Profile.fxml'.";
        assert status != null : "fx:id=\"status\" was not injected: check your FXML file 'Profile.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'Profile.fxml'.";
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'Profile.fxml'.";

        setComplain_list(App.getComplain_list());
        setUsername(App.getUser1());

            message.setCellValueFactory(new PropertyValueFactory<Complain, String>("message"));
            refund.setCellValueFactory(new PropertyValueFactory<Complain, Double>("refund"));
            status.setCellValueFactory(new PropertyValueFactory<Complain, String>("status"));
            answer.setCellValueFactory(new PropertyValueFactory<Complain, String>("answer"));
            table.setItems(getList2());
            table.getColumns().addAll();


    }

}
