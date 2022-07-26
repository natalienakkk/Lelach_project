package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Registration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class NetworkMarketingWorkerController {

    @FXML private static String username;

    @FXML public static String getUsername() { return username; }

    @FXML public static void setUsername(String username) { NetworkMarketingWorkerController.username = username; }


    @FXML
    private Text NW_name;

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
    private AnchorPane pane1;

    @FXML
    private Button back_button;

    @FXML
    private Button delete_discount;

    @FXML
    private TextField flower_image;

    @FXML
    private double discount;

    int flag=0;

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
        if((Double.parseDouble(discount_percent.getText()))<100 && (Double.parseDouble(discount_percent.getText()))>0) {
            delete_discount.setDisable(false);
        }
        discount=Double.parseDouble(discount_percent.getText());
        discount_percent.clear();
        SimpleClient.getClient().sendToServer(new Message("#apply discount",discount));
    }

    @FXML
    void additem_butt(ActionEvent event) throws IOException {
        Item new_item=new Item(flower_name.getText(),flower_type.getText(),flower_color.getText(),flower_image.getText(),Double.parseDouble(flower_price.getText()));
        flower_name.clear();
        flower_type.clear();
        flower_color.clear();
        flower_price.clear();
        flower_image.clear();
        SimpleClient.getClient().sendToServer(new Message("#add new item",new_item));
    }



    @FXML
    void showallitems_butt(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#show all items"));
        update();
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

    public void update(){
        assert additem_button != null : "fx:id=\"additem_button\" was not injected: check your FXML file 'NetworkMarketingWorker.fxml'.";
        assert apply_button != null : "fx:id=\"apply_button\" was not injected: check your FXML file 'NetworkMarketingWorker.fxml'.";
        assert discount_percent != null : "fx:id=\"discount_percent\" was not injected: check your FXML file 'NetworkMarketingWorker.fxml'.";
        assert flower_color != null : "fx:id=\"flower_color\" was not injected: check your FXML file 'NetworkMarketingWorker.fxml'.";
        assert flower_name != null : "fx:id=\"flower_name\" was not injected: check your FXML file 'NetworkMarketingWorker.fxml'.";
        assert flower_price != null : "fx:id=\"flower_price\" was not injected: check your FXML file 'NetworkMarketingWorker.fxml'.";
        assert flower_type != null : "fx:id=\"flower_type\" was not injected: check your FXML file 'NetworkMarketingWorker.fxml'.";
        assert showallitems_button != null : "fx:id=\"showallitems_button\" was not injected: check your FXML file 'NetworkMarketingWorker.fxml'.";
        assert NW_name != null : "fx:id=\"NW_name\" was not injected: check your FXML file 'NetworkMarketingWorker.fxml'.";

        setUsername1(App.getUser1());
        NW_name.setText("Welcome " +getUsername1().getUserName());
        delete_discount.setDisable(true);
    }

    @FXML
    void initialize() {
        update();

    }

}
