package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class HomepageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Guest_btn;

    @FXML
    private Button Manager_btn;

    @FXML
    private AnchorPane Pane1;

    @FXML
    private Text welcome;

    @FXML
    void Guest_btn(ActionEvent event) throws IOException {
        try {
            SimpleClient.getClient().sendToServer("#opencatalog");
        }
        catch(IOException e)
        {

            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        AnchorPane pane = FXMLLoader.load(getClass().getResource("catalog.fxml"));
        Pane1.getChildren().setAll(pane);
    }

    @FXML
    void Manager_btn(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer("#openspray");
        System.out.format("SADAGEagsdfg44777777777777777777777777777444444\n");
        AnchorPane pane = FXMLLoader.load(getClass().getResource("flowers.fxml"));
        Pane1.getChildren().setAll(pane);
    }

    @FXML
    void initialize() {
        assert Guest_btn != null : "fx:id=\"Guest_btn\" was not injected: check your FXML file 'homepage.fxml'.";
        assert Manager_btn != null : "fx:id=\"Manager_btn\" was not injected: check your FXML file 'homepage.fxml'.";
        assert Pane1 != null : "fx:id=\"Pane1\" was not injected: check your FXML file 'homepage.fxml'.";
        assert welcome != null : "fx:id=\"welcome\" was not injected: check your FXML file 'homepage.fxml'.";

    }

}
