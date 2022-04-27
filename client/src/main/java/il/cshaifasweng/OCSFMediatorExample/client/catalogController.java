package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class catalogController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text Header;

    @FXML
    private ImageView image_1;

    @FXML
    private ImageView image_2;

    @FXML
    private ImageView image_3;

    @FXML
    private ImageView image_4;

    @FXML
    private ImageView image_5;

    @FXML
    void initialize() {
        assert Header != null : "fx:id=\"Header\" was not injected: check your FXML file 'catalog.fxml'.";
        assert image_1 != null : "fx:id=\"image_1\" was not injected: check your FXML file 'catalog.fxml'.";
        assert image_2 != null : "fx:id=\"image_2\" was not injected: check your FXML file 'catalog.fxml'.";
        assert image_3 != null : "fx:id=\"image_3\" was not injected: check your FXML file 'catalog.fxml'.";
        assert image_4 != null : "fx:id=\"image_4\" was not injected: check your FXML file 'catalog.fxml'.";
        assert image_5 != null : "fx:id=\"image_5\" was not injected: check your FXML file 'catalog.fxml'.";

    }

}
