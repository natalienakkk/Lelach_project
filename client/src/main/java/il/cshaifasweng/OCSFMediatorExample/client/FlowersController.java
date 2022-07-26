package il.cshaifasweng.OCSFMediatorExample.client;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
public class FlowersController {
    @FXML private static Item item;
    @FXML public static Item getItem() { return item; }
    @FXML public static void setItem(Item item) {
        FlowersController.item = item;
    }
    @FXML private AnchorPane Pane1;
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Text flower_color;
    @FXML private Text flower_id;
    @FXML private ImageView flower_image;
    @FXML private Text flower_name;
    @FXML private Text flower_price;
    @FXML private Text flower_type;
    @FXML private Text priceee;
    @FXML private Button back_to_catalog;
    @FXML void back_to_catalog1(ActionEvent event) throws IOException { App.setRoot("catalog"); }
    @FXML void initialize() {
        assert Pane1 != null : "fx:id=\"Pane1\" was not injected: check your FXML file 'flowers.fxml'.";
        assert back_to_catalog != null : "fx:id=\"back_to_catalog\" was not injected: check your FXML file 'flowers.fxml'.";
        assert flower_color != null : "fx:id=\"flower_color\" was not injected: check your FXML file 'flowers.fxml'.";
        assert flower_id != null : "fx:id=\"flower_id\" was not injected: check your FXML file 'flowers.fxml'.";
        assert flower_image != null : "fx:id=\"flower_image\" was not injected: check your FXML file 'flowers.fxml'.";
        assert flower_name != null : "fx:id=\"flower_name\" was not injected: check your FXML file 'flowers.fxml'.";
        assert flower_price != null : "fx:id=\"flower_price\" was not injected: check your FXML file 'flowers.fxml'.";
        assert flower_type != null : "fx:id=\"flower_type\" was not injected: check your FXML file 'flowers.fxml'.";
        assert priceee != null : "fx:id=\"priceee\" was not injected: check your FXML file 'flowers.fxml'.";
        setItem(App.getItem());
        flower_name.setText(item.getName());
        flower_color.setText("Color : "+ item.getColor());
        flower_type.setText("Type : "+ item.getType());
        flower_id.setText("ID : "+ item.getId());
        priceee.setText(item.getPrice()+"");
        Image image1 = new Image(item.getPicture());
        flower_image.setImage(image1);

    }

}
