package il.cshaifasweng.OCSFMediatorExample.client;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
//import il.cshaifasweng.OCSFMediatorExample.entities.ShoppingCart;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Registration;
import il.cshaifasweng.OCSFMediatorExample.entities.ShoppingCart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.greenrobot.eventbus.Logger;

public class OrderCatalogController {
    @FXML private static Item item;
    @FXML public static Item getItem() {
        return item;
    }
    @FXML public static void setItem(Item item) {
        OrderCatalogController.item = item;
    }
    @FXML public static ShoppingCart cart;
    @FXML public static ShoppingCart getCart() {
        return cart;
    }
    @FXML public static void setCart(ShoppingCart cart) {
        OrderCatalogController.cart = cart;
    }
    public static Registration User;
    public static Registration getUser() { return User; }
    public static void setUser(Registration user) { User = user; }
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private AnchorPane Pane1;
    @FXML private Button addtocart;
    @FXML private Spinner<Integer> amount_picker;
    @FXML private Button back_to_catalog;
    @FXML private Text flower_color;
    @FXML private Text flower_id;
    @FXML private Text flower_name;
    @FXML private Text flower_price;
    @FXML private Text flower_type;
    @FXML private Text priceee;
    @FXML private ImageView image;
    @FXML void addtocart(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#addtocart" , item , Double.valueOf(amount_picker.getValue()) ,User.getUserName()));
        App.setRoot("catalog");

    }
    @FXML void back_to_catalog1(ActionEvent event) throws IOException {
        App.setRoot("catalog");
    }
    @FXML void initialize() {
        assert Pane1 != null : "fx:id=\"Pane1\" was not injected: check your FXML file 'client.fxml'.";
        assert addtocart != null : "fx:id=\"addtocart\" was not injected: check your FXML file 'client.fxml'.";
        assert amount_picker != null : "fx:id=\"amount_picker\" was not injected: check your FXML file 'client.fxml'.";
        assert back_to_catalog != null : "fx:id=\"back_to_catalog\" was not injected: check your FXML file 'client.fxml'.";
        assert flower_color != null : "fx:id=\"flower_color\" was not injected: check your FXML file 'client.fxml'.";
        assert flower_id != null : "fx:id=\"flower_id\" was not injected: check your FXML file 'client.fxml'.";
        assert flower_name != null : "fx:id=\"flower_name\" was not injected: check your FXML file 'client.fxml'.";
        assert flower_price != null : "fx:id=\"flower_price\" was not injected: check your FXML file 'client.fxml'.";
        assert flower_type != null : "fx:id=\"flower_type\" was not injected: check your FXML file 'client.fxml'.";
        assert priceee != null : "fx:id=\"priceee\" was not injected: check your FXML file 'client.fxml'.";
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'client.fxml'.";
        setItem(App.getItem());
        setUser(App.getUser1());

        flower_name.setText(item.getName());
        flower_color.setText("Color : "+ item.getColor());
        flower_type.setText("Type : "+ item.getType());
        flower_id.setText("ID : "+ item.getId());
        priceee.setText(item.getPrice()+"");
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100);
        valueFactory.setValue(1);
        amount_picker.setValueFactory(valueFactory);
        Image image1 = new Image(item.getPicture());
        image.setImage(image1);

    }

}
