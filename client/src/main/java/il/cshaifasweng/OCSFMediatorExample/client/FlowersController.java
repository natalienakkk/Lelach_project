package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class FlowersController {
    private static Item item;

    public static Item getItem() {
        return item;
    }

    public static void setItem(Item item) {
        FlowersController.item = item;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    void initialize() {
        assert flower_color != null : "fx:id=\"flower_color\" was not injected: check your FXML file 'flowers.fxml'.";
        assert flower_id != null : "fx:id=\"flower_id\" was not injected: check your FXML file 'flowers.fxml'.";
        assert flower_image != null : "fx:id=\"flower_image\" was not injected: check your FXML file 'flowers.fxml'.";
        assert flower_name != null : "fx:id=\"flower_name\" was not injected: check your FXML file 'flowers.fxml'.";
        assert flower_price != null : "fx:id=\"flower_price\" was not injected: check your FXML file 'flowers.fxml'.";
        assert flower_type != null : "fx:id=\"flower_type\" was not injected: check your FXML file 'flowers.fxml'.";
        flower_name.setText(item.getName());
        flower_color.setText("Color : "+ item.getColor());

    }

}
