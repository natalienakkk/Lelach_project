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
		System.out.format(" recifasfwe4f13333333ved ");
		String msgstring = ((Message) msg ).getMessage();

		if(msgstring.startsWith("#SendLists"))
		{
			System.out.format("message recived ");
			catalogController.setCataloglist((List<Catalog>) ((Message) msg).getObject());
			System.out.format("message recived ");
		}
		else if(msgstring.startsWith("#openspray1"))
		{
			System.out.format(" recifasfwe4f13333333ved ");
			FlowersController.setItem((Item) ((Message) msg).getObject());
		}
		/*if(msgstring.startsWith("#ok"))
		{
			EventBus.getDefault().post(new Received((Message) msg));
			System.out.format("SADAGEagsdfg");
		}*/

	}
	
	public static SimpleClient getClient() {

		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}

		return client;
	}

}
