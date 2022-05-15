package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class OrderCatalogController {

    private static Item item;

    public static Item getItem() {
        return item;
    }

    public static void setItem(Item item) {
        OrderCatalogController.item = item;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane Pane1;

    @FXML
    private Spinner<Integer> amount_picker;

    @FXML
    private Button back_to_catalog;

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
    private Text priceee;

    @FXML
    void back_to_catalog1(ActionEvent event) throws IOException {
        App.setRoot("catalog");

    }

    @FXML
    void initialize() {
        assert Pane1 != null : "fx:id=\"Pane1\" was not injected: check your FXML file 'client.fxml'.";
        assert amount_picker != null : "fx:id=\"amount_picker\" was not injected: check your FXML file 'client.fxml'.";
        assert back_to_catalog != null : "fx:id=\"back_to_catalog\" was not injected: check your FXML file 'client.fxml'.";
        assert flower_color != null : "fx:id=\"flower_color\" was not injected: check your FXML file 'client.fxml'.";
        assert flower_id != null : "fx:id=\"flower_id\" was not injected: check your FXML file 'client.fxml'.";
        assert flower_name != null : "fx:id=\"flower_name\" was not injected: check your FXML file 'client.fxml'.";
        assert flower_price != null : "fx:id=\"flower_price\" was not injected: check your FXML file 'client.fxml'.";
        assert flower_type != null : "fx:id=\"flower_type\" was not injected: check your FXML file 'client.fxml'.";
        assert priceee != null : "fx:id=\"priceee\" was not injected: check your FXML file 'client.fxml'.";
        flower_name.setText(item.getName());
        flower_color.setText("Color : "+ item.getColor());
        flower_type.setText("Type : "+ item.getType());
        flower_id.setText("ID : "+ item.getId());
        priceee.setText(item.getPrice()+"");
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10);
        valueFactory.setValue(1);
        amount_picker.setValueFactory(valueFactory);

    }

}
