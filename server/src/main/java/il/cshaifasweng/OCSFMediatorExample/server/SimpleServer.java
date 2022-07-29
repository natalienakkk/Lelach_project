package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
//import il.cshaifasweng.OCSFMediatorExample.entities.Image;
import il.cshaifasweng.OCSFMediatorExample.server.Helpers.MyThread;
import il.cshaifasweng.OCSFMediatorExample.server.Helpers.RefundCheck;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
 import il.cshaifasweng.OCSFMediatorExample.entities.ShoppingCart;

//import javax.imageio.spi.ServiceRegistry;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;




public class SimpleServer extends AbstractServer {
	private static Session session;
	private static SessionFactory sessionFactory = getSessionFactory();
	int firsttime =0;
	int firsttime1 =0;
	public SimpleServer(int port) {
		super(port);

	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) throws IOException {

		String msgString = msg.toString();
		if (msgString.equals("#opencatalog")) {
			Message msg3 = ((Message) msg);
			String user_type = (String) msg3.getObject();
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Item> itemList = getAll(Item.class);
			List<ShoppingCart> a = new ArrayList<ShoppingCart>();
			for (int i = 0; i < itemList.size(); i++) {
				itemList.get(i).setCartList(a);
			}
			client.sendToClient(new Message("#opencatalog", user_type, itemList));
			session.close();
		} else if (msgString.startsWith("#deletefromcart")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Message msg3 = ((Message) msg);
			ShoppingCart cart1 = (ShoppingCart) msg3.getObject();
			int i = (int) msg3.getObject2();
			//cart1.RemovefromCart(i);
			cart1.RemovefromCart(i);
			cart1.gettotalPrice(cart1);
			session.update(cart1);
			session.getTransaction().commit();
			session.flush();
			session.close();
		} else if (msgString.startsWith("#submitorder")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Message msg1 = ((Message) msg);
			Order order = (Order) msg1.getObject();
			ShoppingCart cart = (ShoppingCart) msg1.getObject2();
			int totalpricefinal = (int) msg1.getObject3();
			int price = cart.gettotalPrice(cart);
			if(order.getDeliveryOp().equals("Yes")) totalpricefinal-=20;
			order.setCart(cart);
			List<ShoppingCart> a = new ArrayList<ShoppingCart>();
			for (int i = 0; i < cart.getItems().size(); i++) {
				order.getCart().getItems().set(i, cart.getItems().get(i));
			}
			order.getCart().setAmount(cart.getAmount());
			order.getCart().gettotalPrice(cart);

			if (totalpricefinal != -1) {
				if (price != totalpricefinal) {

					Registration User = null;
					List<Registration> registrations = getAll(Registration.class);
					for (int i = 0; i < registrations.size(); i++) {
						if (registrations.get(i).getUserName().equals(order.getClientname())) {
							User = registrations.get(i);
						}

					}
					if(!(price==(0.9*totalpricefinal)))
					User.setRefund(User.getRefund() - (price - totalpricefinal));
					session.update(User);
					session.getTransaction().commit();
				}
			}


//			for(int i =0; i<cart.getItems().size();i++)
//			{
//
//			}
			if (cart.getItems().size() == 0) {
				Warning new_warning = new Warning("Dear Client,youre cart is empty!");
				client.sendToClient(new Message("#submitorderwarning", new_warning));
			} else if (order.getRecievedate().equals("a")) {
				Warning new_warning = new Warning("Dear Client,you should fill the receive Date + Time!");
				client.sendToClient(new Message("#submitorderwarning", new_warning));
			} else if (order.getDeliveryOp().equals("None")) {
				Warning new_warning = new Warning("Dear Client, you should fill the Delivery option!");
				client.sendToClient(new Message("#submitorderwarning", new_warning));
			} else {
				String details1 = "";

				for (int j = 0; j < order.getCart().getItems().size(); j++) {
					if (j == order.getCart().getItems().size() - 1) {
						details1 = details1 + order.getCart().getAmount().get(j) + " x " + order.getCart().getItems().get(j).getName();
					} else
						details1 = details1 + order.getCart().getAmount().get(j) + "x" + order.getCart().getItems().get(j).getName() + " + ";
				}
				session.save(order);
				session.update(cart);

				String t = "Dear Mr/Mrs " + order.getClientname() + '\n'
						+ "Thank you for your Purchase!" + '\n'
						+ "Your order Contains :" + details1 + '\n'
						+ "Your Recive Date is : " + order.getRecievedate() + " " + order.getRecievetime() + '\n'
						+ "Order ID : " + order.getId();
				String f = "Dear Mr/Mrs " + order.getReceivername() + '\n'
						+ "There is a Delievery coming for you from our store, Sent By " + order.getClientname() + '\n'
						+ "Your order Contains :" + details1 + '\n'
						+ "Your Recive Date is : " + order.getRecievedate() + " " + order.getRecievetime() + '\n';
				String note = '\n' + "Note from " + order.getClientname() + " : " + order.getNote();
				if (!order.getNote().equals(null)) f = f + note;
				client.sendToClient(new Message("#submitorder", order, cart));
				if (order.getReceivermail().equals("a")) {
					Thread thread = new Thread(new MyThread(order.getClientmail(), "Order Summary", t));
					thread.start();
				}
				else{
					Thread thread = new Thread(new MyThread(order.getReceivermail(), "Somone Sent you a gift!!", f));
					thread.start();
					Thread thread1 = new Thread(new MyThread(order.getClientmail(), "Order Summary", t));
					thread1.start();

				}
			}
			session.close();
		} else if (msgString.startsWith("#openuseritem")) {
			Message msg2 = ((Message) msg);
			int flowerid = (int) msg2.getObject();
			session = sessionFactory.openSession();
			List<Item> Itemlist = getAll(Item.class);
			for (int i = 0; i < Itemlist.size(); i++) {
				if (Itemlist.get(i).getId() == flowerid) {
					List<ShoppingCart> a = new ArrayList<ShoppingCart>();
					Itemlist.get(i).setCartList(a);
					client.sendToClient(new Message("#openuseritem", Itemlist.get(i)));
					break;
				}
			}
			session.close();
		}
		else if (msgString.startsWith("#addtocart")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			ShoppingCart cart1 = null;
			Message msg1 = ((Message) msg);
			Item item = (Item) msg1.getObject();
			double amount = (double) msg1.getObject2();
			String username = (String) msg1.getObject3();
			int ind = -1;
			List<FirstTime> firstTimes = getAll(FirstTime.class);
			for (int i = 0; i < firstTimes.size(); i++) {
				if (firstTimes.get(i).getUsername().equals(username)) {
					ind = i;
				}
			}
			if (firstTimes.get(ind).getFirsttime() == 0) {
				cart1 = new ShoppingCart();
				cart1.AddtoCart(item);
				cart1.Addamount(amount);
				cart1.gettotalPrice(cart1);
				cart1.setUsernamee(username);
				session.save(item);
				session.save(cart1);
				firstTimes.get(ind).setFirsttime(1);
				firstTimes.get(ind).setFirsttime1(1);
				session.update(firstTimes.get(ind));
			} else {
				List<ShoppingCart> cartList = getAll(ShoppingCart.class);
				int flag0 = 0;
				int index = 0;

				cart1 = cartList.get(cartList.size() - 1);

				for (int i = cartList.size() - 1; i >= 0; i--) {
					if (cartList.get(i).getUsernamee().equals(username)) {
						cart1 = cartList.get(i);
						break;
					}

				}
				for (int j = 0; j < cartList.get(cartList.size() - 1).getItems().size(); j++) {
					if (item.getName().equals(cartList.get(cartList.size() - 1).getItems().get(j).getName())) {
						flag0 = 1;
						index = j;
					}
				}
				if (flag0 == 0) {
					cart1.AddtoCart(item);
					cart1.Addamount(amount);
					cart1.gettotalPrice(cart1);
				} else if (flag0 == 1) {
					cart1.getAmount().set(index, cart1.getAmount().get(index) + amount);
					cart1.gettotalPrice(cart1);
				}


			}

			if (cart1 == null) System.out.println("null!!\n");


			//List<ShoppingCart> a = getAll(ShoppingCart.class);
//			for (int i = 0 ;i<a.size() ; i++)
//			{
//				a.get(i).gettotalPrice(a.get(i));
//			}

			session.update(cart1);
			session.getTransaction().commit();
			session.close();


		}
		else if (msgString.startsWith("#getcart")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			ShoppingCart cart1 = null;
			Message msg1 = ((Message) msg);
			String username = (String) msg1.getObject();
			Registration User = null;
			List<Registration> registrations = getAll(Registration.class);
			for (int i = 0; i < registrations.size(); i++) {
				if (registrations.get(i).getUserName().equals(username)) {
					User = registrations.get(i);
				}

			}
			List<ShoppingCart> cartList = getAll(ShoppingCart.class);
			for (int i = cartList.size() - 1; i >= 0; i--) {
				if (cartList.get(i).getUsernamee().equals(username)) {
					cart1 = cartList.get(i);
					break;
				}

			}
			int ind = -1;
			List<FirstTime> firstTimes = getAll(FirstTime.class);
			for (int i = 0; i < firstTimes.size(); i++) {
				if (firstTimes.get(i).getUsername().equals(username)) {
					ind = i;
				}
			}
			if (firstTimes.get(ind).getFirsttime1() == 0) {
				ShoppingCart cart = new ShoppingCart();
				client.sendToClient(new Message("#getcart", cart));
			} else {
				Order ord = new Order();
				cart1.setOrder(ord);
				List<Item> a = new ArrayList<Item>();
				List<Double> b = new ArrayList<Double>();
				for (int i = 0; i < cart1.getItems().size(); i++) {
					a.add(cart1.getItems().get(i));
					b.add(cart1.getAmount().get(i));
				}
				cart1.setItems(a);
				cart1.setAmount(b);
				List<ShoppingCart> c = new ArrayList<ShoppingCart>();
				for (int i = 0; i < cart1.getItems().size(); i++) {
					cart1.getItems().get(i).setCartList(c);
				}
//				cart1.setItems(cart2.getItems());
//				cart1.setAmount(cart2.getAmount());
				client.sendToClient(new Message("#getcart", cart1, User));
			}
			session.update(cart1);
			session.getTransaction().commit();
			session.flush();
			session.close();

		}
		else if (msgString.startsWith("#warning")) {
			Warning warning = new Warning("Warning from server!");
			try {
				client.sendToClient(warning);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#SignUpRequest")) {
			Message msg1 = ((Message) msg);
			Registration newSignUp = (Registration) msg1.getObject();
			String ID1 = newSignUp.getClient_ID();
			String UserName = newSignUp.getUserName();
			session = sessionFactory.openSession();
			try {
				List<Registration> clients = getAll(Registration.class);
				for (Registration registration : clients) {
					if (registration.getClient_ID().equals(ID1)) {
//						registration.setRegistered(false);
						Warning new_warning = new Warning("Dear " + newSignUp.getFirstName() + ",you are already Signed up.\n Please go to Login.");
						client.sendToClient(new Message("#SignUpWarning", new_warning));
						return;
					}
					else if(registration.getUserName().equals(UserName)){
//						registration.setRegistered(false);
						Warning new_warning = new Warning("Dear " + newSignUp.getFirstName() + ", UserName is already taken.\n Please select another UserName.");
						client.sendToClient(new Message("#SignUpWarning", new_warning));
						return;
					}
				}
				session.beginTransaction();
				session.save(newSignUp);
				session.flush();
				session.getTransaction().commit();
				////////////////////////////////////////////////////
				Confirmation newWarning = new Confirmation("Dear " + newSignUp.getUserName() + " welcome to Lilach. you have been signed up successfully");
				client.sendToClient(new Message("#MemberSignedUpSucces", newWarning));
				Thread thread = new Thread(new MyThread(newSignUp.getEmail(),"Welcome to Lelach!","Dear Mr/Mrs "+newSignUp.getFirstName() + '\n' + "Your Sign Up was completed successfully ,Have fun shopping with Lelach. "));
				thread.run();
				session.close();
				return;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (msgString.startsWith("#LoginRequest")) {
			Message msg1 = ((Message) msg);
			User newLogin = (User) msg1.getObject();
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Item> itemList = getAll(Item.class);
			int flago = 0;
			String UserName = newLogin.getUserName();
			List<FirstTime> firstTimes = getAll(FirstTime.class);
			for (int i = 0; i < firstTimes.size(); i++) {
				if (firstTimes.get(i).getUsername().equals(UserName)) {
					firstTimes.get(i).setFirsttime(0);
					firstTimes.get(i).setFirsttime1(0);
					flago = 1;
					session.update(firstTimes.get(i));
				}
			}
			if (flago == 0) {
				FirstTime firstTime = new FirstTime(0, 0, UserName);
				session.save(firstTime);
			}
			String Password = newLogin.getPassword();
			int UserNameFound = -1;
			try {
				List<Registration> clients = getAll(Registration.class);
				for (Registration registration : clients) {
					if (registration.getUserName().equals(UserName)) {
						UserNameFound = 1;
						if (registration.getPassword().equals(Password)) {
							if (registration.getStatus().equalsIgnoreCase("blocked client")) {
								Warning new_warning = new Warning("You're account have been blocked. Please contact customer service");
								client.sendToClient(new Message("#BlockedAccount", new_warning));
//								return;
							}
							else if (registration.getRegistered().equals(true)) {
								Warning newWarning = new Warning("You're already logged in from another computer");
								client.sendToClient(new Message("#LoginWarning", newWarning));
//								return;
							}
							else {
								registration.setRegistered(true);
//								client.sendToClient(new Message("#LogInSucess", registration, itemList));
//								session.update(registration);
//								session.flush();
//								session.getTransaction().commit();

								List<ShoppingCart> a = new ArrayList<ShoppingCart>();
								for (int i = 0; i < itemList.size(); i++) {
									itemList.get(i).setCartList(a);
								}
//								List<ShoppingCart> cartList = getAll(ShoppingCart.class);
//								for(int i=cartList.size()-1;i>=0;i--)
//								{
//									if(cartList.get(i).getUsernamee().equals(UserName)) {
//										cartList.get(i).setFirstime1(0);
//										cartList.get(i).setFirstime(0);
//									}
//								}
								client.sendToClient(new Message("#LogInSucess", registration, itemList));
								session.update(registration);
								session.flush();
								session.getTransaction().commit();
							}
						} else {
							Warning new_warning = new Warning("You have entered invalid input. Please try again ");
							client.sendToClient(new Message("#LoginWarning", new_warning));
							return;
						}
					}
				}
				if (UserNameFound != 1) {
					Warning new_warning = new Warning("You have entered invalid input. Please try again ");
					client.sendToClient(new Message("#LoginWarning", new_warning));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else if (msgString.equals("#update flower")) {
			Message msgupdate = ((Message) msg);
			Long flower_id = ((Long) msgupdate.getObject());
			String type = ((String) msgupdate.getObject2());
			String info = ((String) msgupdate.getObject3());
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Item> itemsList = getAll(Item.class);
			for (int i = 0; i < itemsList.size(); i++) {
				if (itemsList.get(i).getId().equals(flower_id)) {
					if (type.equals("color_update")) {
						itemsList.get(i).setColor(info);
						session.update(itemsList.get(i));
						session.getTransaction().commit();
						break;
					} else if (type.equals("name_update")) {
						itemsList.get(i).setName(info);
						session.update(itemsList.get(i));
						session.getTransaction().commit();
						break;
					} else if (type.equals("type_update")) {
						itemsList.get(i).setType(info);
						session.update(itemsList.get(i));
						session.getTransaction().commit();
						break;
					} else if (type.equals("price_update")) {
						itemsList.get(i).setPrice(Double.parseDouble(info));
						session.update(itemsList.get(i));
						session.getTransaction().commit();
						break;
					} else if (type.equals("image_update")) {
						itemsList.get(i).setPicture(info);
						session.update(itemsList.get(i));
						session.getTransaction().commit();
						break;
					}
				}
			}
			List<ShoppingCart> a = new ArrayList<ShoppingCart>();
			for (int i = 0; i < itemsList.size(); i++) {
				itemsList.get(i).setCartList(a);
			}
			client.sendToClient(new Message("#opencatalog","1NetworkMarketingWorker" , itemsList));
			session.flush();
			session.close();
		}
		else if (msgString.equals("#delete item")) {
			Message msgupdate = ((Message) msg);
			Long item_id = ((Long) msgupdate.getObject());
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Item> itemsList = getAll(Item.class);
			for (int i = 0; i < itemsList.size(); i++) {
				if (itemsList.get(i).getId().equals(item_id)) {
					session.delete(itemsList.get(i));
					session.getTransaction().commit();
					break;
				}
			}
			session.close();
			session = sessionFactory.openSession();
			session.beginTransaction();
			itemsList = getAll(Item.class);
			List<ShoppingCart> a = new ArrayList<ShoppingCart>();
			for (int i = 0; i < itemsList.size(); i++) {
				itemsList.get(i).setCartList(a);
			}
			client.sendToClient(new Message("#opencatalog","1NetworkMarketingWorker" , itemsList));
			session.close();
		}
		else if (msgString.equals("#add new item")) {
			Item new_item = new Item();
			Message msg_add = ((Message) msg);
			new_item = (Item) msg_add.getObject();
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(new_item);
			session.flush();
			session.getTransaction().commit();
			List<Item> itemList = getAll(Item.class);
			client.sendToClient(new Message("#opencatalog", "NetworkMarketingWorker", itemList));
			session.close();
		}
		else if (msgString.startsWith("#show all items")) {

			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Item> itemList = getAll(Item.class);
			List<ShoppingCart> a = new ArrayList<ShoppingCart>();
			for (int i = 0; i < itemList.size(); i++) {
				itemList.get(i).setCartList(a);
			}
			client.sendToClient(new Message("#opencatalog", "1NetworkMarketingWorker",itemList));
			session.close();
		}
		else if (msgString.startsWith("#apply discount")) {
			Message msgdiscount = ((Message) msg);
			double discount = ((double) msgdiscount.getObject()) / 100;
			if (discount <= 0 || discount >= 1) {
				Warning newWarning = new Warning("PLEASE enter a number between 1 and 100");
				client.sendToClient(new Message("wrong discount", newWarning));
				return;
			}
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Item> itemsList = getAll(Item.class);
			List<Registration>registrations=getAll(Registration.class);
			double new_price;
			List<ShoppingCart> cart = new ArrayList<ShoppingCart>();
			for (int i = 0; i < itemsList.size(); i++) {
				new_price = (itemsList.get(i).getPrice()) * (1 - discount);
				itemsList.get(i).setPrice(new_price);
				itemsList.get(i).setCartList(cart);
				session.update(itemsList.get(i));
			}
			session.getTransaction().commit();

			List<Thread> threadList= new ArrayList<Thread>();
			for(int i=0;i<registrations.size();i++){
				if(registrations.get(i).getStatus().equals("Client")){
					Thread thread=new Thread(new MyThread(registrations.get(i).getEmail(),"SALE!","Our dear Customer we would like to notify you that we have a "+((int)(discount*100))+"% on everything"));
					threadList.add(thread);
				}
			}
			for(int i=0;i<threadList.size();i++){
				threadList.get(i).start();
			}


			//client.sendToClient(new Message("#opencatalog","NetworkMarketingWorker",itemsList,"Welcome to our site we have "+ Double.toString(discount*100)+" on everything"));
			session.close();

		}
		else if (msgString.equals("#delete discount")) {
			Message msgdelete_discount = ((Message) msg);
			double discount = ((double) msgdelete_discount.getObject()) / 100;
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Item> itemsList = getAll(Item.class);
			double new_price;
			for (int i = 0; i < itemsList.size(); i++) {
				new_price = (itemsList.get(i).getPrice()) * (1 / (1 - discount));
				itemsList.get(i).setPrice(new_price);
				session.update(itemsList.get(i));
			}
			session.getTransaction().commit();
			session.flush();
			session.close();
		}
		else if (msgString.startsWith("#block account")) {
			Message msgblock = ((Message) msg);
			String username = ((String) msgblock.getObject());
			session = sessionFactory.openSession();
			session.beginTransaction();
			int flag = 0;
			List<Registration> usernameList = getAll(Registration.class);
			for (int i = 0; i < usernameList.size(); i++) {
				if (usernameList.get(i).getUserName().equals(username)) {
					if (usernameList.get(i).getStatus().equals("Client")) {
						flag = 1;
						usernameList.get(i).setStatus("blocked client");
						session.save(usernameList.get(i));
						break;
					}
				}

			}
			session.getTransaction().commit();
			//session.flush();
			session.close();

			if (flag == 0) {
				Warning newWarning = new Warning("You have entered a wrong username");
				client.sendToClient(new Message("wrong username", newWarning));
			}

		}
		else if (msgString.startsWith("#unblock account")) {
			Message msgunblock = ((Message) msg);
			String username = ((String) msgunblock.getObject());
			session = sessionFactory.openSession();
			session.beginTransaction();
			int flag = 0;
			List<Registration> usernameList = getAll(Registration.class);
			for (int i = 0; i < usernameList.size(); i++) {
				if (usernameList.get(i).getUserName().equals(username)) {
					if (usernameList.get(i).getStatus().equals("blocked client")) {
						flag = 1;
						usernameList.get(i).setStatus("Client");
						session.save(usernameList.get(i));
						break;
					}
				}

			}
			session.getTransaction().commit();
			//session.flush();
			session.close();
			if (flag == 0) {
				Warning newWarning = new Warning("You have entered a wrong username or the username is already unblocked");
				client.sendToClient(new Message("wrong username", newWarning));
			}

		}
		else if (msgString.equals("#send message")) {
			Message msgclient = ((Message) msg);
			String username = ((String) msgclient.getObject());
			String client_msg = ((String) msgclient.getObject2());
			session = sessionFactory.openSession();
			session.beginTransaction();
			int flag = 0;
			List<Registration> usernameList = getAll(Registration.class);
			for (int i = 0; i < usernameList.size(); i++) {
				if (usernameList.get(i).getUserName().equals(username) && (usernameList.get(i).getStatus().equals("Client"))) {
					SystemManagers_Messages message = new SystemManagers_Messages(client_msg, username);
					session.save(message);
					flag = 1;
					break;
				}
			}


			if (flag == 0) {
				Warning newWarning = new Warning("You have entered a wrong username");
				client.sendToClient(new Message("wrong username", newWarning));
			}


			session.getTransaction().commit();
			session.close();

		}
		else if (msgString.equals("#update worker type")) {
			Message msgstatus = ((Message) msg);
			String username = ((String) msgstatus.getObject());
			String worker_status = ((String) msgstatus.getObject2());
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Registration> usernameList = getAll(Registration.class);
			int i = 0, flag = 0;
			for (i = 0; i < usernameList.size(); i++) {
				if (usernameList.get(i).getUserName().equals(username)) {
					if (!(usernameList.get(i).getStatus().equals("Client"))) {
						flag = 1;
						usernameList.get(i).setStatus(worker_status);
						session.save(usernameList.get(i));
						break;
					}
				}
			}
			session.flush();
			session.getTransaction().commit();
			session.close();

			if (flag == 0) {
				Warning newWarning = new Warning("You have entered a wrong username");
				client.sendToClient(new Message("wrong username", newWarning));
			}
		}
		else if (msgString.equals("#send clients/workers list")) {
			Message msglist = ((Message) msg);
			String type = (String) msglist.getObject();
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Registration> list = getAll(Registration.class);
			client.sendToClient(new Message("list sent", type, list));
			session.close();
		}
		else if (msgString.equals("#show Report")) {
			Message msg_report = ((Message) msg);
			String Type = ((String) msg_report.getObject());
			LocalDate start = ((LocalDate) msg_report.getObject2());
			LocalDate end = ((LocalDate) msg_report.getObject3());
			String store_name = ((String) msg_report.getObject4());
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Report> report_list = getAll(Report.class);
			List<Complain> complain_list = getAll(Complain.class);
			List<Order> order_list = getAll(Order.class);
			List<Registration> registrations = getAll(Registration.class);
			if (Type.equals("Complain Report"))
				try {
					client.sendToClient(new Message("#list of report sent", Type, start, end, report_list, complain_list, store_name, registrations));
				} catch (IOException e) {
					e.printStackTrace();
				}
			else if (Type.equals("Revenue Report") || Type.equals("Orders Report")) {
				for (int j=0; j<order_list.size();j++)
				{
					List<Item> a = new ArrayList<Item>();
					List<Double> b = new ArrayList<Double>();
					for (int i = 0; i < order_list.get(j).getCart().getItems().size(); i++) {
						a.add(order_list.get(j).getCart().getItems().get(i));
						b.add(order_list.get(j).getCart().getAmount().get(i));
					}
					order_list.get(j).getCart().setItems(a);
					order_list.get(j).getCart().setAmount(b);
					List<ShoppingCart> c = new ArrayList<ShoppingCart>();
					for (int i = 0; i < order_list.get(j).getCart().getItems().size(); i++) {
						order_list.get(j).getCart().getItems().get(i).setCartList(c);
					}

				}
				client.sendToClient(new Message("#list of report sent2", Type, start, end, order_list, store_name, registrations));
			}
			session.close();
		}
		else if (msgString.equals("#show Report to compare")) {
			Message msg_report = ((Message) msg);
			String Type = ((String) msg_report.getObject());
			LocalDate first_start = ((LocalDate) msg_report.getObject2());
			LocalDate first_end = ((LocalDate) msg_report.getObject3());
			LocalDate second_start = ((LocalDate) msg_report.getObject4());
			LocalDate second_end = ((LocalDate) msg_report.getObject5());
			String store_name = ((String) msg_report.getObject6());
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Complain> complain_list = getAll(Complain.class);
			List<Order> order_list = getAll(Order.class);
			List<Registration> registrations = getAll(Registration.class);

			if (Type.equals("Complain Compare")) {
				try {
					client.sendToClient(new Message("#list of report sent to compare", Type, first_start, first_end, second_start, second_end, complain_list, store_name, registrations));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (Type.equals("Revenue Compare") || Type.equals("Orders Compare")) {
				for (int j=0; j<order_list.size();j++)
				{
					List<Item> a = new ArrayList<Item>();
					List<Double> b = new ArrayList<Double>();
					for (int i = 0; i < order_list.get(j).getCart().getItems().size(); i++) {
						a.add(order_list.get(j).getCart().getItems().get(i));
						b.add(order_list.get(j).getCart().getAmount().get(i));
					}
					order_list.get(j).getCart().setItems(a);
					order_list.get(j).getCart().setAmount(b);
					List<ShoppingCart> c = new ArrayList<ShoppingCart>();
					for (int i = 0; i < order_list.get(j).getCart().getItems().size(); i++) {
						order_list.get(j).getCart().getItems().get(i).setCartList(c);
					}

				}
				try {
					client.sendToClient(new Message("#list of report sent to compare2", order_list, first_start, first_end, second_start, second_end, Type, store_name, registrations));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			session.close();
		}
		else if(msgString.equals("#complain list")) {

			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Complain> complainList=getAll(Complain.class);

			List<Order> orderList=getAll(Order.class);
			for(int u = 0 ; u<orderList.size();u++)
			{
				Order ord = new Order();
				orderList.get(u).getCart().setOrder(ord);
				List<Item> a = new ArrayList<Item>();
				List<Double> b = new ArrayList<Double>();
				for (int i = 0; i < orderList.get(u).getCart().getItems().size(); i++) {
					a.add(orderList.get(u).getCart().getItems().get(i));
					b.add(orderList.get(u).getCart().getAmount().get(i));
				}
				orderList.get(u).getCart().setItems(a);
				orderList.get(u).getCart().setAmount(b);
				List<ShoppingCart> c = new ArrayList<ShoppingCart>();
				for(int i=0;i<orderList.get(u).getCart().getItems().size();i++)
				{
					orderList.get(u).getCart().getItems().get(i).setCartList(c);
				}
			}



			//client.sendToClient(new Message("#list of complain sent",complainList,orderList));
			client.sendToClient(new Message("#list of complain sent",complainList,orderList));
			session.close();
		}

		else if (msgString.equals("#client complain")) {
			Message msg_complain = ((Message) msg);
			String username = ((String) msg_complain.getObject());
			String message = ((String) msg_complain.getObject2());
			String type = ((String) msg_complain.getObject3());
			String order_id = ((String) msg_complain.getObject4());
			LocalTime time = ((LocalTime) msg_complain.getObject5());
			LocalDate date = ((LocalDate) msg_complain.getObject6());
			session = sessionFactory.openSession();
			session.beginTransaction();
			int flag = 0;
			List<Order> orderList = getAll(Order.class);
			if (order_id.equals("")) {
				Complain new_complain = new Complain(username, date, time, "new complain", message, type);
				session.save(new_complain);
			} else {
				long orderID = Long.parseLong(order_id);
				for (int i = 0; i < orderList.size(); i++) {
					if(orderList.get(i).getId().equals(orderID) && orderList.get(i).getClientname().equals(username)) {
						flag = 1;
					Complain new_complain = new Complain(username, date, time, "new complain", message, type, orderID);
					session.save(new_complain);
					}
				}
				if(flag==0) {
					Warning newWarning = new Warning("There is no such order");
					client.sendToClient(new Message("wrong discount", newWarning));
					return;
				}
			}
			session.flush();
			session.getTransaction().commit();
			session.close();
		}
		else if(msgString.equals("#send order")) {
			Message msg_order = ((Message) msg);
			Long id=(Long) msg_order.getObject();
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Order> orderList=getAll(Order.class);
			if(orderList.size()==0)
				System.out.println("msh zabt");
			for(int i=0;i<orderList.size();i++)
			{
				if(orderList.get(i).getId().equals(id))
				{
					client.sendToClient(new Message("#order sent",orderList.get(i)));
					break;
				}
			}
			session.getTransaction().commit();
			session.close();

		}
		else if (msgString.equals("#complain finished")) {
			Complain complain=new Complain();
			Message msg_complain1 = ((Message) msg);
			complain  = ((Complain) msg_complain1.getObject());
			Registration User =null;
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Registration> registrations = getAll(Registration.class);
			List<Complain> complainList=getAll(Complain.class);
			for(int i=0;i<registrations.size();i++)
			{
				if(registrations.get(i).getUserName().equals(complain.getUsername()))
				{
					User=registrations.get(i);
				}
			}
			for(int i=0;i<complainList.size();i++)
			{ /*we should delete this from data base after we contact the customer*/
				if(complainList.get(i).getId().equals(complain.getId())){
					complainList.get(i).setAnswer(complain.getAnswer());
					complainList.get(i).setRefund(complain.getRefund());
					complainList.get(i).setStatus("completed");
					session.update(complainList.get(i));
					break;
				}
			}
			User.setRefund(User.getRefund()+complain.getRefund());
			session.update(User);
			session.getTransaction().commit();
			List<Order> orderList=getAll(Order.class);
			for(int u = 0 ; u<orderList.size();u++)
			{
				Order ord = new Order();
				orderList.get(u).getCart().setOrder(ord);
				List<Item> a = new ArrayList<Item>();
				List<Double> b = new ArrayList<Double>();
				for (int i = 0; i < orderList.get(u).getCart().getItems().size(); i++) {
					a.add(orderList.get(u).getCart().getItems().get(i));
					b.add(orderList.get(u).getCart().getAmount().get(i));
				}
				orderList.get(u).getCart().setItems(a);
				orderList.get(u).getCart().setAmount(b);
				List<ShoppingCart> c = new ArrayList<ShoppingCart>();
				for(int i=0;i<orderList.get(u).getCart().getItems().size();i++)
				{
					orderList.get(u).getCart().getItems().get(i).setCartList(c);
				}
			}
			client.sendToClient(new Message("#list of complain sent",complainList,orderList));
			session.getTransaction().commit();
			session.flush();
			session.close();
		}
		else if (msgString.equals("#OpenCancelOrder")) {
			Message msg1 = ((Message) msg);
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Order> orderList = getAll(Order.class);

			for(int u = 0 ; u<orderList.size();u++)
			{
				Order ord = new Order();
				orderList.get(u).getCart().setOrder(ord);
				List<Item> a = new ArrayList<Item>();
				List<Double> b = new ArrayList<Double>();
				for (int i = 0; i < orderList.get(u).getCart().getItems().size(); i++) {
					a.add(orderList.get(u).getCart().getItems().get(i));
					b.add(orderList.get(u).getCart().getAmount().get(i));
				}
				orderList.get(u).getCart().setItems(a);
				orderList.get(u).getCart().setAmount(b);
				List<ShoppingCart> c = new ArrayList<ShoppingCart>();
				for(int i=0;i<orderList.get(u).getCart().getItems().size();i++)
				{
					orderList.get(u).getCart().getItems().get(i).setCartList(c);
				}
			}
			client.sendToClient(new Message("#MyOrdersList", orderList));

		}
		else if (msgString.equals("#CancelOrder")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Message msg1 = ((Message) msg);
			Order order = (Order) msg1.getObject();
			order.setStatus("Canceled");
			Registration User = (Registration) msg1.getObject2();
			String CurrUserID = order.getClientid();
			List<Registration> regList = getAll(Registration.class);
			for (Registration buyer : regList) {
				if (buyer.getClient_ID().equalsIgnoreCase(CurrUserID)) {
					String Date = order.getRecievedate();
					String Time = order.getRecievetime();
					RefundCheck time = new RefundCheck();
					int temp = time.Refund(Date, Time);
					Confirmation Respond;

					if (temp == 3) {
						Respond = new Confirmation("accordingly to our refunding policy" +
								" , you will get a full refund of your order that is : "
								+ order.getTotalprice());
						buyer.setRefund(buyer.getRefund()+Double.parseDouble(order.getTotalprice()));

						//refund accordingly to 3 hours or more before getting the package
					} else if (temp == 1) {
						Respond = new Confirmation("accordingly to our refunding policy ," +
								" you will get a 50% refund of your order that is : "
								+ (Double.parseDouble(order.getTotalprice()) / 2) );
						//refund accordingly to 3 hours or more before getting the package
						buyer.setRefund(buyer.getRefund()+Double.parseDouble(order.getTotalprice()) / 2);
					} else {
						Respond = new Confirmation("accordingly to our refund policy , you wont get a refund");
					}
					session.update(buyer);
					session.update(order);
					session.flush();
					session.getTransaction().commit();

					String t = "Dear Mr/Mrs : " + order.getClientname() + '\n'
							+  " Order with ID " + order.getId() + " have been canceled successfully";
					try {
						client.sendToClient(new Message("#OrderCanceled", Respond));
						Thread thread=new Thread(new MyThread("saherdaoud2000@windowslive.com","Order Canceled",t));
						thread.start();
					} catch (IOException e) {

					}

					//Send Email With the information of the respond *_*
					return;

//						}
//					}

				}
			}
			session.close();
		}
//		else if (msgString.equals("#OpenCancelOrder")) {
//			Message msg1 = ((Message) msg);
////			Registration client2 = (Registration) msg1.getObject();
////			String CurrUser = client2.getClient_ID();
//			session = sessionFactory.openSession();
//			session.beginTransaction();
//			List<Order> orderList = getAll(Order.class);
//			for(int u = 0 ; u<orderList.size();u++)
//			{
//				Order ord = new Order();
//				orderList.get(u).getCart().setOrder(ord);
//				List<Item> a = new ArrayList<Item>();
//				List<Double> b = new ArrayList<Double>();
//				for (int i = 0; i < orderList.get(u).getCart().getItems().size(); i++) {
//					a.add(orderList.get(u).getCart().getItems().get(i));
//					b.add(orderList.get(u).getCart().getAmount().get(i));
//				}
//				orderList.get(u).getCart().setItems(a);
//				orderList.get(u).getCart().setAmount(b);
//				List<ShoppingCart> c = new ArrayList<ShoppingCart>();
//				for(int i=0;i<orderList.get(u).getCart().getItems().size();i++)
//				{
//					orderList.get(u).getCart().getItems().get(i).setCartList(c);
//				}
//			}
//			client.sendToClient(new Message("#MyOrdersList", orderList));
//
//		} else if (msgString.equals("#CancelOrder")) {
//			session = sessionFactory.openSession();
//			session.beginTransaction();
//			Message msg1 = ((Message) msg);
//			Order order = (Order) msg1.getObject();
//			order.setStatus("Canceled");
//			Registration User = (Registration) msg1.getObject2();
//			String CurrUserID = order.getClientid();
//
//
//			List<Registration> regList = getAll(Registration.class);
//			for (Registration buyer : regList) {
//				if (buyer.getClient_ID().equalsIgnoreCase(CurrUserID)) {

//					String Date = order.getRecievedate();
//					String Time = order.getRecievetime();
//					RefundCheck time = new RefundCheck();
//					int temp = time.Refund(Date, Time);
//					String RefundRespond = String.valueOf(User.getCreditCard());
//					RefundRespond = RefundRespond.substring(RefundRespond.length() - 4);
////                    Confirmation Respond;
//					Confirmation Respond;
//
//					if (temp == 3) {
//						Respond = new Confirmation("accordingly to our refunding policy" +
//								" , you will get a full refund of your order that is : "
//								+ order.getTotalprice() +
//								" to your Credit Card that ends with the digits : ***" + RefundRespond);
//						buyer.setRefund(Double.parseDouble(order.getTotalprice()));
//
//						//refund accordingly to 3 hours or more before getting the package
//					} else if (temp == 1) {
//						Respond = new Confirmation("accordingly to our refunding policy ," +
//								" you will get a 50% refund of your order that is : "
//								+ (Double.parseDouble(order.getTotalprice()) / 2) +
//								"to your Credit Card that ends with the digits :"
//								+ RefundRespond);
//						//refund accordingly to 3 hours or more before getting the package
//						buyer.setRefund(Double.parseDouble(order.getTotalprice()) / 2);
//					} else {
//						Respond = new Confirmation("accordingly to our refund policy , you wont get a refund");
//					}
//
//					session.update(buyer);
//					session.update(order);
//					session.flush();
//					session.getTransaction().commit();
//
//
//					try {
//						client.sendToClient(new Message("#OrderCanceled", Respond));
//					} catch (IOException e) {
//
//					}
//
//					//Send Email With the information of the respond *_*
//					return;
//
////						}
////					}
//
//				}
//			}
//		}
		else if(msgString.equals("#LogOut")) {
			session = sessionFactory.openSession();
			Message msg1 = ((Message) msg);
			Registration CurrUser = (Registration) msg1.getObject();
			String CurrUserID = CurrUser.getClient_ID();
			try{
				List<Registration> regList = getAll(Registration.class);
				for (Registration buyer : regList){
					if(buyer.getClient_ID().equalsIgnoreCase(CurrUserID))
					{
						buyer.setRegistered(false);
						session.beginTransaction();
						session.update(buyer);
						session.flush();
						session.getTransaction().commit();
						return;
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(msgString.equals("#send complain list2")) {
			Complain complain=new Complain();
			Message msg_complain1 = ((Message) msg);
			String type_profile=((String) msg_complain1.getObject());
			complain  = ((Complain) msg_complain1.getObject2());
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Complain> complainList=getAll(Complain.class);
			client.sendToClient(new Message("#list of complain sent2", complainList,type_profile));
			session.close();

		}
		else if(msgString.equals("#send order list")) {
			Message msg_order1 = ((Message) msg);
			String type_profile=((String) msg_order1.getObject());
			String username =((String) msg_order1.getObject2());
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Order> orderList=getAll(Order.class);
			List<ShoppingCart> shoppingCartList=getAll(ShoppingCart.class);
//			orderList.get(0).setCart(shoppingCartList.get(0));
//			orderList.get(0).getCart().setAmount(shoppingCartList.get(0).getAmount());
//			orderList.get(0).getCart().gettotalPrice(shoppingCartList.get(0));
//			session.update(orderList.get(0));

			for(int u = 0 ; u<orderList.size();u++)
			{
				Order ord = new Order();
				orderList.get(u).getCart().setOrder(ord);
				List<Item> a = new ArrayList<Item>();
				List<Double> b = new ArrayList<Double>();
				for (int i = 0; i < orderList.get(u).getCart().getItems().size(); i++) {
					a.add(orderList.get(u).getCart().getItems().get(i));
					b.add(orderList.get(u).getCart().getAmount().get(i));
				}
				orderList.get(u).getCart().setItems(a);
				orderList.get(u).getCart().setAmount(b);
				List<ShoppingCart> c = new ArrayList<ShoppingCart>();
				for(int i=0;i<orderList.get(u).getCart().getItems().size();i++)
				{
					orderList.get(u).getCart().getItems().get(i).setCartList(c);
				}
			}
			List<Order> orderList1 = new ArrayList<Order>();
			for(int i=0;i<orderList.size();i++)
			{
				if(orderList.get(i).getClientname().equals(username))
				{
					orderList1.add(orderList.get(i));
				}
			}
			client.sendToClient(new Message("#list of order sent", orderList1,type_profile));

			session.close();

		}
		else if(msgString.equals("#send message list")){
			Message msg_messages = ((Message) msg);
			String type_profile=((String) msg_messages.getObject());
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<SystemManagers_Messages> messagesList=getAll(SystemManagers_Messages.class);
			client.sendToClient(new Message("#list of message sent", messagesList,type_profile));
			session.close();
		}

//		session = sessionFactory.openSession();
//		session.beginTransaction();
//		List<Order> order_list=getAll(Order.class);
//		for(Order orders : order_list)
//		{
//			String DelDate = orders.getRecievedate();
//			String DelTime = orders.getRecievetime();
//
//			RefundCheck time = new RefundCheck();
//			int temp = time.Delivery(DelDate, DelTime);
//			if(temp == 1 && orders.getStatus().equalsIgnoreCase("pending"))
//			{
//				orders.setStatus("Delivered");
//				session.update(orders);
//				session.getTransaction().commit();
//				String f = "Your order have been delivered successfully, order number: " + orders.getId();
//				Thread thread = new Thread(new MyThread(orders.getClientmail(), "Order Delivered", f));
//				thread.start();
//			}
//			session.flush();



	}
	private static SessionFactory getSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
		//configuration.addAnnotatedClass(Catalog.class);
		configuration.addAnnotatedClass(Item.class);
		configuration.addAnnotatedClass((Message.class));
		configuration.addAnnotatedClass((Registration.class));
		configuration.addAnnotatedClass((SystemManagers_Messages.class));
		configuration.addAnnotatedClass((Report.class));
		configuration.addAnnotatedClass((ShoppingCart.class));
		configuration.addAnnotatedClass((Order.class));
		configuration.addAnnotatedClass((Complain.class));
		configuration.addAnnotatedClass((Confirmation.class));
		configuration.addAnnotatedClass((FirstTime.class));
//		configuration.addAnnotatedClass((Image.class));


		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
	private static void initializeData()
	{
		//Catalog temp =new Catalog();
//		Order order2 = new Order();
//		session.save(order2);
		// Rose Plant flower Bouquet
		Item it1 =new Item("Spray carnations","flower","Pink","il/cshaifasweng/OCSFMediatorExample/client/images/Spray carnations.jpg"  ,50);
		Item it2 =new Item("Delphinium" , "Plant" , "Blue" , "il/cshaifasweng/OCSFMediatorExample/client/images/Delphinium.jpg"  ,20);
		Item it3 =new Item("Zamia Coconut L" , "Plant" , "Green" ,  "il/cshaifasweng/OCSFMediatorExample/client/images/Zamia Coconut L.jpg",50);
		Item it4 =new Item("Sensivaria medium" , "Plant" , "Green" , "il/cshaifasweng/OCSFMediatorExample/client/images/Sensivaria medium.jpg" , 35);
		Item it5 =new Item("Bridal bouquet" , "Bouquet" , "White" , "il/cshaifasweng/OCSFMediatorExample/client/images/Bridal bouquet.jpg" , 200);
		Item it6 =new Item("Blue Roses" , "Rose" , "Blue" ,"il/cshaifasweng/OCSFMediatorExample/client/images/Blue Roses.jpg" ,  80);
		Item it7 =new Item("Red Roses" , "Rose" , "Red" ,"il/cshaifasweng/OCSFMediatorExample/client/images/Red Roses.jpg"  , 90);
		Item it8 =new Item("Single Rose" , "Rose" , "Red" , "il/cshaifasweng/OCSFMediatorExample/client/images/Single Rose.jpg",  10);
		Item it9 =new Item("Posy Bouquet" , "Bouquet" , "Pink and White" , "il/cshaifasweng/OCSFMediatorExample/client/images/Posy Bouquet.jpg" , 70);
		Item it10 =new Item("Basket Bouquet" , "Bouquet" , "Pink and White" ,"il/cshaifasweng/OCSFMediatorExample/client/images/Basket Bouquet.jpg"  ,80 );
		Item it11 =new Item("Fan Bouquet" , "Bouquet" , "White" ,"il/cshaifasweng/OCSFMediatorExample/client/images/Fan Bouquet.jpg"  ,55 );
		Item it12 =new Item("Fiesta Bouquet" , "Bouquet" , "Red" , "il/cshaifasweng/OCSFMediatorExample/client/images/Fiesta Bouquet.jpg" ,110 );
		Item it13 =new Item("Peony Bouquet" , "Bouquet" , "Pink and White" , "il/cshaifasweng/OCSFMediatorExample/client/images/Peony Bouquet.jpg" , 80);
		Item it14 =new Item("Hello Sunshine" , "flower" , "Yellow" , "il/cshaifasweng/OCSFMediatorExample/client/images/Hello Sunshine.jpg" ,80 );
		Item it15 =new Item("Rainbow Roses" , "Rose" , "Rainbow" ,"il/cshaifasweng/OCSFMediatorExample/client/images/Rainbow Roses.jpg"  , 200);
		Item it16 =new Item("Yellow Roses" , "Rose" , "Yellow" , "il/cshaifasweng/OCSFMediatorExample/client/images/Yellow Roses.jpg" ,70 );
		Item it17 =new Item("White Roses" , "Rose" , "White" , "il/cshaifasweng/OCSFMediatorExample/client/images/White Roses.jpg" , 80);
		Item it18 =new Item("SunFlower Bouquet" , "Bouquet" , "Yellow" , "il/cshaifasweng/OCSFMediatorExample/client/images/SunFlower Bouquet.jpg" ,  90);
		Item it19 =new Item("Friendship Bouquet" , "Bouquet" , "White" , "il/cshaifasweng/OCSFMediatorExample/client/images/Friendship Bouquet.jpg" ,90 );
		Item it20 =new Item("Plant" , "Plant" , "Green" ,"il/cshaifasweng/OCSFMediatorExample/client/images/Plant.jpg"  , 30);
		Registration client1 = new Registration("Kareen", "Ghattas", "123456789", "kareenghattas1999@gmail.com", "0505123456", "client1", "1234", "Client", "2233445566", "1/1/2023", "Store Account",0,"Lelach, Haifa" );
		Registration client2 = new Registration("Natalie", "Nakkara", "234789456", "natalienk2000@gmail.com", "0524789000", "client2", "1234", "Client", "1234561299", "5/8/2024", "Chain Account",0,"Lelach, Tel Aviv" );
		Registration client3 = new Registration("Natal", "Nakka", "234789776", "saherdaoud2000@windowslive.com", "0524789000", "client3", "1234", "blocked client", "1234561299", "5/8/2024", "One year subscription",0,"Lelach, Tel Aviv");
		Registration CEO = new Registration("Rashil", "Mbariky", "4443336661", "", "", "Rashi", "rashi", "CEO", "", "", "",0,"");
		Registration NetworkMarketingWorker = new Registration("Eissa", "Wahesh", "111456789", "", "", "Eissa", "11111", "NetworkMarketingWorker", "", "", "",0,"");
		Registration customerservice=new Registration("nunu","nunu","","222456789","","nunu","nunu","CustomerService","","","",0,"");
		Registration SystemManger = new Registration("Elias", "Dow", "", "333356789", "", "Elisa", "lampon", "SystemManager", "", "", "",0,"");
		Registration BranchManger = new Registration("Saher", "Daoud", "", "425555589", "", "Saher", "123456", "BranchManager", "", "", "",0,"Lelach, Haifa");
		Registration BranchManger2 = new Registration("saher5", "Daoud", "", "222426789", "", "Saher1", "123456", "BranchManager", "", "", "",0,"Lelach, Tel Aviv");

		//session.save(temp);
		session.save(it1);
		session.save(it2);
		session.save(it3);
		session.save(it4);
		session.save(it5);
		session.save(it6);
		session.save(it7);
		session.save(it8);
		session.save(it9);
		session.save(it10);
		session.save(it11);
		session.save(it12);
		session.save(it13);
		session.save(it14);
		session.save(it15);
		session.save(it16);
		session.save(it17);
		session.save(it18);
		session.save(it19);
		session.save(it20);
		session.save(client1);
		session.save(client2);
		session.save(client3);
		session.save(CEO);
		session.save(NetworkMarketingWorker);
		session.save(SystemManger);
		session.save(BranchManger);
		session.save(BranchManger2);
		session.save(customerservice);
//		temp.addIteam(it1);
//		temp.addIteam(it2);
//		temp.addIteam(it3);
//		temp.addIteam(it4);
//		temp.addIteam(it5);
//		temp.addIteam(it6);
//		temp.addIteam(it7);
//		temp.addIteam(it8);
//		temp.addIteam(it9);
//		temp.addIteam(it10);
//		temp.addIteam(it11);
//		temp.addIteam(it12);
//		temp.addIteam(it13);
//		temp.addIteam(it14);
//		temp.addIteam(it15);
//		temp.addIteam(it16);
//		temp.addIteam(it17);
//		temp.addIteam(it18);
//		temp.addIteam(it19);
//		temp.addIteam(it20);
		session.flush();
	}

	public void connectData() {
		try {
			SessionFactory sessionFactory = getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			initializeData();
			session.close();
		} catch (Exception exception) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			System.err.println("An error occured, changes have been rolled back.");
			exception.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
				session.getSessionFactory().close();
			}
		}
	}
	public static <T> List<T> getAll(Class<T> object) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
		Root<T> rootEntry = criteriaQuery.from(object);
		CriteriaQuery<T> allCriteriaQuery = criteriaQuery.select(rootEntry);

		TypedQuery<T> allQuery = session.createQuery(allCriteriaQuery);
		return allQuery.getResultList();
	}


}