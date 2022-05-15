package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.glass.ui.Application;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class catalogControllerUser {
    @FXML
    private static List<Item> itemList;

    @FXML
    public static List<Item> getItemList() {
        return itemList;
    }

    @FXML
    public static void setItemList(List<Item> itemList) {
        catalogControllerUser.itemList = itemList;
    }

    @FXML
    private static String type;

    @FXML
    public static String getType() {
        return type;
    }

    @FXML
    public static void setType(String type) {
        catalogControllerUser.type = type;
    }


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text Header;

    @FXML
    private Button Back;

    @FXML
    private Button buttun_1;

    @FXML
    private Button buttun_10;

    @FXML
    private Button buttun_11;

    @FXML
    private Button buttun_12;

    @FXML
    private Button buttun_13;

    @FXML
    private Button buttun_14;

    @FXML
    private Button buttun_15;

    @FXML
    private Button buttun_16;

    @FXML
    private Button buttun_17;

    @FXML
    private Button buttun_18;

    @FXML
    private Button buttun_19;

    @FXML
    private Button buttun_2;

    @FXML
    private Button buttun_20;

    @FXML
    private Button buttun_3;

    @FXML
    private Button buttun_4;

    @FXML
    private Button buttun_5;

    @FXML
    private Button buttun_6;

    @FXML
    private Button buttun_7;

    @FXML
    private Button buttun_8;

    @FXML
    private Button buttun_9;

    @FXML
    private Button other;


    @FXML
    private AnchorPane Pane2;

    @FXML
    void Back1(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        Pane2.getChildren().setAll(pane);
    }

    @FXML
    void other(ActionEvent event) {

    }


    @FXML
    void buttun_5(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", "Bridal bouquet"));
        if (type.equals("Guest")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("flowers.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Manager")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("manager.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Client")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("client.fxml"));
            Pane2.getChildren().setAll(pane);
        }
    }

    @FXML
    void buttun_2(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", "Delphinium"));
        if (type.equals("Guest")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("flowers.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Manager")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("manager.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Client")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("client.fxml"));
            Pane2.getChildren().setAll(pane);
        }
    }

    @FXML
    void buttun_4(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", "Sensivaria medium"));
        if (type.equals("Guest")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("flowers.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Manager")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("manager.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Client")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("client.fxml"));
            Pane2.getChildren().setAll(pane);
        }
    }

    @FXML
    void buttun_1(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", "Spray carnations"));
        if (type.equals("Guest")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("flowers.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Manager")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("manager.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Client")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("client.fxml"));
            Pane2.getChildren().setAll(pane);
        }
    }

    @FXML
    void buttun_3(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", "Zamia Coconut L"));
        if (type.equals("Guest")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("flowers.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Manager")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("manager.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Client")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("client.fxml"));
            Pane2.getChildren().setAll(pane);
        }
    }

    @FXML
    void buttun_10(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", "Basket Bouquet"));
        if (type.equals("Guest")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("flowers.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Manager")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("manager.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Client")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("client.fxml"));
            Pane2.getChildren().setAll(pane);
        }
    }

    @FXML
    void buttun_11(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", "Fan Bouquet"));
        if (type.equals("Guest")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("flowers.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Manager")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("manager.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Client")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("client.fxml"));
            Pane2.getChildren().setAll(pane);
        }
    }

    @FXML
    void buttun_12(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", "Fiesta Bouquet"));
        if (type.equals("Guest")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("flowers.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Manager")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("manager.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Client")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("client.fxml"));
            Pane2.getChildren().setAll(pane);
        }
    }

    @FXML
    void buttun_13(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", "Peony Bouquet"));
        if (type.equals("Guest")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("flowers.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Manager")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("manager.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Client")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("client.fxml"));
            Pane2.getChildren().setAll(pane);
        }
    }

    @FXML
    void buttun_14(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", "Hello Sunshine"));
        if (type.equals("Guest")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("flowers.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Manager")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("manager.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Client")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("client.fxml"));
            Pane2.getChildren().setAll(pane);
        }
    }

    @FXML
    void buttun_15(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", "Rainbow Roses"));
        if (type.equals("Guest")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("flowers.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Manager")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("manager.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Client")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("client.fxml"));
            Pane2.getChildren().setAll(pane);
        }
    }

    @FXML
    void buttun_16(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", "Yellow Roses"));
        if (type.equals("Guest")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("flowers.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Manager")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("manager.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Client")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("client.fxml"));
            Pane2.getChildren().setAll(pane);
        }
    }

    @FXML
    void buttun_17(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", "White Roses"));
        if (type.equals("Guest")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("flowers.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Manager")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("manager.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Client")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("client.fxml"));
            Pane2.getChildren().setAll(pane);
        }
    }

    @FXML
    void buttun_18(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", "SunFlower Bouquet"));
        if (type.equals("Guest")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("flowers.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Manager")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("manager.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Client")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("client.fxml"));
            Pane2.getChildren().setAll(pane);
        }
    }

    @FXML
    void buttun_19(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", "Friendship Bouquet"));
        if (type.equals("Guest")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("flowers.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Manager")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("manager.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Client")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("client.fxml"));
            Pane2.getChildren().setAll(pane);
        }
    }


    @FXML
    void buttun_20(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", "Plant"));
        if (type.equals("Guest")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("flowers.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Manager")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("manager.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Client")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("client.fxml"));
            Pane2.getChildren().setAll(pane);
        }
    }


    @FXML
    void buttun_6(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", "Blue Roses"));
        if (type.equals("Guest")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("flowers.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Manager")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("manager.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Client")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("client.fxml"));
            Pane2.getChildren().setAll(pane);
        }
    }

    @FXML
    void buttun_7(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", "Red Roses"));
        if (type.equals("Guest")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("flowers.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Manager")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("manager.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Client")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("client.fxml"));
            Pane2.getChildren().setAll(pane);
        }
    }

    @FXML
    void buttun_8(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", "Single Rose"));
        if (type.equals("Guest")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("flowers.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Manager")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("manager.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Client")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("client.fxml"));
            Pane2.getChildren().setAll(pane);
        }
    }

    @FXML
    void buttun_9(ActionEvent event) throws IOException {
        SimpleClient.getClient().sendToServer(new Message("#openuseritem", "Posy Bouquet"));
        if (type.equals("Guest")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("flowers.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Manager")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("manager.fxml"));
            Pane2.getChildren().setAll(pane);
        } else if (type.equals("Client")) {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("client.fxml"));
            Pane2.getChildren().setAll(pane);
        }
    }

    ArrayList<Button> buttons_list = new ArrayList<Button>();


    @FXML
    void initialize() throws InterruptedException {
        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'catalog.fxml'.";
        assert Header != null : "fx:id=\"Header\" was not injected: check your FXML file 'catalog.fxml'.";
        assert Pane2 != null : "fx:id=\"Pane2\" was not injected: check your FXML file 'catalog.fxml'.";
        assert buttun_1 != null : "fx:id=\"buttun_1\" was not injected: check your FXML file 'catalog.fxml'.";
        assert buttun_10 != null : "fx:id=\"buttun_10\" was not injected: check your FXML file 'catalog.fxml'.";
        assert buttun_11 != null : "fx:id=\"buttun_11\" was not injected: check your FXML file 'catalog.fxml'.";
        assert buttun_12 != null : "fx:id=\"buttun_12\" was not injected: check your FXML file 'catalog.fxml'.";
        assert buttun_13 != null : "fx:id=\"buttun_13\" was not injected: check your FXML file 'catalog.fxml'.";
        assert buttun_14 != null : "fx:id=\"buttun_14\" was not injected: check your FXML file 'catalog.fxml'.";
        assert buttun_15 != null : "fx:id=\"buttun_15\" was not injected: check your FXML file 'catalog.fxml'.";
        assert buttun_16 != null : "fx:id=\"buttun_16\" was not injected: check your FXML file 'catalog.fxml'.";
        assert buttun_17 != null : "fx:id=\"buttun_17\" was not injected: check your FXML file 'catalog.fxml'.";
        assert buttun_18 != null : "fx:id=\"buttun_18\" was not injected: check your FXML file 'catalog.fxml'.";
        assert buttun_19 != null : "fx:id=\"buttun_19\" was not injected: check your FXML file 'catalog.fxml'.";
        assert buttun_2 != null : "fx:id=\"buttun_2\" was not injected: check your FXML file 'catalog.fxml'.";
        assert buttun_20 != null : "fx:id=\"buttun_20\" was not injected: check your FXML file 'catalog.fxml'.";
        assert buttun_3 != null : "fx:id=\"buttun_3\" was not injected: check your FXML file 'catalog.fxml'.";
        assert buttun_4 != null : "fx:id=\"buttun_4\" was not injected: check your FXML file 'catalog.fxml'.";
        assert buttun_5 != null : "fx:id=\"buttun_5\" was not injected: check your FXML file 'catalog.fxml'.";
        assert buttun_6 != null : "fx:id=\"buttun_6\" was not injected: check your FXML file 'catalog.fxml'.";
        assert buttun_7 != null : "fx:id=\"buttun_7\" was not injected: check your FXML file 'catalog.fxml'.";
        assert buttun_8 != null : "fx:id=\"buttun_8\" was not injected: check your FXML file 'catalog.fxml'.";
        assert buttun_9 != null : "fx:id=\"buttun_9\" was not injected: check your FXML file 'catalog.fxml'.";
        assert other != null : "fx:id=\"buttun_6\" was not injected: check your FXML file 'catalog.fxml'.";
        if (type.equals("Guest")) other.setText("Sign in/up");
        else if (type.equals("Manager")) other.setText("View things");
        else if (type.equals("Client")) other.setText("My Profile");
        buttons_list.add(buttun_1);
        buttons_list.add(buttun_2);
        buttons_list.add(buttun_3);
        buttons_list.add(buttun_4);
        buttons_list.add(buttun_5);
        buttons_list.add(buttun_6);
        buttons_list.add(buttun_7);
        buttons_list.add(buttun_8);
        buttons_list.add(buttun_9);
        buttons_list.add(buttun_10);
        buttons_list.add(buttun_11);
        buttons_list.add(buttun_12);
        buttons_list.add(buttun_13);
        buttons_list.add(buttun_14);
        buttons_list.add(buttun_15);
        buttons_list.add(buttun_16);
        buttons_list.add(buttun_17);
        buttons_list.add(buttun_18);
        buttons_list.add(buttun_19);
        buttons_list.add(buttun_20);
        for (int i = 0; i < itemList.size(); i++) {
            buttons_list.get(i).setText(itemList.get(i).getName());
        }
    }


}