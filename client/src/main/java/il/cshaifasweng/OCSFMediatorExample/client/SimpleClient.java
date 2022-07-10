package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import javafx.application.Platform;
import org.greenrobot.eventbus.EventBus;


import java.awt.event.ItemEvent;
import java.io.IOException;
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
		else if(msgstring.equals("#list of report sent"))
		{
			EventBus.getDefault().post(new ReportEvent((String) ((Message) msg).getObject(),(String) ((Message) msg).getObject2(),(String) ((Message) msg).getObject3(),(List<Report>) ((Message) msg).getObject4()));
		}
		else if(msgstring.equals("#list of report sent to compare"))
		{
			EventBus.getDefault().post(new ReportEvent((String) ((Message) msg).getObject(),(String) ((Message) msg).getObject2(),(String) ((Message) msg).getObject3(),(String)((Message) msg).getObject4(),(String) ((Message) msg).getObject5(),(List<Report>) ((Message) msg).getObject6()));
		}
	}
	public static SimpleClient getClient() {
		if (client == null) { client = new SimpleClient("localhost", 3000); }
		return client;
	}
}
