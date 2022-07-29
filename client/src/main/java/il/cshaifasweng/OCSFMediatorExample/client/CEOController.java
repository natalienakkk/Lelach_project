package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Registration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.time.LocalDate;

public class CEOController {

    @FXML private static String username;

    @FXML public static String getUsername() { return username; }

    @FXML public static void setUsername(String username) { CEOController.username = username; }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Branch_button;

    @FXML
    private Text CEO_Name;

    @FXML
    private ComboBox<String> Choose_button;

    @FXML
    private Button CompareReports;

    @FXML
    private DatePicker end_date;

    @FXML
    private DatePicker firstr_end;

    @FXML
    private DatePicker firstr_start;

    @FXML
    private AnchorPane pane1;

    @FXML
    private DatePicker secondr_end;

    @FXML
    private DatePicker secondr_start;

    @FXML
    private Button show_report;

    @FXML
    private DatePicker start_date;

    @FXML
    private ComboBox<String> store_name;

    String Type,storeName;
    LocalDate start,end,first_start,second_start,first_end,second_end;

    @FXML
    void Branch_Butt(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("BranchManager.fxml"));
        pane1.getChildren().setAll(pane);
    }
    @FXML
    void Report_Type_butt(ActionEvent event) {
        Type=Choose_button.getSelectionModel().getSelectedItem();
    }

    @FXML
    void CompareReports_butt(ActionEvent event) {
        if (Type.equals("Complain Report")) {
            try {
                SimpleClient.getClient().sendToServer(new Message("#show Report to compare", "Complain Compare", first_start, first_end, second_start, second_end,storeName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(Type.equals("Revenue Report")){
            try {
                SimpleClient.getClient().sendToServer(new Message("#show Report to compare", "Revenue Compare", first_start, first_end, second_start, second_end,storeName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(Type.equals("Orders Report")){
            try {
                SimpleClient.getClient().sendToServer(new Message("#show Report to compare", "Orders Compare", first_start, first_end, second_start, second_end,storeName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    void end_butt(ActionEvent event) {
        end=end_date.getValue();
    }

    @FXML
    void firstr_end_butt(ActionEvent event) {
        first_end=firstr_end.getValue();
    }

    @FXML
    void firstr_start_butt(ActionEvent event) {
        first_start=firstr_start.getValue();
    }

    @FXML
    void secondr_end_butt(ActionEvent event) {
        second_end=secondr_end.getValue();
    }

    @FXML
    void secondr_start_butt(ActionEvent event) {
        second_start=secondr_start.getValue();
    }

    @FXML
    void show_butt(ActionEvent event) throws IOException {
        try {
            SimpleClient.getClient().sendToServer(new Message("#show Report",Type,start,end,"Lelach"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void start_butt(ActionEvent event) {
        start=start_date.getValue();
    }

    Registration username1;

    public Registration getUsername1() { return username1; }

    public void setUsername1(Registration username1) { this.username1 = username1; }

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
    void choose_store(ActionEvent event) {
        storeName = store_name.getSelectionModel().getSelectedItem();
    }

    @FXML
    void initialize() {
        assert Branch_button != null : "fx:id=\"Branch_button\" was not injected: check your FXML file 'CEO.fxml'.";
        assert CEO_Name != null : "fx:id=\"CEO_Name\" was not injected: check your FXML file 'CEO.fxml'.";
        assert Choose_button != null : "fx:id=\"Choose_button\" was not injected: check your FXML file 'CEO.fxml'.";
        assert CompareReports != null : "fx:id=\"CompareReports\" was not injected: check your FXML file 'CEO.fxml'.";
        assert end_date != null : "fx:id=\"end_date\" was not injected: check your FXML file 'CEO.fxml'.";
        assert firstr_end != null : "fx:id=\"firstr_end\" was not injected: check your FXML file 'CEO.fxml'.";
        assert firstr_start != null : "fx:id=\"firstr_start\" was not injected: check your FXML file 'CEO.fxml'.";
        assert pane1 != null : "fx:id=\"pane1\" was not injected: check your FXML file 'CEO.fxml'.";
        assert secondr_end != null : "fx:id=\"secondr_end\" was not injected: check your FXML file 'CEO.fxml'.";
        assert secondr_start != null : "fx:id=\"secondr_start\" was not injected: check your FXML file 'CEO.fxml'.";
        assert show_report != null : "fx:id=\"show_report\" was not injected: check your FXML file 'CEO.fxml'.";
        assert logout != null : "fx:id=\"logout\" was not injected: check your FXML file 'BranchManager.fxml'.";
        assert store_name != null : "fx:id=\"store_name\" was not injected: check your FXML file 'CEO.fxml'.";
        assert start_date != null : "fx:id=\"start_date\" was not injected: check your FXML file 'CEO.fxml'.";
        Choose_button.getItems().add("Revenue Report");
        Choose_button.getItems().add("Orders Report");
        Choose_button.getItems().add("Complain Report");
        store_name.getItems().add("Lelach, Haifa");
        store_name.getItems().add("Lelach, Tel Aviv");
        store_name.getItems().add("Lelach");
        setUsername1(App.getUser1());
        CEO_Name.setText("Welcome "+username1.getUserName());
    }

}
