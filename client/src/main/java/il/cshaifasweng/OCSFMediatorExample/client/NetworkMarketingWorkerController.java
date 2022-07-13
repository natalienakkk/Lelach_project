package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class NetworkMarketingWorkerController {

    @FXML private static String username;

    @FXML public static String getUsername() { return username; }

    @FXML public static void setUsername(String username) { NetworkMarketingWorkerController.username = username; }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button additem_button;

    @FXML
    private Button apply_button;

    @FXML
    private TextField discount_percent;

    @FXML
    private TextField flower_color;

    @FXML
    private TextField flower_name;

    @FXML
    private TextField flower_price;

    @FXML
    private TextField flower_type;

    @FXML
    private Button showallitems_button;

    @FXML
    private Text worker_name;

    @FXML
    private AnchorPane pane1;

    @FXML
    private Button back_button;

    @FXML
    private Button delete_discount;

    @FXML
    private TextField flower_image;

    @FXML
    private double discount;

    /******remember to change that**/
    String f_picture="fh";


    @FXML
    void delete_discount(ActionEvent event) {
        delete_discount.setDisable(true);
        try {
            SimpleClient.getClient().sendToServer(new Message("#delete discount",discount));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void back_butt(ActionEvent event) {
        try {
            App.setRoot("homepage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void apply_butt(ActionEvent event) throws IOException {
        delete_discount.setDisable(false);
        discount=Double.parseDouble(discount_percent.getText());
        discount_percent.clear();
        SimpleClient.getClient().sendToServer(new Message("#apply discount",discount));
    }

    @FXML
    void additem_butt(ActionEvent event) throws IOException {
        Item new_item=new Item(flower_name.getText(),flower_type.getText(),flower_color.getText(),f_picture/*flower_image.getText()*/,Double.parseDouble(flower_price.getText()));
        flower_name.clear();
        flower_type.clear();
        flower_color.clear();
        flower_price.clear();
        SimpleClient.getClient().sendToServer(new Message("#add new item",new_item));
    }



    @FXML
    void showallitems_butt(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#show all items"));
        ScrollPane pane = FXMLLoader.load(getClass().getResource("catalog.fxml"));
        pane1.getChildren().setAll(pane);
    }

    @FXML
    void initialize() {
        assert additem_button != null : "fx:id=\"additem_button\" was not injected: check your FXML file 'NetworkMarketingWorker.fxml'.";
        assert apply_button != null : "fx:id=\"apply_button\" was not injected: check your FXML file 'NetworkMarketingWorker.fxml'.";
        assert discount_percent != null : "fx:id=\"discount_percent\" was not injected: check your FXML file 'NetworkMarketingWorker.fxml'.";
        assert flower_color != null : "fx:id=\"flower_color\" was not injected: check your FXML file 'NetworkMarketingWorker.fxml'.";
        assert flower_name != null : "fx:id=\"flower_name\" was not injected: check your FXML file 'NetworkMarketingWorker.fxml'.";
        assert flower_price != null : "fx:id=\"flower_price\" was not injected: check your FXML file 'NetworkMarketingWorker.fxml'.";
        assert flower_type != null : "fx:id=\"flower_type\" was not injected: check your FXML file 'NetworkMarketingWorker.fxml'.";
        assert showallitems_button != null : "fx:id=\"showallitems_button\" was not injected: check your FXML file 'NetworkMarketingWorker.fxml'.";
        assert worker_name != null : "fx:id=\"worker_name\" was not injected: check your FXML file 'NetworkMarketingWorker.fxml'.";
        // assert workername != null : "fx:id=\"workername\" was not injected: check your FXML file 'NetworkMarketingWorker.fxml'.";

        setUsername(App.getUsername());
        worker_name.setText(getUsername());
        delete_discount.setDisable(true);
    }

}
