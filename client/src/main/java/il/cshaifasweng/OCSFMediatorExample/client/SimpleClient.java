package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import javafx.application.Platform;
import org.greenrobot.eventbus.EventBus;


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
			catalogControllerUser.setType((String) ((Message) msg).getObject());
			catalogControllerUser.setItemList((List<Item>) ((Message) msg).getObject2());
			//OrderCatalogController.setCart((ShoppingCart) ((Message) msg).getObject3());
		}
		else if (msgstring.startsWith("#openuseritem"))
		{
			if (catalogControllerUser.getType().equals("Manager"))
			{
				ManagerController.setItem((Item) ((Message) msg).getObject());
			}
			else if (catalogControllerUser.getType().equals("Guest"))
			{
				FlowersController.setItem((Item) ((Message) msg).getObject());
			}
			else if (catalogControllerUser.getType().equals("Client"))
			{
				OrderCatalogController.setItem((Item) ((Message) msg).getObject());
			}
		}
	}
	public static SimpleClient getClient() {
		if (client == null) { client = new SimpleClient("localhost", 3000); }
		return client;
	}
}
