package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private SimpleClient client;
    private static String type;
    private static List<Item> itemList;
    private static Item item;
    boolean isRegistered = false;

    @Override
    public void start(Stage stage) throws IOException {
        if(!isRegistered) {
            EventBus.getDefault().register(this);
            isRegistered = true;
            System.out.println("registered");
        }
    	client = SimpleClient.getClient();
    	client.openConnection();
        scene = new Scene(loadFXML("homepage"), 770, 550);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.
                load();
    }

    public static String getType() {
        return type;
    }

    public static void setType(String appType) {
        type = appType;
    }

    public static List<Item> getItemList() {
        return itemList;
    }

    public static void setItemList(List<Item> List) {
        itemList = List;
    }

    public static Item getItem() {
        return item;
    }

    public static void setItem(Item it) {
        item = it;
    }



    @Subscribe
    public void catalogEventFunc(catalogEvent event) {
        System.out.println("in catalogEventFunc");
        setType(event.getType());
        setItemList(event.getItemList());
        if(itemList==null){
            System.out.println("app item list is null");
        }
        Platform.runLater(()-> {
            try {
                setRoot("catalog");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Subscribe
    public void itemEventFunc(itemEvent event)
    {
        setItem(event.getItem());
        if ( type.equals("Guest")) {
            Platform.runLater(() -> {
                try {
                    setRoot("flowers");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        }
        else if ( type.equals("Client")) {
            Platform.runLater(() -> {
                try {
                    setRoot("client");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        }
        else if ( type.equals("Manager")) {
            Platform.runLater(() -> {
                try {
                    setRoot("manager");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        }
    }

    @Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		super.stop();
	}


	public static void main(String[] args) {
        launch();
    }

}