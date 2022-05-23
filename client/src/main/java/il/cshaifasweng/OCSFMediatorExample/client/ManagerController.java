package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.File;
import java.io.IOException;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
public class ManagerController  {
    @FXML private static Item item;
    @FXML public static Item getItem() {
        return item;
    }
    @FXML public static void setItem(Item item) {
        ManagerController.item = item;
    }
    @FXML private AnchorPane Pane1;
    @FXML private Button back_button;
    @FXML private TextField edit_price;
    @FXML private Text flower_color;
    @FXML private Text flower_id;
    @FXML private Text flower_name;
    @FXML private Text flower_price;
    @FXML private Text flower_type;
    @FXML private Text priceee;
    @FXML private ImageView image;
    @FXML void edit_price2(KeyEvent event) throws IOException {
        if (event.getCode() ==  KeyCode.ENTER ) {
            String price_change = edit_price.getText();
            double price_change1 = Double.parseDouble(price_change);
            SimpleClient.getClient().sendToServer(new Message("#update_price",price_change1 ,item.getId()));
            AnchorPane pane = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            Pane1.getChildren().setAll(pane);
        }
    }
    @FXML void back_button1(ActionEvent event) throws IOException { App.setRoot("catalog"); }
    @FXML void initialize() {
        assert Pane1 != null : "fx:id=\"Pane1\" was not injected: check your FXML file 'manager.fxml'.";
        assert back_button != null : "fx:id=\"back_button\" was not injected: check your FXML file 'manager.fxml'.";
        assert edit_price != null : "fx:id=\"edit_price\" was not injected: check your FXML file 'manager.fxml'.";
        assert flower_color != null : "fx:id=\"flower_color\" was not injected: check your FXML file 'manager.fxml'.";
        assert flower_id != null : "fx:id=\"flower_id\" was not injected: check your FXML file 'manager.fxml'.";
        assert flower_name != null : "fx:id=\"flower_name\" was not injected: check your FXML file 'manager.fxml'.";
        assert flower_price != null : "fx:id=\"flower_price\" was not injected: check your FXML file 'manager.fxml'.";
        assert flower_type != null : "fx:id=\"flower_type\" was not injected: check your FXML file 'manager.fxml'.";
        assert priceee != null : "fx:id=\"priceee\" was not injected: check your FXML file 'manager.fxml'.";
        assert image != null : "fx:id=\"image\" was not injected: check your FXML file 'manager.fxml'.";
        setItem(App.getItem());
        flower_name.setText(item.getName());
        flower_color.setText("Color : "+ item.getColor());
        flower_type.setText("Type : "+ item.getType());
        flower_id.setText("ID : "+ item.getId());
        priceee.setText(item.getPrice()+"");
        File file = new File("C:\\Users\\Saher\\IdeaProjects\\saher-eissa\\client\\src\\main\\resources\\images\\"+item.getName()+".jpg");
        Image image1 = new Image(file.toURI().toString());
        image.setImage(image1);
    }
}
