package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
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
    private static LocalDate start_date;
    private static LocalDate end_date;
    private static LocalDate start_date2;
    private static LocalDate end_date2;
    private static List<Report> report_list;
    private static ShoppingCart cart5;
    private static Registration User1;
    private static Order order1;
    private static String username;
    private static List<Registration>SystemManager_list;
    private static String type_SystemManager;
    private static List<Complain> complain_list;
    private static List<ShoppingCart>shoppingcart_list;
    private static List<Order>orderList;
    private static Confirmation ConfirmationMSG;

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


    public static Confirmation getConfirmationMSG() {
        return ConfirmationMSG;
    }

    public static void setConfirmationMSG(Confirmation confirmationMSG) {
        ConfirmationMSG = confirmationMSG;
    }

    public static List<Order> getOrderList() {
        return orderList;
    }

    public static void setOrderList(List<Order> orderList) {
        App.orderList = orderList;
    }

    public static LocalDate getStart_date() {
        return start_date;
    }

    public static void setStart_date(LocalDate start_date) {
        App.start_date = start_date;
    }

    public static LocalDate getEnd_date() {
        return end_date;
    }

    public static void setEnd_date(LocalDate end_date) {
        App.end_date = end_date;
    }

    public static LocalDate getStart_date2() {
        return start_date2;
    }

    public static void setStart_date2(LocalDate start_date2) {
        App.start_date2 = start_date2;
    }

    public static LocalDate getEnd_date2() {
        return end_date2;
    }

    public static void setEnd_date2(LocalDate end_date2) {
        App.end_date2 = end_date2;
    }

    public static List<ShoppingCart> getShoppingcart_list() {
        return shoppingcart_list;
    }

    public static void setShoppingcart_list(List<ShoppingCart> shoppingcart_list) {
        App.shoppingcart_list = shoppingcart_list;
    }

    public static List<Complain> getComplain_list() { return complain_list; }

    public static void setComplain_list(List<Complain> complain_list) {
        App.complain_list = complain_list;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        App.username = username;
    }

    public static List<Registration> getSystemManager_list() {
        return SystemManager_list;
    }

    public static void setSystemManager_list(List<Registration> systemManager_list) {
        SystemManager_list = systemManager_list;
    }

    public static String getType_SystemManager() {
        return type_SystemManager;
    }

    public static void setType_SystemManager(String type_SystemManager) {
        App.type_SystemManager = type_SystemManager;
    }

    public static Order getOrder1() { return order1; }

    public static void setOrder1(Order order1) { App.order1 = order1; }

    public static Registration getUser1() {
        return User1;
    }

    public static void setUser1(Registration user1) {
        User1 = user1;
    }

    public static ShoppingCart getCart5() {
        return cart5;
    }

    public static void setCart5(ShoppingCart cart5) {
        App.cart5 = cart5;
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




    public static String getType1() {
        return type2;
    }

    public void setType1(String type) {
        this.type2 = type;
    }


    public static List<Report> getReport_list() {
        return report_list;
    }

    public void setReport_list(List<Report> report_list) {
        this.report_list = report_list;
    }

    @Subscribe
    public void shoppingcarteventfunc(ShoppingCartEvent event) {
        setCart5(event.getCart());
        System.out.println("im in app in shoppingcarteventfunc! ");
        Platform.runLater(() -> {
            try {
                setRoot("cart");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Subscribe
    public void usernameEventFunc(UsernameEvent event){
        setUsername(event.getUsername());
        setSystemManager_list(event.getList());
        setType_SystemManager(event.getType());
        Platform.runLater(() -> {
            try {
                App.setRoot("ShowListForSystemManager");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Subscribe
    public void OrderEventFunc(OrderEvent event)
    {
        setOrder1(event.getOrder());
        System.out.println(order1.getCart().getItems().get(0).getName() + " ordeeeeeeeeeeeeeeer");
        Platform.runLater(() -> {
            try {
                setRoot("order");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }
    @Subscribe
    public void onConfirmationEvent(ConfirmationEvent event) {
        setConfirmationMSG(event.getConfirmation());
        Platform.runLater(() -> {
            try {
                App.setRoot("Confirmation");
            } catch (IOException e){
                e.printStackTrace();
            }
        });
//        Platform.runLater(() -> {
//            Alert alert = new Alert(AlertType.INFORMATION,
//                    String.format("Message: %s\nTimestamp: %s\n",
//                            event.getConfirmation().getMessage(),
//                            event.getConfirmation().getTime().toString())
//            );
//            alert.show();
//        });
    }
    @Subscribe
    public void CancelOrderEventFunc(CancelOrderEvent event)
    {
        setOrderList(event.getMyOrdersList());
        Platform.runLater(() -> {
            try {
                App.setRoot("CancelOrder");
            } catch (IOException e){
                e.printStackTrace();
            }
        });
    }
    @Subscribe
    public void catalogEventFunc(catalogEvent event) {
        setType(event.getType());
        setItemList(event.getItemList());
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
        else if((type.equals("CustomerService")))
        {
            Platform.runLater(() -> {
                try {
                    setRoot("CustomerService_homepage");
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
    public void UserEventFunc(UserEvent event) {
        setUser1(event.getUser());

    }

    @Subscribe
    public void SendUsernameEvent(SendUsernameEvent event){
        setUsername(event.getUsername());
//        Platform.runLater(() -> {
//            try {
//                App.setRoot("ShowListForSystemManager");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
    }


    @Subscribe
    public void ComplainEventFunc(ComplainEvent event){
        setComplain_list(event.getComplain_list());
        setOrderList(event.getOrderList());
        Platform.runLater(() -> {
            try {
                App.setRoot("CustomerService");
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
        System.out.println("11");
        setStart_date(event.getStart_date());
        System.out.println("22");
        setEnd_date(event.getEnd_date());
        System.out.println("33");
        setReport_list(event.getReport_list());
        System.out.println("44");
        setStart_date2(event.getStart_date2());
        setEnd_date2(event.getEnd_date2());
        setComplain_list(event.getComplain_list());


        Platform.runLater(() -> {
            System.out.println("eisaaaaaaaaaaaa");
            if(event.getType().equals("Complain Report")) {
                try {
                    App.setRoot("ComplainReport");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            else if (event.getType().equals("Complain Compare")){
                System.out.println("eisaaaaaaaaaaaa");
                try {
                    App.setRoot("ComplainCompare");
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