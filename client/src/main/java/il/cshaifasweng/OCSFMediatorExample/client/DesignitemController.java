package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Registration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class DesignitemController {
    public static Registration User;
    public static Registration getUser() { return User; }
    public static void setUser(Registration user) { User = user; }
    double totalprice=0;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Spinner<Integer> bluepick;

    @FXML
    private TextField price;

    @FXML
    private Text price1;

    @FXML
    private Spinner<Integer> rainpick;

    @FXML
    private Spinner<Integer> redpick;

    @FXML
    private Spinner<Integer> whitepick;

    @FXML
    private Spinner<Integer> yellpick;

    @FXML
    void Back(ActionEvent event) throws IOException {
        App.setRoot("catalog");
    }

    @FXML
    void pick1(MouseEvent event) {
        totalprice= redpick.getValue()*10+bluepick.getValue()*11+yellpick.getValue()*11+whitepick.getValue()*10+rainpick.getValue()*20;
        price.setText(totalprice+"");
    }

    @FXML
    void pick2(MouseEvent event) {
        totalprice= redpick.getValue()*10+bluepick.getValue()*11+yellpick.getValue()*11+whitepick.getValue()*10+rainpick.getValue()*20;
        price.setText(totalprice+"");
    }

    @FXML
    void pick3(MouseEvent event) {
        totalprice= redpick.getValue()*10+bluepick.getValue()*11+yellpick.getValue()*11+whitepick.getValue()*10+rainpick.getValue()*20;
        price.setText(totalprice+"");
    }

    @FXML
    void pick4(MouseEvent event) {
        totalprice= redpick.getValue()*10+bluepick.getValue()*11+yellpick.getValue()*11+whitepick.getValue()*10+rainpick.getValue()*20;
        price.setText(totalprice+"");
    }

    @FXML
    void pick5(MouseEvent event) {
        totalprice= redpick.getValue()*10+bluepick.getValue()*11+yellpick.getValue()*11+whitepick.getValue()*10+rainpick.getValue()*20;
        price.setText(totalprice+"");
    }

    @FXML
    void add(ActionEvent event) throws IOException {
        Item item = new Item("Designed item","Rose","Rainbow","https://www.flora2000.com/lux300/18592.jpg",totalprice);
        SimpleClient.getClient().sendToServer(new Message("#addtocart" , item , 1.0 ,User.getUserName()));
        App.setRoot("catalog");

    }

    @FXML
    void initialize() {
        assert bluepick != null : "fx:id=\"bluepick\" was not injected: check your FXML file 'design.fxml'.";
        assert price != null : "fx:id=\"price\" was not injected: check your FXML file 'design.fxml'.";
        assert price1 != null : "fx:id=\"price1\" was not injected: check your FXML file 'design.fxml'.";
        assert rainpick != null : "fx:id=\"rainpick\" was not injected: check your FXML file 'design.fxml'.";
        assert redpick != null : "fx:id=\"redpick\" was not injected: check your FXML file 'design.fxml'.";
        assert whitepick != null : "fx:id=\"whitepick\" was not injected: check your FXML file 'design.fxml'.";
        assert yellpick != null : "fx:id=\"yellpick\" was not injected: check your FXML file 'design.fxml'.";
        setUser(App.getUser1());
        SpinnerValueFactory<Integer> valueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,5);
        valueFactory1.setValue(0);
        SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,5);
        valueFactory2.setValue(0);
        SpinnerValueFactory<Integer> valueFactory3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,5);
        valueFactory3.setValue(0);
        SpinnerValueFactory<Integer> valueFactory4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,5);
        valueFactory4.setValue(0);
        SpinnerValueFactory<Integer> valueFactory5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,5);
        valueFactory5.setValue(0);
        bluepick.setValueFactory(valueFactory1);
        redpick.setValueFactory(valueFactory2);
        yellpick.setValueFactory(valueFactory3);
        rainpick.setValueFactory(valueFactory4);
        whitepick.setValueFactory(valueFactory5);

    }

}
