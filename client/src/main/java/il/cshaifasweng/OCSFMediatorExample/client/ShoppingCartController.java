package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.ShoppingCart;
import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;


public class ShoppingCartController {
    private Item item;
    private Double amount;
    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }
    public Double getAmount() { return amount; }
    public void setAmount1(Double amount) { this.amount = amount; }

    @FXML
    private AnchorPane Pane2;

    @FXML
    private Button Back;

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

    @FXML
    void Back(ActionEvent event) throws IOException {
        App.setRoot("catalog");
    }

    @FXML
    void initialize() {
        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'cart.fxml'.";
        assert col1 != null : "fx:id=\"col1\" was not injected: check your FXML file 'cart.fxml'.";
        assert col2 != null : "fx:id=\"col2\" was not injected: check your FXML file 'cart.fxml'.";
        assert col3 != null : "fx:id=\"col3\" was not injected: check your FXML file 'cart.fxml'.";
        assert col4 != null : "fx:id=\"col4\" was not injected: check your FXML file 'cart.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'cart.fxml'.";

        col1.setCellValueFactory(new PropertyValueFactory<Item,String>("name"));
        col2.setCellValueFactory(new PropertyValueFactory<Item,Double>("price"));
        col3.setCellValueFactory(new PropertyValueFactory<Integer,Integer>("amount"));
//        col4.setCellValueFactory(new PropertyValueFactory<Item,Double>("Total Price"));
        table.setItems(showitems());
        table.getColumns().addAll();
    }
    public ObservableList<Item> showitems() {
        ObservableList<Item> items = FXCollections.observableArrayList();
        items.add(item);
        return items;
    }

}
