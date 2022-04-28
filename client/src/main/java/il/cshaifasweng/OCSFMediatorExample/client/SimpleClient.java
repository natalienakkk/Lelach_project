package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;

import java.util.List;

public class SimpleClient extends AbstractClient {

	public static Item item = null;
	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		String msgstring = ((Message)msg ).getMessage();
		System.out.format(msgstring);


		if(msgstring.startsWith("#SendLists"))
		{
			System.out.format("message recived \n");
			catalogController.setCataloglist((List<Catalog>) ((Message) msg).getObject());

		}
		if(msgstring.startsWith("#openspray1"))
		{
			System.out.format(" message for open spray recieved! \n ");
			FlowersController.setItem((Item) ((Message) msg).getObject());
			System.out.format(" message for open spray recieved! \n ");
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
