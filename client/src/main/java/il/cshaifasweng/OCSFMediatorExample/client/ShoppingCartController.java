package il.cshaifasweng.OCSFMediatorExample.client;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import javax.swing.plaf.synth.SynthOptionPaneUI;


public class ShoppingCartController {
    private Item item;
    private Double amount;
    private ShoppingCart cart;
    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }
    private List<Item> items;
    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
    public Double getAmount() { return amount; }
    public void setAmount1(Double amount) { this.amount = amount; }
    public ShoppingCart getCart() { return cart; }
    public void setCart(ShoppingCart cart) { this.cart = cart; }
    private Registration User2;
    public Registration getUser2() { return User2; }
    public void setUser2(Registration user2) { User2 = user2; }
    public int flag5=0;
    public int flag6=0;
    public int flag7=0;
    public int flag8=0;
    private String[] Delivery = {"Yes" , "No" };
    private String[] DeliveryTo3 = {"Myself" , "Someone else" };
    int totalpricefinal;

    @FXML
    private ComboBox<String> DeliveryOP;

    @FXML
    private Text DeliveryTo1;

    @FXML
    private ComboBox<String> DeliveryTo2;

    @FXML
    private ComboBox<String> Note;

    @FXML
    private Text Note1;

    @FXML
    private TextField Note2;

    @FXML
    private DatePicker ReceiveDate;

    @FXML
    private Text Receiveraddress1;

    @FXML
    private TextField Receiveraddress2;

    @FXML
    private Text Receiveremail1;

    @FXML
    private TextField Receiveremail2;

    @FXML
    private Text Receivername1;

    @FXML
    private TextField Receivername2;

    @FXML
    private Text TotalPrice;

    @FXML
    private AnchorPane Pane2;

    @FXML
    private Button Back;

    @FXML
    private Text refund;



    @FXML
    private ResourceBundle resources;

    @FXML
    private Button order;

    @FXML
    private URL location;

    @FXML
    private Text Note3;

    @FXML
    private TableColumn<TableViewSC, String> col1;

    @FXML
    private TableColumn<TableViewSC, Double> col2;

    @FXML
    private TableColumn<TableViewSC, Double> col3;

    @FXML
    private TableColumn<TableViewSC, Double> col4;

    @FXML
    private TableColumn<TableViewSC, Button> col5;

    @FXML
    private TableView<TableViewSC> table;

    @FXML
    private Text subsc;

    @FXML
    private TextField timeh;

    @FXML
    private TextField timem;


    @FXML
    void order(ActionEvent event) throws IOException {
        Order order = new Order();

        if(cart.getItems().size()==0)
        {
            SimpleClient.getClient().sendToServer(new Message("#submitorder", order, cart,-1));
        }
        else if(flag5==0 || flag7==0 || flag8==0){
            order.setRecievedate("a");
            SimpleClient.getClient().sendToServer(new Message("#submitorder", order, cart,-1));
        }
        else if(flag6==0 ){
            order.setDeliveryOp("None");
            order.setRecievedate("b");
            SimpleClient.getClient().sendToServer(new Message("#submitorder", order, cart,-1));
        }
//        if( flag8==0){
//            order.setRecievetime("b");
//            SimpleClient.getClient().sendToServer(new Message("#submitorder", order, cart));
//        }
//        if( flag7==0 ){
//            order.setRecievetime("c");
//            SimpleClient.getClient().sendToServer(new Message("#submitorder", order, cart));
//        }


        else {
            LocalDateTime date = LocalDateTime.now();
            if (DeliveryTo2.getSelectionModel().getSelectedItem() == null) {
                order = new Order(date.toString().substring(0, 10), ReceiveDate.getValue().toString(),timeh.getText()+":"+timem.getText(), User2.getCreditCard(), DeliveryOP.getSelectionModel().getSelectedItem(), User2.getClient_ID(), User2.getUserName(),User2.getEmail(), null, User2.getFirstName(), "a", TotalPrice.getText(), "pending", Note2.getText());
            } else if (DeliveryTo2.getSelectionModel().getSelectedItem().equals("Someone else")) {
                order = new Order(date.toString().substring(0, 10), ReceiveDate.getValue().toString(),timeh.getText()+":"+timem.getText(), User2.getCreditCard(), DeliveryOP.getSelectionModel().getSelectedItem(), User2.getClient_ID(), User2.getUserName(),User2.getEmail() ,Receiveraddress2.getText(), Receivername2.getText(), Receiveremail2.getText(), TotalPrice.getText(), "pending", Note2.getText());
            } else if (DeliveryTo2.getSelectionModel().getSelectedItem().equals("Myself")) {
                order = new Order(date.toString().substring(0, 10), ReceiveDate.getValue().toString(),timeh.getText()+":"+timem.getText(), User2.getCreditCard(), DeliveryOP.getSelectionModel().getSelectedItem(), User2.getClient_ID(), User2.getUserName(), User2.getEmail(),"Haifa", User2.getFirstName(), " ", TotalPrice.getText(), "pending"," ");
            }
            SimpleClient.getClient().sendToServer(new Message("#submitorder", order, cart,totalpricefinal));
        }
    }

    @FXML
    void Back(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#opencatalog", "Client", items));
    }
    int flag = 0;
    @FXML
    void DeliveryOP(ActionEvent event) {
        flag6++;
        if (DeliveryOP.getSelectionModel().getSelectedItem().equals("Yes")) {
            DeliveryTo2.setVisible(true);
            DeliveryTo1.setVisible(true);
            totalpricefinal+=20;
            TotalPrice.setText((totalpricefinal)+" ");
            flag = 1;
            Note.setValue(" ");
            DeliveryTo2.setValue(" ");
            if (DeliveryTo2.getSelectionModel().getSelectedItem().equals("Someone else"))
            {
                Receiveraddress1.setVisible(true);
                Receiveraddress2.setVisible(true);
                Receiveremail1.setVisible(true);
                Receiveremail2.setVisible(true);
                Receivername1.setVisible(true);
                Receivername2.setVisible(true);
                Note.setVisible(true);
                Note3.setVisible(true);
                if (Note.getSelectionModel().getSelectedItem().equals("Yes"))
                {
                    Note1.setVisible(true);
                    Note2.setVisible(true);
                }
            }

        }
        else if(DeliveryOP.getSelectionModel().getSelectedItem().equals("No"))
        {
            DeliveryTo2.setVisible(false);
            DeliveryTo1.setVisible(false);
            Receiveraddress1.setVisible(false);
            Receiveraddress2.setVisible(false);
            Receiveremail1.setVisible(false);
            Receiveremail2.setVisible(false);
            Receivername1.setVisible(false);
            Receivername2.setVisible(false);
            Note.setVisible(false);
            Note1.setVisible(false);
            Note2.setVisible(false);
            Note3.setVisible(false);

            if (flag == 1)
            {
                totalpricefinal-=20;
                TotalPrice.setText((totalpricefinal+" "));
            }
        }
    }

    @FXML
    void DeliveryTo2(ActionEvent event) {
        if (DeliveryTo2.getSelectionModel().getSelectedItem().equals("Someone else"))
        {
            Receiveraddress1.setVisible(true);
            Receiveraddress2.setVisible(true);
            Receiveremail1.setVisible(true);
            Receiveremail2.setVisible(true);
            Receivername1.setVisible(true);
            Receivername2.setVisible(true);
            Note.setVisible(true);
            Note3.setVisible(true);
            if (Note.getSelectionModel().getSelectedItem().equals("Yes"))
            {
                Note1.setVisible(true);
                Note2.setVisible(true);
            }
        }
        else
        {
            Receiveraddress1.setVisible(false);
            Receiveraddress2.setVisible(false);
            Receiveremail1.setVisible(false);
            Receiveremail2.setVisible(false);
            Receivername1.setVisible(false);
            Receivername2.setVisible(false);
            Note.setVisible(false);
            Note3.setVisible(false);
            Note1.setVisible(false);
            Note2.setVisible(false);
        }
    }

    @FXML
    void Note(ActionEvent event) {
        if (Note.getSelectionModel().getSelectedItem().equals("Yes"))
        {
            Note1.setVisible(true);
            Note2.setVisible(true);
        }
        else
        {
            Note1.setVisible(false);
            Note2.setVisible(false);
        }
    }

    @FXML void ReceiveDate(ActionEvent event) {
        flag5++;
        LocalDate Expiry_Date = ReceiveDate.getValue();
    }
    @FXML void recivetime1(KeyEvent event) { flag7++; }
    @FXML void recivetime2(KeyEvent event) { flag8++; }


    List<Button> buttonList = new ArrayList<Button>();


    public ObservableList<TableViewSC> showitems() {
        ObservableList<TableViewSC> items = FXCollections.observableArrayList();
        for(int i=0; i < cart.getItems().size() ; i++ )
        {

            Button b = new Button();
            buttonList.add(b);
            int finalI = i;
            buttonList.get(i).setOnAction(event ->
            {
                try {
                    SimpleClient.getClient().sendToServer(new Message("#deletefromcart", cart , finalI));
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                cart.getItems().remove(finalI);
//                cart.getAmount().remove(finalI);
//                cart.setItemsNum(cart.getItemsNum()-1);
                cart.RemovefromCart(finalI);
                table.setItems(showitems());
                table.getColumns().addAll();
                TotalPrice.setText(cart.gettotalPrice(cart)+ " ");

            });
            buttonList.get(i).setText("Delete");

                items.add(new TableViewSC(cart.getItems().get(i).getName(), cart.getItems().get(i).getPrice(), cart.getAmount().get(i), cart.getItems().get(i).getPrice() * cart.getAmount().get(i), buttonList.get(i)));

        }

        return items;
    }

    @FXML
    void initialize() {
        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'cart.fxml'.";
        assert DeliveryOP != null : "fx:id=\"DeliveryOP\" was not injected: check your FXML file 'cart.fxml'.";
        assert DeliveryTo1 != null : "fx:id=\"DeliveryTo1\" was not injected: check your FXML file 'cart.fxml'.";
        assert DeliveryTo2 != null : "fx:id=\"DeliveryTo2\" was not injected: check your FXML file 'cart.fxml'.";
        assert Note != null : "fx:id=\"Note\" was not injected: check your FXML file 'cart.fxml'.";
        assert Note1 != null : "fx:id=\"Note1\" was not injected: check your FXML file 'cart.fxml'.";
        assert Note2 != null : "fx:id=\"Note2\" was not injected: check your FXML file 'cart.fxml'.";
        assert Pane2 != null : "fx:id=\"Pane2\" was not injected: check your FXML file 'cart.fxml'.";
        assert ReceiveDate != null : "fx:id=\"ReceiveDate\" was not injected: check your FXML file 'cart.fxml'.";
        assert Receiveraddress1 != null : "fx:id=\"Receiveraddress1\" was not injected: check your FXML file 'cart.fxml'.";
        assert Receiveraddress2 != null : "fx:id=\"Receiveraddress2\" was not injected: check your FXML file 'cart.fxml'.";
        assert Receiveremail1 != null : "fx:id=\"Receiveremail1\" was not injected: check your FXML file 'cart.fxml'.";
        assert Receiveremail2 != null : "fx:id=\"Receiveremail2\" was not injected: check your FXML file 'cart.fxml'.";
        assert Receivername1 != null : "fx:id=\"Receivername1\" was not injected: check your FXML file 'cart.fxml'.";
        assert Receivername2 != null : "fx:id=\"Receivername2\" was not injected: check your FXML file 'cart.fxml'.";
        assert TotalPrice != null : "fx:id=\"TotalPrice\" was not injected: check your FXML file 'cart.fxml'.";
        assert col1 != null : "fx:id=\"col1\" was not injected: check your FXML file 'cart.fxml'.";
        assert col2 != null : "fx:id=\"col2\" was not injected: check your FXML file 'cart.fxml'.";
        assert col3 != null : "fx:id=\"col3\" was not injected: check your FXML file 'cart.fxml'.";
        assert col4 != null : "fx:id=\"col4\" was not injected: check your FXML file 'cart.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'cart.fxml'.";
        Receiveraddress1.setVisible(false);
        Receiveraddress2.setVisible(false);
        Receiveremail1.setVisible(false);
        Receiveremail2.setVisible(false);
        Receivername1.setVisible(false);
        Receivername2.setVisible(false);
        DeliveryTo2.setVisible(false);
        DeliveryTo1.setVisible(false);
        refund.setVisible(false);
        Note1.setVisible(false);
        Note2.setVisible(false);
        Note.setVisible(false);
        Note3.setVisible(false);
        setCart(App.getCart5());
        setUser2(App.getUser1());
        setItems(App.getItemList());
        subsc.setVisible(false);

        int a = (int) User2.getRefund();
        int b = cart.gettotalPrice(cart);
        int c = a-b;
        if(a==0)
            totalpricefinal = cart.gettotalPrice(cart);
        else {
            if(a>=cart.gettotalPrice(cart))
            {
                totalpricefinal=0;
            }
            else totalpricefinal = cart.gettotalPrice(cart)-a;
        }
        if(a!=0)
        {
            refund.setVisible(true);
        }
        if ( User2.getAccountType().equals("One year subscription") && cart.gettotalPrice(cart)>50 )
        {
            totalpricefinal = (int) (totalpricefinal*0.9);
            subsc.setVisible(true);
        }

        TotalPrice.setText(totalpricefinal+"");
        DeliveryOP.getItems().addAll(Delivery);
        Note.getItems().addAll(Delivery);
        DeliveryTo2.getItems().addAll(DeliveryTo3);
//        for(int i=0 ; i<cart.getItems().size() ; i++)
//        {
//            System.out.println(cart.getItems().get(i).getName());
//        }
        col1.setCellValueFactory(new PropertyValueFactory<TableViewSC, String >("name"));
        col2.setCellValueFactory(new PropertyValueFactory<TableViewSC,Double>("Price"));
        col3.setCellValueFactory(new PropertyValueFactory<TableViewSC,Double>("Amount"));
        col4.setCellValueFactory(new PropertyValueFactory<TableViewSC,Double>("Total_Price"));
        col5.setCellValueFactory(new PropertyValueFactory<TableViewSC,Button>("button"));
        table.setItems(showitems());
        table.getColumns().addAll();

        ReceiveDate.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0 );
            }
        });

    }


}
