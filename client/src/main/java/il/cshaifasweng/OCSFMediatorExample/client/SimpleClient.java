package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;

import java.util.List;

public class SimpleClient extends AbstractClient {


	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		String msgstring = ((Message)msg ).getMessage();
		if(msgstring.startsWith("#opencatalog"))
		{
			catalogControllerUser.setItemList((List<Item>) ((Message) msg).getObject2());
			catalogControllerUser.setType((String) ((Message) msg).getObject());
		}
		if(msgstring.startsWith("#openuseritem"))
		{
			if(catalogControllerUser.getType().equals("Manager")) {
			ManagerController.setItem((Item) ((Message) msg).getObject());
			System.out.format(ManagerController.getItem().getName() + " from simple client \n"); }
			else if(catalogControllerUser.getType().equals("Guest"))
			FlowersController.setItem((Item) ((Message) msg).getObject());
			else if(catalogControllerUser.getType().equals("Client"))
			OrderCatalogController.setItem((Item) ((Message) msg).getObject());
		}
	}

	public static SimpleClient getClient() {

		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}

		return client;
	}

}
