package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShoppingCartController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Item, String> col1;

    @FXML
    private TableColumn<Item, Double> col2;

    @FXML
    private TableColumn<Integer, Integer> col3;

    @FXML
    private TableColumn<Item, Double> col4;

    @FXML
    private TableView<Item> table;

    ObservableList<Item> list = FXCollections.observableArrayList(

    );
    @FXML
    void initialize() {
        assert col1 != null : "fx:id=\"col1\" was not injected: check your FXML file 'cart.fxml'.";
        assert col2 != null : "fx:id=\"col2\" was not injected: check your FXML file 'cart.fxml'.";
        assert col3 != null : "fx:id=\"col3\" was not injected: check your FXML file 'cart.fxml'.";
        assert col4 != null : "fx:id=\"col4\" was not injected: check your FXML file 'cart.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'cart.fxml'.";
    }

}
