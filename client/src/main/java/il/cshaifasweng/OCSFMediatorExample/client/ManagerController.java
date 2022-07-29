package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ManagerController {
    @FXML private static Item item;
    @FXML public static Item getItem() { return item; }
    @FXML public static void setItem(Item item) { ManagerController.item = item; }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane Pane1;

    @FXML
    private Button back_button;

    @FXML
    private Button color_button;

    @FXML
    private TextField color_update;

    @FXML
    private Button delete_button;

    @FXML
    private Text flower_color;

    @FXML
    private Text flower_id;

    @FXML
    private Text flower_name;

    @FXML
    private Text flower_price;

    @FXML
    private Text flower_type;

    @FXML
    private ImageView image;

    @FXML
    private Button image_button;

    @FXML
    private Button name_button;

    @FXML
    private TextField name_update;

    @FXML
    private Button price_button;

    @FXML
    private TextField price_update;

    @FXML
    private Button type_button;

    @FXML
    private TextField type_update;

    @FXML
    private TextField image_edit;

    @FXML
    void back_butt(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#show all items"));
        update();
    }

    @FXML
    void color_butt(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer(new Message("#update flower",item.getId(),"color_update",color_update.getText()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        color_update.clear();
        update();
    }

    @FXML
    void delete_butt(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer(new Message("#delete item",item.getId()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        update();

    }

    @FXML
    void image_butt(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer(new Message("#update flower",item.getId(),"image_update",image_edit.getText()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        image_edit.clear();
        update();
    }

    @FXML
    void name_butt(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer(new Message("#update flower",item.getId(),"name_update",name_update.getText()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        name_update.clear();
        update();
    }

    @FXML
    void price_butt(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer(new Message("#update flower",item.getId(),"price_update",price_update.getText()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        price_update.clear();
        update();

    }

    @FXML
    void type_butt(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer(new Message("#update flower",item.getId(),"type_update",type_update.getText()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        type_update.clear();
        update();

    }

    public void update(){
        assert Pane1 != null : "fx:id=\"Pane1\" was not injected: check your FXML file 'manager.fxml'.";
        assert back_button != null : "fx:id=\"back_button\" was not injected: check your FXML file 'manager.fxml'.";
        assert color_button != null : "fx:id=\"color_button\" was not injected: check your FXML file 'manager.fxml'.";
        assert color_update != null : "fx:id=\"color_update\" was not injected: check your FXML file 'manager.fxml'.";
        assert delete_button != null : "fx:id=\"delete_button\" was not injected: check your FXML file 'manager.fxml'.";
        assert flower_color != null : "fx:id=\"flower_color\" was not injected: check your FXML file 'manager.fxml'.";
        assert flower_id != null : "fx:id=\"flower_id\" was not injected: check your FXML file 'manager.fxml'.";
        assert flower_name != null : "fx:id=\"flower_name\" was not injected: check your FXML file 'manager.fxml'.";
        assert flower_price != null : "fx:id=\"flower_price\" was not injected: check your FXML file 'manager.fxml'.";
        assert flower_type != null : "fx:id=\"flower_type\" was not injected: check your FXML file 'manager.fxml'.";
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'manager.fxml'.";
        assert image_button != null : "fx:id=\"image_button\" was not injected: check your FXML file 'manager.fxml'.";
        assert name_button != null : "fx:id=\"name_button\" was not injected: check your FXML file 'manager.fxml'.";
        assert name_update != null : "fx:id=\"name_update\" was not injected: check your FXML file 'manager.fxml'.";
        assert price_button != null : "fx:id=\"price_button\" was not injected: check your FXML file 'manager.fxml'.";
        assert price_update != null : "fx:id=\"price_update\" was not injected: check your FXML file 'manager.fxml'.";
        assert type_button != null : "fx:id=\"type_button\" was not injected: check your FXML file 'manager.fxml'.";
        assert type_update != null : "fx:id=\"type_update\" was not injected: check your FXML file 'manager.fxml'.";

        setItem(App.getItem());
        flower_name.setText("Name : "+item.getName());
        flower_color.setText("Color : "+ item.getColor());
        flower_type.setText("Type : "+ item.getType());
        flower_id.setText("ID : "+ item.getId());
        flower_price.setText("Price : " +item.getPrice());
        Image image1 = new Image(item.getPicture());
        image.setImage(image1);
    }

    @FXML
    void initialize() {
        update();
    }

}
