package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import il.cshaifasweng.OCSFMediatorExample.entities.Registration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class OrderController {

    private static Order order;
    public static Order getOrder() { return order; }
    public static void setOrder(Order order) { OrderController.order = order; }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text cardnumber;

    @FXML
    private Text clientid;

    @FXML
    private Text clientname;

    @FXML
    private Text dateoforder;

    @FXML
    private Text delivery;

    @FXML
    private Text note;

    @FXML
    private Text note1;

    @FXML
    private Text orderid;

    @FXML
    private Text receivedate;

    @FXML
    private Text receiveraddress;

    @FXML
    private Text receiveraddress1;

    @FXML
    private Text receiveremail;

    @FXML
    private Text receiveremail1;

    @FXML
    private Text receivername;

    @FXML
    private Text receivername1;

    @FXML
    private Text status;

    @FXML
    private Text totalprice;

    @FXML
    private Button back;

    private static Registration CurrUser;

    public static Registration getCurrUser() {
        return CurrUser;
    }

    public static void setCurrUser(Registration currUser) {
        CurrUser = currUser;
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        try {
            CurrUser.getUserName();
            SimpleClient.getClient().sendToServer(new Message("#LogOut" , CurrUser));
            App.setRoot("homepage");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert back != null : "fx:id=\"back\" was not injected: check your FXML file 'order.fxml'.";
        assert cardnumber != null : "fx:id=\"cardnumber\" was not injected: check your FXML file 'order.fxml'.";
        assert clientid != null : "fx:id=\"clientid\" was not injected: check your FXML file 'order.fxml'.";
        assert clientname != null : "fx:id=\"clientname\" was not injected: check your FXML file 'order.fxml'.";
        assert dateoforder != null : "fx:id=\"dateoforder\" was not injected: check your FXML file 'order.fxml'.";
        assert delivery != null : "fx:id=\"delivery\" was not injected: check your FXML file 'order.fxml'.";
        assert note != null : "fx:id=\"note\" was not injected: check your FXML file 'order.fxml'.";
        assert note1 != null : "fx:id=\"note1\" was not injected: check your FXML file 'order.fxml'.";
        assert orderid != null : "fx:id=\"orderid\" was not injected: check your FXML file 'order.fxml'.";
        assert receivedate != null : "fx:id=\"receivedate\" was not injected: check your FXML file 'order.fxml'.";
        assert receiveraddress != null : "fx:id=\"receiveraddress\" was not injected: check your FXML file 'order.fxml'.";
        assert receiveraddress1 != null : "fx:id=\"receiveraddress1\" was not injected: check your FXML file 'order.fxml'.";
        assert receivername != null : "fx:id=\"receivername\" was not injected: check your FXML file 'order.fxml'.";
        assert receivername1 != null : "fx:id=\"receivername1\" was not injected: check your FXML file 'order.fxml'.";
        assert status != null : "fx:id=\"status\" was not injected: check your FXML file 'order.fxml'.";
        assert totalprice != null : "fx:id=\"totalprice\" was not injected: check your FXML file 'order.fxml'.";
        setOrder(App.getOrder1());
        setCurrUser(App.getUser1());
        clientname.setText(order.getReceivername());
        clientid.setText(order.getClientid());
        dateoforder.setText(order.getDate());
        receivedate.setText(order.getRecievedate());
        cardnumber.setText(order.getCard());
        delivery.setText(order.getDeliveryOp());
        if(delivery.getText().equals("Yes") && (!order.getReceivermail().equals(" "))) {
            receivername.setText(order.getReceivername());
            receiveraddress.setText(order.getReceiveraddress());
            receiveremail.setText(order.getReceivermail());
        }
        else{
            receivername.setVisible(false);
            receivername1.setVisible(false);
            receiveraddress.setVisible(false);
            receiveraddress1.setVisible(false);
            receiveremail.setVisible(false);
            receiveremail1.setVisible(false);
            note.setVisible(false);
            note1.setVisible(false);
        }
        if(!order.getNote().equals(" ")){
            note.setText(order.getNote());
        }
        else {
            note1.setVisible(false);
            note.setVisible(false);
        }
        status.setText(order.getStatus());
        totalprice.setText(order.getTotalprice());

        orderid.setText(order.getId() + " ");

    }

}
