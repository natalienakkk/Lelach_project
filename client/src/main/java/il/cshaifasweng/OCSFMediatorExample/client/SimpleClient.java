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
			System.out.println("heeeeeeey10 \n");
			if((List<Item>) ((Message) msg).getObject2() == null) {
				System.out.println("simpleClient list is null");
			}
			System.out.println("heeeeeeey\n");
			catalogEvent event = new catalogEvent((String) ((Message) msg).getObject() , (List<Item>) ((Message) msg).getObject2() );
			EventBus.getDefault().post(event);
		}
		else if (msgstring.startsWith("#openuseritem"))
		{
			if (catalogControllerUser.getType().equals("Manager"))
			{
				itemEvent event1 = new itemEvent((Item) ((Message) msg).getObject());
				EventBus.getDefault().post(event1);
			}
			else if (catalogControllerUser.getType().equals("Guest"))
			{
				itemEvent event1 = new itemEvent((Item) ((Message) msg).getObject());
				EventBus.getDefault().post(event1);
			}
			else if (catalogControllerUser.getType().equals("Client"))
			{
				itemEvent event1 = new itemEvent((Item) ((Message) msg).getObject());
				EventBus.getDefault().post(event1);
			}
		}
	}
	public static SimpleClient getClient() {
		if (client == null) { client = new SimpleClient("localhost", 3000); }
		return client;
	}
}
