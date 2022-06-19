package il.cshaifasweng.OCSFMediatorExample.client;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class CatalogController {
    @FXML public static List<Item> itemList = new ArrayList<Item>();
    @FXML public static List<Item> getItemList() { return itemList; }
    @FXML public static void setItemList(List<Item> itemList) { CatalogController.itemList = itemList; }
    @FXML public static String type;
    @FXML public static String getType() { return type; }
    @FXML public static void setType(String type) { CatalogController.type = type; }
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Text Header;
    @FXML
    private Button Back;
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
    void Back1(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        Pane2.getChildren().setAll(pane);
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
            AnchorPane pane = FXMLLoader.load(getClass().getResource("cart.fxml"));
            Pane2.getChildren().setAll(pane);

        }
    }

    @FXML
    void button_5(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 5));

    }

    @FXML
    void button_2(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 2));

    }

    @FXML
    void button_4(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 4));

    }

    @FXML
    void button_1(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 1));

    }

    @FXML
    void button_3(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 3));

    }

    @FXML
    void button_10(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 10));

    }
    @FXML
    void button_11(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 11));

    }

    @FXML
    void button_12(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 12));

    }

    @FXML
    void button_13(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 13));

    }

    @FXML
    void button_14(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 14));

    }

    @FXML
    void button_15(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 15));

    }

    @FXML
    void button_16(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 16));

    }

    @FXML
    void button_17(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 17));

    }

    @FXML
    void button_18(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 18));

    }

    @FXML
    void button_19(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 19));

    }

    @FXML
    void button_20(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 20));

    }@FXML
    void button_21(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 21));

    }@FXML
    void button_22(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 22));

    }@FXML
    void button_23(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 23));

    }@FXML
    void button_24(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 24));

    }@FXML
    void button_25(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 25));

    }

    @FXML
    void button_6(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 6));

    }

    @FXML
    void button_7(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 7));

    }

    @FXML
    void button_8(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 8));

    }

    @FXML
    void button_9(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", 9));
    }



    ArrayList<Button> buttons_list = new ArrayList<Button>();
    ArrayList<ImageView> photo_list = new ArrayList<ImageView>();

    @FXML
    void initialize() throws InterruptedException {
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
        setType(App.getType());
        setItemList(App.getItemList());
        if(itemList == null){
        System.out.println("is null");
        }
        setCatalog();

    }


public void setCatalog(){

        if (type != null) {
            if (type.equals("Guest")) {
                other.setText("Sign Up");
                other1.setText("Log In");
            } else if (type.equals("NetworkMarketingWorker")) {
                other1.setVisible(false);
                other.setText("Add Item");
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
        for (int i = 0; i < itemList.size(); i++) {
            buttons_list.get(i).setText(itemList.get(i).getName());
            File file = new File("C:\\Users\\Saher\\IdeaProjects\\saher-eissa\\client\\src\\main\\resources\\images\\" + itemList.get(i).getName() + ".jpg");
            Image image = new Image(file.toURI().toString());
            photo_list.get(i).setImage(image);
        }
        for ( int j=itemList.size() ; j < 25 ; j++)
        {
            buttons_list.get(j).setVisible(false);
            photo_list.get(j).setVisible(false);
        }
    }

}


