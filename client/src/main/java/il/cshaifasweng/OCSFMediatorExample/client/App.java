package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Item;
import il.cshaifasweng.OCSFMediatorExample.entities.Report;
import il.cshaifasweng.OCSFMediatorExample.entities.ShoppingCart;
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
//    private static Item item1;
//    private static Double amount;
    boolean isRegistered = false;
    private static String type2;
    private static String start_date;
    private static String end_date;
    private static String start_date2;
    private static String end_date2;
    private static List<Report> report_list;
    private static ShoppingCart cart;

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
        return fxmlLoader.load();
    }
//
//    public static Item getItem1() {
//        return item1;
//    }
//
//    public static void setItem1(Item item1) {
//        App.item1 = item1;
//    }
//
//    public static Double getAmount() {
//        return amount;
//    }
//
//    public void setAmount(Double amount) {
//        this.amount = amount;
//    }

    public static ShoppingCart getCart() {
        return cart;
    }

    public static void setCart(ShoppingCart cart1) {
        App.cart = cart1;
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

    public static String getStart_date2() {
        return start_date2;
    }

    public static void setStart_date2(String start_date2) {
        App.start_date2 = start_date2;
    }

    public static String getEnd_date2() {
        return end_date2;
    }

    public static void setEnd_date2(String end_date2) {
        App.end_date2 = end_date2;
    }


    public static String getType1() {
        return type2;
    }

    public void setType1(String type) {
        this.type2 = type;
    }

    public static String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public static String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public static List<Report> getReport_list() {
        return report_list;
    }

    public void setReport_list(List<Report> report_list) {
        this.report_list = report_list;
    }

    @Subscribe
    public void catalogEventFunc(catalogEvent event) {
        setType(event.getType());
        setItemList(event.getItemList());
        setCart(event.getCart());
        if(itemList==null){
            System.out.println("app item list is null");
        }
        if ( (type.equals("NetworkMarketingWorker")) ) {
            Platform.runLater(() -> {
                try {
                    setRoot("NetworkMarketingWorker");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        }
        else if ( (type.equals("SystemManager")) ) {
            Platform.runLater(() -> {
                try {
                    setRoot("SystemManager");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        }
        else if ( (type.equals("BranchManager")) ) {
            Platform.runLater(() -> {
                try {
                    setRoot("BranchManager");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        }
        else if ( (type.equals("CEO")) ) {
            Platform.runLater(() -> {
                try {
                    setRoot("CEO");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        }
        else Platform.runLater(() -> {
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
        else if ( type.equals("NetworkMarketingWorker")) {
            Platform.runLater(() -> {
                try {
                    setRoot("manager");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        }
    }
    @Subscribe
    public void onWarningEvent(WarningEvent event) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.WARNING,
                    String.format("Message: %s\nTimestamp: %s\n",
                            event.getWarning().getMessage(),
                            event.getWarning().getTime().toString())
            );
            alert.show();
        });

    }

    @Subscribe
    public void onReportEvent(ReportEvent event) {
        setType(event.getType());
        setStart_date(event.getStart_date());
        setEnd_date(event.getEnd_date());
        setReport_list(event.getReport_list());
        setStart_date2(event.getStart_date2());
        setEnd_date2(event.getEnd_date2());

        Platform.runLater(() -> {
            if(event.getType().equals("Complain Report")) {
                try {
                    App.setRoot("ComplainReport");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                try {
                    App.setRoot("Report");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }
//     public void AddtocartFunc(AddToCartEvent event)
//     {
//         System.out.println(event.getItem().getName() + " in app.!!!");
//         setItem1(event.getItem());
//         setAmount(event.getAmount());
//     }
    @Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		super.stop();
	}


	public static void main(String[] args) {
        launch();
    }

}