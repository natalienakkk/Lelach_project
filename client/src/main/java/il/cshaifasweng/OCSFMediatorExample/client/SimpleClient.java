package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import javafx.application.Platform;
import org.greenrobot.eventbus.EventBus;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
public class SimpleClient extends AbstractClient {
	private static SimpleClient client = null;
	private SimpleClient(String host, int port) {
		super(host, port);
	}
	@Override protected void handleMessageFromServer(Object msg) {
		String msgstring = ((Message) msg).getMessage();
		if (msgstring.startsWith("#opencatalog"))
		{
			if((List<Item>) ((Message) msg).getObject2() == null) {
				System.out.println("simpleClient list is null");
			}
			catalogEvent event = new catalogEvent((String) ((Message) msg).getObject(), (List<Item>) ((Message) msg).getObject2());
			EventBus.getDefault().post(event);

		}
//		else if(msgstring.startsWith("#addtocart"))
//		{
//			AddToCartEvent event = new AddToCartEvent((Item) ((Message) msg).getObject(), (Double) ((Message)msg).getObject2());
//			EventBus.getDefault().post(event);
//		}
		else if(msgstring.startsWith("#submitorder"))
		{
			Order order = (Order) ((Message) msg).getObject();
			OrderEvent event = new OrderEvent((Order) ((Message) msg).getObject());
			EventBus.getDefault().post(event);
		}
		else if (msgstring.startsWith("#getcart"))
		{
			System.out.println("im in simple client in getcart");
			List<Item> b = ((List<Item>) ((Message) msg).getObject());
			List<Double> c = (List<Double>) ((Message) msg).getObject2();
			ShoppingCart a = new ShoppingCart();
			a.setItems(b);
			a.setAmount(c);
			System.out.println(a.gettotalPrice(a) + " " + " nayakat");
			ShoppingCartEvent event = new ShoppingCartEvent(a);
			EventBus.getDefault().post(event);
		}
		else if (msgstring.startsWith("#openuseritem"))
		{
			if (CatalogController.getType().equals("NetworkMarketingWorker"))
			{
				itemEvent event1 = new itemEvent((Item) ((Message) msg).getObject());
				EventBus.getDefault().post(event1);
			}
			else if (CatalogController.getType().equals("Guest"))
			{
				itemEvent event1 = new itemEvent((Item) ((Message) msg).getObject());
				EventBus.getDefault().post(event1);
			}
			else if (CatalogController.getType().equals("Client"))
			{
				itemEvent event1 = new itemEvent((Item) ((Message) msg).getObject());
				EventBus.getDefault().post(event1);
			}
		}
		else if(msgstring.equalsIgnoreCase("#SignUpWarning"))
		{
			EventBus.getDefault().post(new WarningEvent((Warning) ((Message) msg).getObject()));
		}
		else if(msgstring.equalsIgnoreCase("#MemberSignedUpSucces"))
		{
			EventBus.getDefault().post(new WarningEvent((Warning) ((Message) msg).getObject()));
//			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//			alert.setTitle("SignedUpSuccess");
//			alert.setContentText("Dear client, welcome to Lilach. you have been signed up successfully");
//			alert.showAndWait();
			try {
				App.setRoot("HomePage"); /* change to cataloooooooooooggggggg */
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(msgstring.equalsIgnoreCase("#LogInSucess"))
		{
			Registration user = (Registration) ((Message) msg).getObject();
			if((user.getStatus()).equals("Client")) {
				System.out.println("login successss! ");
				catalogEvent event = new catalogEvent(user.getStatus(), (List<Item>) ((Message) msg).getObject2());
				UserEvent event1 = new UserEvent(user);
				EventBus.getDefault().post(event);
				EventBus.getDefault().post(event1);
			}
			else {
				catalogEvent event = new catalogEvent(user.getStatus(), (List<Item>) ((Message) msg).getObject2());
				EventBus.getDefault().post(event);
			}
		}
		else if(msgstring.equalsIgnoreCase("#LoginWarning"))
		{
			EventBus.getDefault().post(new WarningEvent((Warning) ((Message) msg).getObject()));
		}
		else if(msgstring.equalsIgnoreCase("#BlockedAccount"))
		{
			EventBus.getDefault().post(new WarningEvent((Warning) ((Message) msg).getObject()));
			try {
				App.setRoot("HomePage");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(msgstring.equals("#information updated"))
		{
			EventBus.getDefault().post(new itemEvent((Item) ((Message) msg).getObject()));
		}
		else if(msgstring.equals("#item_deleted"))
		{System.out.println("saheeeeeeeeeeeeeer");
			catalogEvent event = new catalogEvent((String) ((Message) msg).getObject(), (List<Item>) ((Message) msg).getObject2());
			EventBus.getDefault().post(event);

			List<Item>list =(List<Item>) ((Message) msg).getObject2();
			for(int i=0;i<list.size();i++)
			{
				list.get(i).getName();
			}

		}
		else if(msgstring.equals("#list of report sent"))
		{
			EventBus.getDefault().post(new ReportEvent((String) ((Message) msg).getObject(),(LocalDate) ((Message) msg).getObject2(),(LocalDate) ((Message) msg).getObject3(),(List<Report>) ((Message) msg).getObject4(),(List<Complain>) ((Message) msg).getObject5()));
		}
		else if(msgstring.equals("#list of report sent to compare"))
		{
			EventBus.getDefault().post(new ReportEvent((String) ((Message) msg).getObject(),(LocalDate) ((Message) msg).getObject2(),(LocalDate) ((Message) msg).getObject3(),(LocalDate)((Message) msg).getObject4(),(LocalDate) ((Message) msg).getObject5(),(List<Complain>) ((Message) msg).getObject6()));
		}
		else if (msgstring.equals("wrong username"))
		{
			EventBus.getDefault().post(new WarningEvent((Warning) ((Message) msg).getObject()));
		}
		else if(msgstring.equals("list sent")){
			EventBus.getDefault().post(new UsernameEvent((String) ((Message) msg).getObject(),(List<Registration>) ((Message) msg).getObject2()));
		}
		else if(msgstring.equals("#list of complain sent"))
		{
			System.out.println("3essa");
			EventBus.getDefault().post(new ComplainEvent((List<Complain>) ((Message) msg).getObject(),(List<Order>) ((Message) msg).getObject2()));
			List<Order> list = (List<Order>) ((Message) msg).getObject2();
			System.out.println(list.get(0).getCart().getItems().get(0).getName() + " orderrrr from client");
			System.out.println("3essa1");
		}
	}
	public static SimpleClient getClient() {
		if (client == null) { client = new SimpleClient("localhost", 3000); }
		return client;
	}
}
