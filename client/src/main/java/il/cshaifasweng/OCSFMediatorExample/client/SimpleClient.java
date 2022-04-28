package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import org.greenrobot.eventbus.EventBus;

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
		if(msgstring.startsWith("#SendLists"))
		{

			catalogController2.setCataloglist1((List<Catalog>) ((Message) msg).getObject());


		}
		if(msgstring.startsWith("#SendLists1"))
		{

			catalogController.setCataloglist((List<Catalog>) ((Message) msg).getObject());

		}
		if(msgstring.startsWith("#openspray1"))
		{

			FlowersController.setItem((Item) ((Message) msg).getObject());


		}if(msgstring.startsWith("#1openspray4"))
		{

			ManagerController.setItem((Item) ((Message) msg).getObject());

		}
		if(msgstring.startsWith("#opendelphunim1"))
		{

			FlowersController.setItem((Item) ((Message) msg).getObject());


		}if(msgstring.startsWith("#1opendelphunim4"))
		{

			ManagerController.setItem((Item) ((Message) msg).getObject());


		}
		if(msgstring.startsWith("#openbridal1"))
		{

			FlowersController.setItem((Item) ((Message) msg).getObject());


		}if(msgstring.startsWith("#1openbridal4"))
		{

			ManagerController.setItem((Item) ((Message) msg).getObject());


		}
		if(msgstring.startsWith("#opensensivaria1"))
		{

			FlowersController.setItem((Item) ((Message) msg).getObject());


		}if(msgstring.startsWith("#1opensensivaria4"))
		{

			ManagerController.setItem((Item) ((Message) msg).getObject());


		}
		if(msgstring.startsWith("#openzamia1"))
		{

			FlowersController.setItem((Item) ((Message) msg).getObject());


		}if(msgstring.startsWith("#1openzamia4"))
		{

			ManagerController.setItem((Item) ((Message) msg).getObject());

		}

	}

	public static SimpleClient getClient() {

		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}

		return client;
	}

}
