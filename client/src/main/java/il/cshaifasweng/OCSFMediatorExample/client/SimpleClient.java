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
			catalogEvent event = new catalogEvent((String) ((Message) msg).getObject() , (List<Item>) ((Message) msg).getObject2() );
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
			catalogEvent event = new catalogEvent(user.getStatus(), (List<Item>) ((Message) msg).getObject2() );
			EventBus.getDefault().post(event);
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
	}
	public static SimpleClient getClient() {
		if (client == null) { client = new SimpleClient("localhost", 3000); }
		return client;
	}
}
