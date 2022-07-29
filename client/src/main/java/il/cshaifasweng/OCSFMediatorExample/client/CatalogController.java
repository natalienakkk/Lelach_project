package il.cshaifasweng.OCSFMediatorExample.client;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Registration;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class CatalogController {
    int Max = 5000000;
    int Min = -1;
    private static String color = null;
    @FXML public static List<Item> itemList = new ArrayList<Item>();
    @FXML public static List<Item> getItemList() { return itemList; }
    @FXML public static void setItemList(List<Item> itemList) { CatalogController.itemList = itemList; }
    @FXML public static String type;
    @FXML public static String getType() { return type; }
    @FXML public static void setType(String type) { CatalogController.type = type; }
    private String[] colors = {"White","Red","Blue","Yellow","Pink","Green","Pink and White","Rainbow"};
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Text Header;
    //////////////////////////////
    @FXML
    private Button LogOutBtn;
    //////////////////////////////
    @FXML
    private Button design;
    @FXML
    private Button button_1;
    @FXML
    private Button button_10;
    @FXML
    private Button button_11;
    @FXML
    private Button button_12;
    @FXML
    private Button button_13;
    @FXML
    private Button button_14;
    @FXML
    private Button button_15;
    @FXML
    private Button button_16;
    @FXML
    private Button button_17;
    @FXML
    private Button button_18;
    @FXML
    private Button button_19;
    @FXML
    private Button button_2;
    @FXML
    private Button button_20;
    @FXML
    private Button button_21;
    @FXML
    private Button button_22;
    @FXML
    private Button button_23;
    @FXML
    private Button button_24;
    @FXML
    private Button button_25;
    @FXML
    private Button button_3;
    @FXML
    private Button button_4;
    @FXML
    private Button button_5;
    @FXML
    private Button button_6;
    @FXML
    private Button button_7;
    @FXML
    private Button button_8;
    @FXML
    private Button button_9;
    @FXML
    private Button complain;
    @FXML
    private ComboBox<String> colorpicker;
    @FXML
    private TitledPane filter;
    @FXML
    private TextField maxprice;
    @FXML
    private TextField minprice;
    @FXML
    private ImageView photo_1;
    @FXML
    private ImageView photo_10;
    @FXML
    private ImageView photo_11;
    @FXML
    private ImageView photo_12;
    @FXML
    private ImageView photo_13;
    @FXML
    private ImageView photo_14;
    @FXML
    private ImageView photo_15;
    @FXML
    private ImageView photo_16;
    @FXML
    private ImageView photo_17;
    @FXML
    private ImageView photo_18;
    @FXML
    private ImageView photo_19;
    @FXML
    private ImageView photo_2;
    @FXML
    private ImageView photo_20;
    @FXML
    private ImageView photo_21;
    @FXML
    private ImageView photo_22;
    @FXML
    private ImageView photo_23;
    @FXML
    private ImageView photo_24;
    @FXML
    private ImageView photo_25;
    @FXML
    private ImageView photo_3;
    @FXML
    private ImageView photo_4;
    @FXML
    private ImageView photo_5;
    @FXML
    private ImageView photo_6;
    @FXML
    private ImageView photo_7;
    @FXML
    private ImageView photo_8;
    @FXML
    private ImageView photo_9;
    @FXML
    private Button other;
    @FXML
    private Button other1;
    @FXML
    private AnchorPane Pane2;
    @FXML
    private Text welcome;
    @FXML
    private MenuItem CancelOrder;
    @FXML
    private ContextMenu ContextMenu;
    @FXML
    private MenuItem MakeComplain;
    @FXML
    private MenuItem MyComplains;
    @FXML
    private MenuItem Orders;

    ////////////////////////////////////////////////
    private static Registration CurrUser;

    public static Registration getCurrUser() {
        return CurrUser;
    }

    public static void setCurrUser(Registration currUser) {
        CurrUser = currUser;
    }
    ////////////////////////////////////////////
    @FXML
    void CancelOrderButton(ActionEvent event) throws IOException {
/*//        SimpleClient.getClient().sendToServer(new Message("#OpenCancelOrder"));
        AnchorPane pane = FXMLLoader.load(getClass().getResource("CancelOrder.fxml"));
        Pane2.getChildren().setAll(pane);*/
        try {
//            App.setRoot("CancelOrder");
            SimpleClient.getClient().sendToServer(new Message("#OpenCancelOrder"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void ConetxtMenuButton(ActionEvent event) {

    }

    @FXML
    void MakeComplainButton(ActionEvent event) throws IOException {
        App.setRoot("CustomerComplain");
    }

    @FXML
    void MyComplainsButton(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer(new Message("#send complain list2", "profile"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void OrdersButton(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer(new Message("#send order list", "profile order" , getCurrUser().getUserName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void MessageButton(ActionEvent actionEvent) {
        try {
            SimpleClient.getClient().sendToServer(new Message("#send message list","profile message"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void LogOut(ActionEvent event) {
        try {
            SimpleClient.getClient().sendToServer(new Message("#LogOut" , CurrUser));
            App.setRoot("homepage");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    void other(ActionEvent event) throws IOException {
        if (type.equals("Guest")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("SingUp.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("NetworkMarketingWorker")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("NetworkMarketingWorker.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Client")) {
            //AnchorPane pane = FXMLLoader.load(getClass().getResource("cart.fxml"));
            //Pane2.getChildren().setAll(pane);

        }

    }

    @FXML
    void other1(ActionEvent event) throws IOException {
        if (type.equals("Guest")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("NetworkMarketingWorker")) {
            //AnchorPane pane = FXMLLoader.load(getClass().getResource("cart.fxml"));
            //Pane2.getChildren().setAll(pane);
        } else if (type.equals("Client")) {
            System.out.println("sending to server from catalogcontroller 1 " + getCurrUser().getUserName());
            SimpleClient.getClient().sendToServer(new Message("#getcart" , getCurrUser().getUserName()));
            System.out.println("sending to server from catalogcontroller 2");
//            AnchorPane pane = FXMLLoader.load(getClass().getResource("cart.fxml"));
//            Pane2.getChildren().setAll(pane);

        }
    }

    @FXML
    void button_5(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_5.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));

    }

    @FXML
    void button_2(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_2.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));

    }

    @FXML
    void button_4(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_4.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));

    }

    @FXML
    void button_1(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_1.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));

    }

    @FXML
    void button_3(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_3.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));

    }

    @FXML
    void button_10(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_10.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));

    }
    @FXML
    void button_11(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_11.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));

    }

    @FXML
    void button_12(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_12.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));

    }

    @FXML
    void button_13(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_13.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));

    }

    @FXML
    void button_14(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_14.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));

    }

    @FXML
    void button_15(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_15.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));

    }

    @FXML
    void button_16(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_16.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));

    }

    @FXML
    void button_17(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_17.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));

    }

    @FXML
    void button_18(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_18.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));

    }

    @FXML
    void button_19(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_19.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));

    }

    @FXML
    void button_20(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_20.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));

    }@FXML
    void button_21(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_21.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));

    }@FXML
    void button_22(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_22.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));

    }@FXML
    void button_23(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_23.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));

    }@FXML
    void button_24(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_24.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));

    }@FXML
    void button_25(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_25.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));

    }

    @FXML
    void button_6(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_6.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));

    }

    @FXML
    void button_7(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_7.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));

    }

    @FXML
    void button_8(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_8.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));

    }

    @FXML
    void button_9(ActionEvent event) throws IOException {
        long id = 50;
        for(int k =0; k<itemList.size();k++) {
            if(button_9.getText().equals(itemList.get(k).getName())) {
                id = itemList.get(k).getId();
                break;
            }
        }
        int x =(int) id;
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", x));
    }

    @FXML
    void colorpicker(ActionEvent event) {
        color=colorpicker.getSelectionModel().getSelectedItem();
        setCatalog();
    }

    @FXML
    void maxprice(ActionEvent event) {
        Max = Integer.parseInt(maxprice.getText());
        System.out.println(Max+" im in action event");
        setCatalog();
    }

    @FXML
    void maxprice1(KeyEvent event) {
        if (event.getCode() ==  KeyCode.ENTER ) {
            Max = Integer.parseInt(maxprice.getText());
            System.out.println(Max+" im in keypressed event");
        }
    }
    @FXML
    void minprice(ActionEvent event) {
        Min = Integer.parseInt(minprice.getText());
        System.out.println(Min+" im in action event");
        setCatalog();
    }



    @FXML
    void minprice1(KeyEvent event) {
        if (event.getCode() ==  KeyCode.ENTER ) {
            Min = Integer.parseInt(minprice.getText());
            System.out.println(Min+" im in keypressed event");
        }
    }

    @FXML
    void refresh(MouseEvent event) throws IOException {
            SimpleClient.getClient().sendToServer(new Message("#opencatalog" , "Client" , itemList));

    }


    @FXML
    void design1(ActionEvent event) throws IOException {
        App.setRoot("design");
    }


    ArrayList<Button> buttons_list = new ArrayList<Button>();
    ArrayList<ImageView> photo_list = new ArrayList<ImageView>();

    @FXML
    void initialize() throws InterruptedException {
        assert colorpicker != null : "fx:id=\"colorpicker\" was not injected: check your FXML file 'catalog.fxml'.";
        assert filter != null : "fx:id=\"filter\" was not injected: check your FXML file 'catalog.fxml'.";
        assert maxprice != null : "fx:id=\"maxprice\" was not injected: check your FXML file 'catalog.fxml'.";
        assert minprice != null : "fx:id=\"minprice\" was not injected: check your FXML file 'catalog.fxml'.";
        assert LogOutBtn != null : "fx:id=\"LogOutBtn\" was not injected: check your FXML file 'catalog.fxml'.";
        assert Header != null : "fx:id=\"Header\" was not injected: check your FXML file 'catalog.fxml'.";
        assert Pane2 != null : "fx:id=\"Pane2\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_1 != null : "fx:id=\"button_1\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_10 != null : "fx:id=\"button_10\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_11 != null : "fx:id=\"button_11\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_12 != null : "fx:id=\"button_12\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_13 != null : "fx:id=\"button_13\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_14 != null : "fx:id=\"button_14\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_15 != null : "fx:id=\"button_15\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_16 != null : "fx:id=\"button_16\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_17 != null : "fx:id=\"button_17\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_18 != null : "fx:id=\"button_18\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_19 != null : "fx:id=\"button_19\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_2 != null : "fx:id=\"button_2\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_20 != null : "fx:id=\"button_20\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_21 != null : "fx:id=\"button_20\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_22 != null : "fx:id=\"button_20\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_23 != null : "fx:id=\"button_20\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_24 != null : "fx:id=\"button_20\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_25 != null : "fx:id=\"button_20\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_3 != null : "fx:id=\"button_3\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_4 != null : "fx:id=\"button_4\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_5 != null : "fx:id=\"button_5\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_6 != null : "fx:id=\"button_6\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_7 != null : "fx:id=\"button_7\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_8 != null : "fx:id=\"button_8\" was not injected: check your FXML file 'catalog.fxml'.";
        assert button_9 != null : "fx:id=\"button_9\" was not injected: check your FXML file 'catalog.fxml'.";
        assert other != null : "fx:id=\"button_6\" was not injected: check your FXML file 'catalog.fxml'.";
        assert other1 != null : "fx:id=\"button_6\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_1 != null : "fx:id=\"photo_1\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_10 != null : "fx:id=\"photo_10\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_11 != null : "fx:id=\"photo_11\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_12 != null : "fx:id=\"photo_12\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_13 != null : "fx:id=\"photo_13\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_14 != null : "fx:id=\"photo_14\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_15 != null : "fx:id=\"photo_15\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_16 != null : "fx:id=\"photo_16\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_17 != null : "fx:id=\"photo_17\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_18 != null : "fx:id=\"photo_18\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_19 != null : "fx:id=\"photo_19\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_2 != null : "fx:id=\"photo_2\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_20 != null : "fx:id=\"photo_20\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_21 != null : "fx:id=\"photo_20\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_22 != null : "fx:id=\"photo_20\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_23 != null : "fx:id=\"photo_20\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_24 != null : "fx:id=\"photo_20\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_25 != null : "fx:id=\"photo_20\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_3 != null : "fx:id=\"photo_3\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_4 != null : "fx:id=\"photo_4\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_5 != null : "fx:id=\"photo_5\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_6 != null : "fx:id=\"photo_6\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_7 != null : "fx:id=\"photo_7\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_8 != null : "fx:id=\"photo_8\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_9 != null : "fx:id=\"photo_9\" was not injected: check your FXML file 'catalog.fxml'.";
        colorpicker.getItems().addAll(colors);
        setCurrUser(App.getUser1());
        setType(App.getType());
        setItemList(App.getItemList());
        for(int i=0;i<itemList.size();i++)
        {
            itemList.get(i).getName();
        }
        if(itemList == null){
            System.out.println("is null");
        }
        color = "start";
        setCatalog();
        filter.setExpanded(false);

    }


    public void setCatalog(){
//        if(CurrUser==null) welcome.setText("");
//        else if(CurrUser.getSelectedStore().equals("Lelach, Haifa")) {
//            System.out.println("aa1");
//            welcome.setText("Welcome to Lelach,Haifa");
//            System.out.println("aa2");
//        }
//        else{
//            System.out.println("aa1");
//            welcome.setText("Welcome to Lelach,Tel Aviv");
//            System.out.println("aa2");
//        }
        if (type != null) {
            if (type.equals("Guest")) {
                other.setText("Sign Up");
                other1.setText("Log In");
            } else if (type.equals("NetworkMarketingWorker")) {
                other1.setVisible(false);
                other.setText("Edit Catalog");
                filter.setVisible(false);
            } else if (type.equals("Client")) {
                other.setText("My Profile");
                other1.setText("Shopping Cart");
            } else System.out.format("fasfjkefef \n");
        }
        buttons_list.add(button_1);
        buttons_list.add(button_2);
        buttons_list.add(button_3);
        buttons_list.add(button_4);
        buttons_list.add(button_5);
        buttons_list.add(button_6);
        buttons_list.add(button_7);
        buttons_list.add(button_8);
        buttons_list.add(button_9);
        buttons_list.add(button_10);
        buttons_list.add(button_11);
        buttons_list.add(button_12);
        buttons_list.add(button_13);
        buttons_list.add(button_14);
        buttons_list.add(button_15);
        buttons_list.add(button_16);
        buttons_list.add(button_17);
        buttons_list.add(button_18);
        buttons_list.add(button_19);
        buttons_list.add(button_20);
        buttons_list.add(button_21);
        buttons_list.add(button_22);
        buttons_list.add(button_23);
        buttons_list.add(button_24);
        buttons_list.add(button_25);
        photo_list.add(photo_1);
        photo_list.add(photo_2);
        photo_list.add(photo_3);
        photo_list.add(photo_4);
        photo_list.add(photo_5);
        photo_list.add(photo_6);
        photo_list.add(photo_7);
        photo_list.add(photo_8);
        photo_list.add(photo_9);
        photo_list.add(photo_10);
        photo_list.add(photo_11);
        photo_list.add(photo_12);
        photo_list.add(photo_13);
        photo_list.add(photo_14);
        photo_list.add(photo_15);
        photo_list.add(photo_16);
        photo_list.add(photo_17);
        photo_list.add(photo_18);
        photo_list.add(photo_19);
        photo_list.add(photo_20);
        photo_list.add(photo_21);
        photo_list.add(photo_22);
        photo_list.add(photo_23);
        photo_list.add(photo_24);
        photo_list.add(photo_25);
        System.out.println(Min+" im in setcatalog");
        int size=0;
        int i;
        for ( i = 0; i < itemList.size();i++) {
            buttons_list.get(i).setVisible(true);
            photo_list.get(i).setVisible(true);
            if (itemList.get(i).getPrice() < Max && itemList.get(i).getPrice() > Min )
            {
                if(color.equals("start")) size++;
                else {
                    if(itemList.get(i).getColor().equals(color)) size++;
                }
            }
        }
        System.out.println(size);


        int g=0;
//        List<Image> imageList = new ArrayList<Image>();
//        for ( int y = 0; y < size ;y++) {
//            imageList.add(new Image(itemList.get(y).getPicture()));
//        }
        String pic =null;

        for ( i = 0; i < size ;i++) {
            for(;g<itemList.size();g++){
                if(itemList.get(g).getPrice() < Max && itemList.get(g).getPrice() > Min ) {
                    if(color.equals("start")) {
                        buttons_list.get(i).setText(itemList.get(g).getName());
                        Image image = new Image(itemList.get(g).getPicture());
                        photo_list.get(i).setImage(image);
                        g++;
                        break;
                    }
                    else {
                        if(itemList.get(g).getColor().equals(color)){
                            buttons_list.get(i).setText(itemList.get(g).getName());
                            Image image = new Image(itemList.get(g).getPicture());
                            photo_list.get(i).setImage(image);
                            g++;
                            break;
                        }
                    }
                }
            }
        }
        for ( int j=i ; j < 25 ; j++)
        {
            buttons_list.get(j).setVisible(false);
            photo_list.get(j).setVisible(false);
        }
    }

}


