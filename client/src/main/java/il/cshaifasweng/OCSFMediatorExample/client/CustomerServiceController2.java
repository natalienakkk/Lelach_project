package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Complain;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Order;
import il.cshaifasweng.OCSFMediatorExample.entities.ShoppingCart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CustomerServiceController2 {

    @FXML
    private Text refund_hide;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back_button;

    @FXML
    private TextArea complain_message;

    @FXML
    private Text details;

    @FXML
    private Button finish_button;

    @FXML
    private Text orderId;

    @FXML
    private Text price;

    @FXML
    private TextField replay_message;

    @FXML
    private TextField refund;

    @FXML
    private Text type;

    @FXML
    private Text username;

    public static Complain getComplain() {
        return complain;
    }

    public static void setComplain(Complain complain) {
        CustomerServiceController2.complain = complain;
    }

    @FXML
    private Text details_hide;

    @FXML
    private Text order_hide;



    @FXML
    private Text price_hide;

    public static Complain complain;
    public static List<Order> order2;

    public static List<Order> getOrder2() {
        return order2;
    }

    public static void setOrder2(List<Order> order2) {
        CustomerServiceController2.order2 = order2;
    }

    @FXML
    void back(ActionEvent event) {
        try {
            App.setRoot("CustomerService_homepage");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void finish(ActionEvent event) {
        try {
            complain.setAnswer(replay_message.getText());
            if(!(refund.getText().isEmpty())) {
                complain.setRefund(Double.parseDouble(refund.getText()));
            }
            SimpleClient.getClient().sendToServer(new Message("#complain finished",complain));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            App.setRoot("CustomerService");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert back_button != null : "fx:id=\"back_button\" was not injected: check your FXML file 'CustomerService2.fxml'.";
        assert complain_message != null : "fx:id=\"complain_message\" was not injected: check your FXML file 'CustomerService2.fxml'.";
        assert details != null : "fx:id=\"details\" was not injected: check your FXML file 'CustomerService2.fxml'.";
        assert finish_button != null : "fx:id=\"finish_button\" was not injected: check your FXML file 'CustomerService2.fxml'.";
        assert orderId != null : "fx:id=\"orderId\" was not injected: check your FXML file 'CustomerService2.fxml'.";
        assert price != null : "fx:id=\"price\" was not injected: check your FXML file 'CustomerService2.fxml'.";
        assert replay_message != null : "fx:id=\"replay_message\" was not injected: check your FXML file 'CustomerService2.fxml'.";
        assert refund != null : "fx:id=\"refund\" was not injected: check your FXML file 'CustomerService2.fxml'.";
        assert type != null : "fx:id=\"type\" was not injected: check your FXML file 'CustomerService2.fxml'.";
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'CustomerService2.fxml'.";
        CustomerServiceController2.setComplain(CustomerServiceController.getComplain());
        setOrder2(App.getOrderList());

        complain_message.setText(complain.getMessage());
        username.setText(complain.getUsername());
        type.setText(complain.getComplain_type());
        complain_message.setText(complain.getMessage());
        if(complain.getOrderID()!=null) {
            int id1 = complain.getOrderID().intValue()-1;
            String id = Long.toString(complain.getOrderID());
            orderId.setText(id);
            price.setText(order2.get(id1).getTotalprice());
            String details1 = "";

            for (int i=0;i<order2.get(id1).getCart().getItems().size();i++)
            {
                if(i==order2.get(id1).getCart().getItems().size())
                {
                    details1 = details1+order2.get(id1).getCart().getAmount().get(i)+ " x " +order2.get(id1).getCart().getItems().get(i).getName();
                }
                else
                    details1 = details1+order2.get(id1).getCart().getAmount().get(i)+ "x" +order2.get(id1).getCart().getItems().get(i).getName()+ " + " ;
            }
            details.setText(details1.substring(0,details1.length()-3));
        }


        if(complain.getComplain_type().equals("General Complain")) {
            order_hide.setVisible(false);
            price_hide.setVisible(false);
            details_hide.setVisible(false);
            refund_hide.setVisible(false);
            refund.setVisible(false);
        }

    }

}
