package il.cshaifasweng.OCSFMediatorExample.client;
import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
public class catalogControllerUser implements Initializable {
    @FXML private static List<Item> itemList;
    @FXML public static List<Item> getItemList() { return itemList; }
    @FXML public static void setItemList(List<Item> itemList) { catalogControllerUser.itemList = itemList; }
    @FXML private static String type;
    @FXML public static String getType() { return type; }
    @FXML public static void setType(String type) { catalogControllerUser.type = type; }
    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private Text Header;
    @FXML private Button Back;
    @FXML private Button button_1;
    @FXML private Button button_10;
    @FXML private Button button_11;
    @FXML private Button button_12;
    @FXML private Button button_13;
    @FXML private Button button_14;
    @FXML private Button button_15;
    @FXML private Button button_16;
    @FXML private Button button_17;
    @FXML private Button button_18;
    @FXML private Button button_19;
    @FXML private Button button_2;
    @FXML private Button button_20;
    @FXML private Button button_3;
    @FXML private Button button_4;
    @FXML private Button button_5;
    @FXML private Button button_6;
    @FXML private Button button_7;
    @FXML private Button button_8;
    @FXML private Button button_9;
    @FXML private ImageView photo_1;
    @FXML private ImageView photo_10;
    @FXML private ImageView photo_11;
    @FXML private ImageView photo_12;
    @FXML private ImageView photo_13;
    @FXML private ImageView photo_14;
    @FXML private ImageView photo_15;
    @FXML private ImageView photo_16;
    @FXML private ImageView photo_17;
    @FXML private ImageView photo_18;
    @FXML private ImageView photo_19;
    @FXML private ImageView photo_2;
    @FXML private ImageView photo_20;
    @FXML private ImageView photo_3;
    @FXML private ImageView photo_4;
    @FXML private ImageView photo_5;
    @FXML private ImageView photo_6;
    @FXML private ImageView photo_7;
    @FXML private ImageView photo_8;
    @FXML private ImageView photo_9;
    @FXML private Button other;
    @FXML private Button other1;
    @FXML private AnchorPane Pane2;
    @FXML void Back1(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        Pane2.getChildren().setAll(pane);
    }
    @FXML void other(ActionEvent event) { }
    @FXML void other1(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("cart.fxml"));
        Pane2.getChildren().setAll(pane); }
    @FXML void button_5(ActionEvent event) throws IOException { SimpleClient.getClient().sendToServer(new Message("#openuseritem", 5));func(); }
    @FXML void button_2(ActionEvent event) throws IOException { SimpleClient.getClient().sendToServer(new Message("#openuseritem", 2));func(); }
    @FXML void button_4(ActionEvent event) throws IOException { SimpleClient.getClient().sendToServer(new Message("#openuseritem", 4));func(); }
    @FXML void button_1(ActionEvent event) throws IOException { SimpleClient.getClient().sendToServer(new Message("#openuseritem", 1));func(); }
    @FXML void button_3(ActionEvent event) throws IOException { SimpleClient.getClient().sendToServer(new Message("#openuseritem", 3));func(); }
    @FXML void button_10(ActionEvent event) throws IOException { SimpleClient.getClient().sendToServer(new Message("#openuseritem", 10));func(); }
    @FXML void button_11(ActionEvent event) throws IOException { SimpleClient.getClient().sendToServer(new Message("#openuseritem", 11));func(); }
    @FXML void button_12(ActionEvent event) throws IOException { SimpleClient.getClient().sendToServer(new Message("#openuseritem", 12));func(); }
    @FXML void button_13(ActionEvent event) throws IOException { SimpleClient.getClient().sendToServer(new Message("#openuseritem", 13));func(); }
    @FXML void button_14(ActionEvent event) throws IOException { SimpleClient.getClient().sendToServer(new Message("#openuseritem", 14));func(); }
    @FXML void button_15(ActionEvent event) throws IOException { SimpleClient.getClient().sendToServer(new Message("#openuseritem", 15));func(); }
    @FXML void button_16(ActionEvent event) throws IOException { SimpleClient.getClient().sendToServer(new Message("#openuseritem", 16));func(); }
    @FXML void button_17(ActionEvent event) throws IOException { SimpleClient.getClient().sendToServer(new Message("#openuseritem", 17));func(); }
    @FXML void button_18(ActionEvent event) throws IOException { SimpleClient.getClient().sendToServer(new Message("#openuseritem", 18));func(); }
    @FXML void button_19(ActionEvent event) throws IOException { SimpleClient.getClient().sendToServer(new Message("#openuseritem", 19));func(); }
    @FXML void button_20(ActionEvent event) throws IOException { SimpleClient.getClient().sendToServer(new Message("#openuseritem", 20));func(); }
    @FXML void button_6(ActionEvent event) throws IOException { SimpleClient.getClient().sendToServer(new Message("#openuseritem", 6));func(); }
    @FXML void button_7(ActionEvent event) throws IOException { SimpleClient.getClient().sendToServer(new Message("#openuseritem", 7));func(); }
    @FXML void button_8(ActionEvent event) throws IOException { SimpleClient.getClient().sendToServer(new Message("#openuseritem", 8));func(); }
    @FXML void button_9(ActionEvent event) throws IOException { SimpleClient.getClient().sendToServer(new Message("#openuseritem", 9));func(); }
    @FXML public void func() throws IOException {
        if (type.equals("Guest")) { App.setRoot("flowers"); }
        else if (type.equals("Manager")) { App.setRoot("manager"); }
        else if (type.equals("Client")) { App.setRoot("client"); }
    }
    @FXML ArrayList<Button> buttons_list = new ArrayList<Button>();
    @FXML ArrayList<ImageView> photo_list = new ArrayList<ImageView>();
//    @FXML void initialize() throws InterruptedException {
//        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert Header != null : "fx:id=\"Header\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert Pane2 != null : "fx:id=\"Pane2\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert button_1 != null : "fx:id=\"button_1\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert button_10 != null : "fx:id=\"button_10\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert button_11 != null : "fx:id=\"button_11\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert button_12 != null : "fx:id=\"button_12\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert button_13 != null : "fx:id=\"button_13\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert button_14 != null : "fx:id=\"button_14\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert button_15 != null : "fx:id=\"button_15\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert button_16 != null : "fx:id=\"button_16\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert button_17 != null : "fx:id=\"button_17\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert button_18 != null : "fx:id=\"button_18\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert button_19 != null : "fx:id=\"button_19\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert button_2 != null : "fx:id=\"button_2\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert button_20 != null : "fx:id=\"button_20\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert button_3 != null : "fx:id=\"button_3\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert button_4 != null : "fx:id=\"button_4\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert button_5 != null : "fx:id=\"button_5\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert button_6 != null : "fx:id=\"button_6\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert button_7 != null : "fx:id=\"button_7\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert button_8 != null : "fx:id=\"button_8\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert button_9 != null : "fx:id=\"button_9\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert other != null : "fx:id=\"button_6\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert other1 != null : "fx:id=\"button_6\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert photo_1 != null : "fx:id=\"photo_1\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert photo_10 != null : "fx:id=\"photo_10\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert photo_11 != null : "fx:id=\"photo_11\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert photo_12 != null : "fx:id=\"photo_12\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert photo_13 != null : "fx:id=\"photo_13\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert photo_14 != null : "fx:id=\"photo_14\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert photo_15 != null : "fx:id=\"photo_15\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert photo_16 != null : "fx:id=\"photo_16\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert photo_17 != null : "fx:id=\"photo_17\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert photo_18 != null : "fx:id=\"photo_18\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert photo_19 != null : "fx:id=\"photo_19\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert photo_2 != null : "fx:id=\"photo_2\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert photo_20 != null : "fx:id=\"photo_20\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert photo_3 != null : "fx:id=\"photo_3\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert photo_4 != null : "fx:id=\"photo_4\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert photo_5 != null : "fx:id=\"photo_5\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert photo_6 != null : "fx:id=\"photo_6\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert photo_7 != null : "fx:id=\"photo_7\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert photo_8 != null : "fx:id=\"photo_8\" was not injected: check your FXML file 'catalog.fxml'.";
//        assert photo_9 != null : "fx:id=\"photo_9\" was not injected: check your FXML file 'catalog.fxml'.";
//        if (type == null) System.out.format(" type is NULL \n");
//        else if (type.equals("Guest")){ other.setText("Sign in/up"); other1.setText("walashi");}
//        else if (type.equals("Manager")){ other.setText("View things"); other1.setText("add/remove item");}
//        else if (type.equals("Client")){ other.setText("My Profile"); other1.setText("Shopping Cart"); }
//        buttons_list.add(button_1);
//        buttons_list.add(button_2);
//        buttons_list.add(button_3);
//        buttons_list.add(button_4);
//        buttons_list.add(button_5);
//        buttons_list.add(button_6);
//        buttons_list.add(button_7);
//        buttons_list.add(button_8);
//        buttons_list.add(button_9);
//        buttons_list.add(button_10);
//        buttons_list.add(button_11);
//        buttons_list.add(button_12);
//        buttons_list.add(button_13);
//        buttons_list.add(button_14);
//        buttons_list.add(button_15);
//        buttons_list.add(button_16);
//        buttons_list.add(button_17);
//        buttons_list.add(button_18);
//        buttons_list.add(button_19);
//        buttons_list.add(button_20);
//        photo_list.add(photo_1);
//        photo_list.add(photo_2);
//        photo_list.add(photo_3);
//        photo_list.add(photo_4);
//        photo_list.add(photo_5);
//        photo_list.add(photo_6);
//        photo_list.add(photo_7);
//        photo_list.add(photo_8);
//        photo_list.add(photo_9);
//        photo_list.add(photo_10);
//        photo_list.add(photo_11);
//        photo_list.add(photo_12);
//        photo_list.add(photo_13);
//        photo_list.add(photo_14);
//        photo_list.add(photo_15);
//        photo_list.add(photo_16);
//        photo_list.add(photo_17);
//        photo_list.add(photo_18);
//        photo_list.add(photo_19);
//        photo_list.add(photo_20);
//        for (int i = 0; i < itemList.size(); i++) {
//            buttons_list.get(i).setText(itemList.get(i).getName());
//            File file = new File("C:\\Users\\Saher\\IdeaProjects\\saher-eissa\\client\\src\\main\\resources\\images\\"+itemList.get(i).getName()+".jpg");
//            Image image = new Image(file.toURI().toString());
//            photo_list.get(i).setImage(image);
//        }
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'catalog.fxml'.";
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
        assert photo_3 != null : "fx:id=\"photo_3\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_4 != null : "fx:id=\"photo_4\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_5 != null : "fx:id=\"photo_5\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_6 != null : "fx:id=\"photo_6\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_7 != null : "fx:id=\"photo_7\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_8 != null : "fx:id=\"photo_8\" was not injected: check your FXML file 'catalog.fxml'.";
        assert photo_9 != null : "fx:id=\"photo_9\" was not injected: check your FXML file 'catalog.fxml'.";
        if (type == null) System.out.format(" type is NULL \n");
        else if (type.equals("Guest")){ other.setText("Sign in/up"); other1.setText("walashi");}
        else if (type.equals("Manager")){ other.setText("View things"); other1.setText("add/remove item");}
        else if (type.equals("Client")){ other.setText("My Profile"); other1.setText("Shopping Cart"); }
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
        for (int i = 0; i < itemList.size(); i++) {
            buttons_list.get(i).setText(itemList.get(i).getName());
            File file = new File("C:\\Users\\Saher\\IdeaProjects\\saher-eissa\\client\\src\\main\\resources\\images\\"+itemList.get(i).getName()+".jpg");
            Image image = new Image(file.toURI().toString());
            photo_list.get(i).setImage(image);
        }
    }
}