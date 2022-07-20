package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class BranchManagerController {

    @FXML private static String username;

    @FXML public static String getUsername() { return username; }

    @FXML public static void setUsername(String username) { BranchManagerController.username = username; }

    @FXML
    private AnchorPane pane1;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> Choose_button;

    @FXML
    private DatePicker end_date;

    @FXML
    private Button show_report;

    @FXML
    private DatePicker start_date;

    @FXML
    private Text branch_name;

    String Type;
    LocalDate start,end;


    @FXML
    void Report_Type_butt(ActionEvent event) {
        Type=Choose_button.getSelectionModel().getSelectedItem();
    }

    @FXML
    void end_butt(ActionEvent event) {
        end=end_date.getValue();
        //end1=end.toString();
    }

    @FXML
    void show_butt(ActionEvent event) {
        System.out.println(Type+start+end);
        try {
            SimpleClient.getClient().sendToServer(new Message("#show Report",Type,start,end));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void start_butt(ActionEvent event) {
        start=start_date.getValue();
        //start1=start.toString();

    }

    @FXML
    void initialize() {
        assert Choose_button != null : "fx:id=\"Choose_button\" was not injected: check your FXML file 'BranchManagerController.fxml'.";
        assert end_date != null : "fx:id=\"end_date\" was not injected: check your FXML file 'BranchManagerController.fxml'.";
        assert show_report != null : "fx:id=\"show_report\" was not injected: check your FXML file 'BranchManagerController.fxml'.";
        assert start_date != null : "fx:id=\"start_date\" was not injected: check your FXML file 'BranchManagerController.fxml'.";
        assert start_date != null : "fx:id=\"start_date\" was not injected: check your FXML file 'CEO.fxml'.";
        Choose_button.getItems().add("Revenue Report");
        Choose_button.getItems().add("Complain Report");
        Choose_button.getItems().add("Orders Report");
        setUsername(App.getUsername());
        branch_name.setText("Welcome " + username);
    }

}
