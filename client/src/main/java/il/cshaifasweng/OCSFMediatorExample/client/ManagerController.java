package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Catalog;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.input.KeyEvent;

public class ManagerController {


    private static Item item;

    public static Item getItem() {
        return item;
    }

    public static void setItem(Item item) {
        ManagerController.item = item;
    }
    @FXML
    private AnchorPane Pane1;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField edit_price;

    @FXML
    private Text flower_color;

    @FXML
    private Text flower_id;

    @FXML
    private ImageView flower_image;

    @FXML
    private Text flower_name;

    @FXML
    private Text flower_price;

    @FXML
    private Text flower_type;

    @FXML
    private Text priceee;

    @FXML
    void edit_price2(KeyEvent event) throws IOException {
        if (event.getCode() ==  KeyCode.ENTER ) {
            String price_change = edit_price.getText();
            double price_change1 = Double.parseDouble(price_change);
            SimpleClient.getClient().sendToServer(new Message("#update_price",price_change1 ,item.getId()));
            AnchorPane pane = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            Pane1.getChildren().setAll(pane);
        }
    }
    @FXML
    void edit_price1(ActionEvent event) {

    }

    @FXML
    void initialize() { System.out.format("15125125");
        assert Pane1 != null : "fx:id=\"Pane1\" was not injected: check your FXML file 'manager.fxml'.";
        assert edit_price != null : "fx:id=\"edit_price\" was not injected: check your FXML file 'manager.fxml'.";
        assert flower_color != null : "fx:id=\"flower_color\" was not injected: check your FXML file 'manager.fxml'.";
        assert flower_id != null : "fx:id=\"flower_id\" was not injected: check your FXML file 'manager.fxml'.";
        assert flower_image != null : "fx:id=\"flower_image\" was not injected: check your FXML file 'manager.fxml'.";
        assert flower_name != null : "fx:id=\"flower_name\" was not injected: check your FXML file 'manager.fxml'.";
        assert flower_price != null : "fx:id=\"flower_price\" was not injected: check your FXML file 'manager.fxml'.";
        assert flower_type != null : "fx:id=\"flower_type\" was not injected: check your FXML file 'manager.fxml'.";
        assert priceee != null : "fx:id=\"priceee\" was not injected: check your FXML file 'manager.fxml'.";
        flower_name.setText(item.getName());
        flower_color.setText("Color : "+ item.getColor());
        flower_type.setText("Type : "+ item.getType());
        flower_id.setText("ID : "+ item.getId());
        priceee.setText(item.getPrice()+"");

    }

}
