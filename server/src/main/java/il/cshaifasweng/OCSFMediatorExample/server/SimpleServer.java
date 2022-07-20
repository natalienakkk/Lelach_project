package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.Helpers.RefundCheck;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
 import il.cshaifasweng.OCSFMediatorExample.entities.ShoppingCart;

//import javax.imageio.spi.ServiceRegistry;
import javax.persistence.*;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;



import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.build.AllowSysOut;
import org.hibernate.service.ServiceRegistry;




public class SimpleServer extends AbstractServer {
	private static Session session;
	private static SessionFactory sessionFactory = getSessionFactory();
	int firsttime =0;
	ShoppingCart cart1;
	public SimpleServer(int port) {
		super(port);

	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) throws IOException {

		String msgString = msg.toString();
		if(msgString.equals("#opencatalog"))
		{
			Message msg3 = ((Message) msg);
			String user_type = (String) msg3.getObject();
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Item> itemList=getAll(Item.class);
			client.sendToClient(new Message("#opencatalog" , user_type , itemList));
			session.close();
		}
		else if(msgString.startsWith("#submitorder")) {
			System.out.println("hey2");
			session = sessionFactory.openSession();
			session.beginTransaction();
			Message msg1 = ((Message) msg);
			Order order = (Order) msg1.getObject();
			ShoppingCart cart = (ShoppingCart) msg1.getObject2();
			//order.setCart(cart);
			session.save(order);
			System.out.println("3030303");
			client.sendToClient(new Message("#submitorder", order , cart));
			System.out.println("303022222");
			session.close();
			System.out.println("saherrrrrrrrrrrrrrrr");
		}
		else if(msgString.startsWith("#openuseritem"))
		{
			Message msg2 = ((Message) msg);
			int flowerid = (int) msg2.getObject();
			session = sessionFactory.openSession();
			List<Item> Itemlist=getAll(Item.class);
			for(int i=0 ; i < Itemlist.size() ; i++)
			{
				if(Itemlist.get(i).getId() == flowerid)
				{
					client.sendToClient(new Message("#openuseritem" , Itemlist.get(i)));
					break;
				}
			}
			session.close();
		}

		else if(msgString.startsWith("#addtocart"))
		{
			Message msg1 = ((Message) msg);
			Item item = (Item) msg1.getObject();
			double amount = (double) msg1.getObject2();
			session = sessionFactory.openSession();
			session.beginTransaction();
			//ShoppingCart cart1 = (ShoppingCart) msg1.getObject3();
			if(firsttime == 0 ) {
				cart1 = new ShoppingCart();
				session.save(cart1);
				firsttime++;
				System.out.println(item.getName() + " a");
			}
			else
			{
				System.out.println(item.getName() + " b");
			}

			if(cart1 == null) System.out.println("null!!\n");
			cart1.AddtoCart(item);
			cart1.Addamount(amount);

			List<ShoppingCart> a = getAll(ShoppingCart.class);

			System.out.println(cart1.getItems().size());
			session.save(cart1);
			session.getTransaction().commit();
			session.close();

		}
		else if(msgString.startsWith("#getcart"))
		{
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<ShoppingCart> a = getAll(ShoppingCart.class);
			List<Item> b = new ArrayList<Item>();
			List<Double> c = new ArrayList<Double>();
			for(int j = 0; j<a.get(a.size()-1).getItems().size() ; j++)
			{
				b.add(a.get(a.size()-1).getItems().get(j));
				c.add(a.get(a.size()-1).getAmount().get(j));
			}
			client.sendToClient(new Message("#getcart",b ,c));
			session.close();

		}
		else if (msgString.startsWith("#warning")) {
			Warning warning = new Warning("Warning from server!");
			try {
				client.sendToClient(warning);
				System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#SignUpRequest")) {
			//Registration newSignUp = new Registration();
			Message msg1 = ((Message) msg);
			Registration newSignUp = (Registration) msg1.getObject();
			String ID1 = newSignUp.getClient_ID(); //(String) msg1.getObject();
			session = sessionFactory.openSession();
			try {
				List<Registration> clients = getAll(Registration.class);
				for (Registration registration : clients) {
					if (registration.getClient_ID().equalsIgnoreCase(ID1)) {
//                        System.out.print("foundddddddddddd\n");
//                   registration.setRegistered(true);
						Warning new_warning = new Warning("Dear Client,you are already Signed up.\n Please go to Login.");
						client.sendToClient(new Message("#SignUpWarning", new_warning));
						return;
					} else {
						session.beginTransaction();
						session.save(newSignUp);
						session.flush();
						session.getTransaction().commit();
						////////////////////////////////////////////////////
						Confirmation newWarning = new Confirmation("Dear " + newSignUp.getUserName() + " welcome to Lilach. you have been signed up successfully");
						client.sendToClient(new Message("#MemberSignedUpSucces", newWarning));
						return;
					}
				}

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
			String UserName = newLogin.getUserName();
			System.out.println(UserName);
			String Password = newLogin.getPassword();
			int UserNameFound = -1;
			try {
				List<Registration> clients = getAll(Registration.class);
				for (Registration registration : clients) {
//					System.out.println("akm mra bfot");
//					System.out.println(registration.getUserName());
//					System.out.println(UserName);
					if (registration.getUserName().equalsIgnoreCase(UserName)) {
						UserNameFound = 1;
						if (registration.getPassword().equalsIgnoreCase(Password)) {
							if (registration.getStatus().equalsIgnoreCase("blocked client")) {
								Warning new_warning = new Warning("You're account have been blocked. Please contact customer service");
								client.sendToClient(new Message("#BlockedAccount", new_warning));
								return;
							}
//							else if (registration.getRegistered().equals(true)) {
//								Warning newWanrning = new Warning("You're already logged in from another computer");
//								client.sendToClient(new Message("#LoginWarning", newWanrning));
//								return;
//							}
							else {
//								Registration client2 = (Registration) msg1.getObject();
//								client2.getClient_ID();
//
//								Hibernate.initialize(registration.getPurchases());
//								List<Purchase> tempList = registration.getPurchases();

//
//								System.out.println(registration.getUserName() + " " + registration.getPassword() + " /n" + registration.getPurchases().size() + " "
//								+ registration.getClient_ID() + " " + registration.getAccountType() + " " + registration.getCreditCard() + " " +
//										registration.getEmail() + " " + registration.getExpiryDate() + " " + registration.getFirstName() + " "
//								+ registration.getLastName()  + " " + registration.getPhoneNumber() + " " + registration.getStatus());

//								Purchase tmp = new Purchase(registration,23,"asd",1516);
//								tempList.add(tmp);
//								System.out.println(tempList.size() + "" );
//								for (Purchase purchase : tempList)
//									System.out.println(purchase.getCard() + " " + "temp list is alright !");
//								client.sendToClient(new Message("#LogInSucess", registration , itemList , tempList));
								registration.setRegistered(true);
								client.sendToClient(new Message("#LogInSucess", registration, itemList));

								System.out.println("sent to client successfully");
//								session.close(); ///////
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
//					else{
//						System.out.println("Username wronggggggggg");
//						Warning new_warning = new Warning("You have entered invalid input. Please try again ");
//						client.sendToClient(new Message("#LoginWarning", new_warning));
//
//					}
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
		else if(msgString.equals("#update flower"))
		{
			Message msgupdate = ((Message) msg);
			Long flower_id=((Long) msgupdate.getObject());
			String type =((String) msgupdate.getObject2());
			String info=((String) msgupdate.getObject3());
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Item> itemsList = getAll(Item.class);
			for (int i = 0; i < itemsList.size(); i++){
				if(itemsList.get(i).getId().equals(flower_id)) {
					if (type.equals("color_update")) {
						itemsList.get(i).setColor(info);
						session.save(itemsList.get(i));
						client.sendToClient(new Message("#information updated",itemsList.get(i)));
						break;
					}
					else if(type.equals("name_update"))
					{
						itemsList.get(i).setName(info);
						session.save(itemsList.get(i));
						client.sendToClient(new Message("#information updated",itemsList.get(i)));
						break;
					}
					else if(type.equals("type_update"))
					{
						itemsList.get(i).setType(info);
						session.save(itemsList.get(i));
						client.sendToClient(new Message("#information updated",itemsList.get(i)));
						break;
					}
					else if(type.equals("price_update"))
					{
						itemsList.get(i).setPrice(Double.parseDouble(info));
						session.save(itemsList.get(i));
						client.sendToClient(new Message("#information updated",itemsList.get(i)));
						break;
					}
				}
			}
			session.getTransaction().commit();
			session.flush();
			session.close();
		}
		else if(msgString.equals("#delete item"))
		{
			Message msgupdate = ((Message) msg);
			Long item_id=((Long) msgupdate.getObject());
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Item> itemsList = getAll(Item.class);
			for (int i = 0; i < itemsList.size(); i++){
				if(itemsList.get(i).getId().equals(item_id)) {
					session.delete(itemsList.get(i));
					System.out.println(i);
					break;
				}
			}
			itemsList = getAll(Item.class);
			session.getTransaction().commit();
			client.sendToClient(new Message("#item_deleted","NetworkMarketingWorker" , itemsList));
			session.close();
		}
		else if(msgString.equals("#add new item"))
		{
			Item new_item= new Item();
			Message msg_add = ((Message) msg);
			new_item=(Item) msg_add.getObject();
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(new_item);
			session.flush();
			session.getTransaction().commit();
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Item> itemList=getAll(Item.class);
			client.sendToClient(new Message("#opencatalog" ,"NetworkMarketingWorker", itemList));
			session.close();
		}
		else if (msgString.startsWith("#apply discount"))
		{
			Message msgdiscount = ((Message) msg);
			double discount = ((double) msgdiscount.getObject())/100;
			if(discount<=0 || discount>=1)
			{
				Warning newWarning = new Warning("PLEASE enter a number between 1 and 100");
				client.sendToClient(new Message("wrong discount",newWarning));
				return;
			}
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Item> itemsList = getAll(Item.class);
			double new_price;
			for (int i = 0; i < itemsList.size(); i++) {
				new_price = (itemsList.get(i).getPrice()) * (1 - discount);
				itemsList.get(i).setPrice(new_price);
				session.save(itemsList.get(i));
			}
			session.getTransaction().commit();
			session.flush();
			session.close();

		}

		else if (msgString.equals("#delete discount"))
		{
			Message msgdelete_discount = ((Message) msg);
			double discount=((double) msgdelete_discount.getObject())/100;
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Item> itemsList = getAll(Item.class);
			double new_price;
			for (int i = 0; i < itemsList.size(); i++) {
				new_price = (itemsList.get(i).getPrice()) * (1/(1 - discount));
				itemsList.get(i).setPrice(new_price);
				session.save(itemsList.get(i));
			}
			session.getTransaction().commit();
			session.flush();
			session.close();
		}
		else if(msgString.startsWith("#block account"))
		{
			Message msgblock = ((Message) msg);
			String username = ((String) msgblock.getObject());
			session = sessionFactory.openSession();
			session.beginTransaction();
			int flag=0;
			List<Registration> usernameList=getAll(Registration.class);
			for(int i=0 ; i < usernameList.size() ; i++)
			{
				if (usernameList.get(i).getUserName().equals(username))
				{
					if(usernameList.get(i).getStatus().equals("Client")) {
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

			if(flag==0)
			{
				Warning newWarning = new Warning("You have entered a wrong username");
				client.sendToClient(new Message("wrong username",newWarning));
			}

		}


		else if (msgString.equals("#send message")) {
			Message msgclient = ((Message) msg);
			String username = ((String) msgclient.getObject());
			String client_msg = ((String) msgclient.getObject2());
			session = sessionFactory.openSession();
			session.beginTransaction();
			int flag=0;
			List<Registration> usernameList = getAll(Registration.class);
			for (int i = 0; i < usernameList.size(); i++) {
				if (usernameList.get(i).getUserName().equals(username) && (usernameList.get(i).getStatus().equals("Client"))){
					flag = 1;
					break;
				}
			}
			if(flag==0)
			{
				Warning newWarning = new Warning("You have entered a wrong username");
				client.sendToClient(new Message("wrong username",newWarning));
			}
//			System.out.format(client_msg+"\n");
//			System.out.format(username);
			SystemManagers_Messages message = new SystemManagers_Messages(client_msg, username);
			//	session = sessionFactory.openSession();
			//	session.beginTransaction();
			session.save(message);
			session.flush();
			session.getTransaction().commit();
		}


		else if(msgString.equals("#update worker type"))
		{
			Message msgstatus = ((Message) msg);
			String username = ((String) msgstatus.getObject());
			String worker_status=((String)msgstatus.getObject2());
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Registration> usernameList=getAll(Registration.class);
			int i = 0,flag=0;
			for(i=0 ; i < usernameList.size() ; i++)
			{
				if(usernameList.get(i).getUserName().equals(username))
				{
					if(!(usernameList.get(i).getStatus().equals("Client"))) {
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

			if(flag==0)
			{
				Warning newWarning = new Warning("You have entered a wrong username");
				client.sendToClient(new Message("wrong username",newWarning));
			}
		}
		else if(msgString.equals("#send clients/workers list")){
			Message msglist = ((Message) msg);
			String type=(String)msglist.getObject();
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Registration> list=getAll(Registration.class);
			client.sendToClient(new Message("list sent",type,list));
			session.close();
		}
		else if(msgString.equals("#show Report"))
		{
			Message msg_report = ((Message) msg);
			String Type  = ((String) msg_report.getObject());
			LocalDate start=((LocalDate) msg_report.getObject2());
			LocalDate end=((LocalDate) msg_report.getObject3());
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Report> report_list=getAll(Report.class);
			List<Complain> complain_list=getAll(Complain.class);
			List<Order> order_list=getAll(Order.class);
			if(Type.equals("Complain Report"))
				try {
					client.sendToClient(new Message("#list of report sent",Type,start,end,report_list,complain_list));
				} catch (IOException e) {
					e.printStackTrace();
				}
			else if(Type.equals("Revenue Report") || Type.equals("Orders Report"))
			{
				client.sendToClient(new Message("#list of report sent2",Type,start,end,order_list));
			}
			session.close();
		}


		else if(msgString.equals("#show Report to compare"))
		{
			//System.out.println("natlieeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
			Message msg_report = ((Message) msg);
			String Type = ((String) msg_report.getObject());
			LocalDate first_start=((LocalDate) msg_report.getObject2());
			LocalDate first_end=((LocalDate) msg_report.getObject3());
			LocalDate second_start=((LocalDate) msg_report.getObject4());
			LocalDate second_end=((LocalDate) msg_report.getObject5());
			System.out.println("server "+Type+first_start+first_end+second_start+second_end);
			System.out.println("natalie"+Type+first_start+first_end+second_start+second_end);
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Complain> complain_list=getAll(Complain.class);
			List<Order> order_list=getAll(Order.class);
//			if(Type.equals(null))
//			{
//				Warning newWarning = new Warning("PLEASE Choose type of Report");
//				client.sendToClient(new Message("wrong type", newWarning));
//			}
			if(Type.equals("Complain Compare")) {
				try {
					client.sendToClient(new Message("#list of report sent to compare", Type, first_start, first_end, second_start, second_end, complain_list));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if(Type.equals("Revenue Compare")){
				System.out.println("revenue compare");
				try {
					client.sendToClient(new Message("#list of report sent to compare2", order_list , first_start, first_end, second_start, second_end ,Type));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			session.close();
		}
		else if(msgString.equals("#client complain"))
		{
			Message msg_complain = ((Message) msg);
			String username  = ((String) msg_complain.getObject());
			String message=((String) msg_complain.getObject2());
			String type =((String) msg_complain.getObject3());
			String order_id =((String) msg_complain.getObject4());
			LocalTime time=((LocalTime) msg_complain.getObject5());
			LocalDate date=((LocalDate) msg_complain.getObject6());
			session = sessionFactory.openSession();
			session.beginTransaction();
			if(order_id.equals("")) {
				Complain new_complain=new Complain(username,date,time,"new complain",message,type);
				session.save(new_complain);
			}
			else {
				long orderID = Long.parseLong(order_id);
				Complain new_complain = new Complain(username, date, time, "new complain", message, type,  orderID);
				session.save(new_complain);
			}
			session.flush();
			session.getTransaction().commit();
			//client.sendToClient(new Message("#"));
			session.close();
		}


		else if(msgString.equals("#complain list"))
		{

			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Complain> complainList=getAll(Complain.class);
			//List<Order> orderList=getAll(Order.class);
			//client.sendToClient(new Message("#list of complain sent",complainList,orderList));
			client.sendToClient(new Message("#list of complain sent",complainList));
			session.close();
		}
		else if(msgString.equals("#send order"))
		{
			System.out.println("markkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
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
					System.out.println("msh zabt2222222222222222222");
					client.sendToClient(new Message("#order sent",orderList.get(i)));
					break;
				}
			}
			session.getTransaction().commit();
			session.close();

		}
		else if (msgString.equals("#complain finished"))
		{
			Complain complain=new Complain();
			Message msg_complain1 = ((Message) msg);
			complain  = ((Complain) msg_complain1.getObject());
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Complain> complainList=getAll(Complain.class);
			for(int i=0;i<complainList.size();i++)
			{ /*we should delete this from data base after we contact the customer*/
				if(complainList.get(i).getId().equals(complain.getId())){
					complainList.get(i).setAnswer(complain.getAnswer());
					complainList.get(i).setRefund(complain.getRefund());
					complainList.get(i).setStatus("complete");
					session.save(complainList.get(i));
					break;
				}
			}
			session.getTransaction().commit();
			session.flush();
			session.close();
		}
		else if (msgString.equals("#delete complain")){
			Complain complain=new Complain();
			Message msg_complain1 = ((Message) msg);
			complain  = ((Complain) msg_complain1.getObject());
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Complain> complainList=getAll(Complain.class);
			for(int i=0;i<complainList.size();i++) {
				if(complainList.get(i).getId().equals(complain.getId()))
				{
					session.delete(complainList.get(i));
					break; }
			}
			session.getTransaction().commit();
			session.flush();
			session.close();
		}
		else if (msgString.equals("#OpenCancelOrder")) {
			Message msg1 = ((Message) msg);
//			Registration client2 = (Registration) msg1.getObject();
//			String CurrUser = client2.getClient_ID();
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Order> OrdersList = getAll(Order.class);
//			for(Order order : OrdersList)
//			{
//				System.out.println(order.getRecievedate());
//				System.out.println(order.getStatus());
//				System.out.println(order.getTotalprice());
//				System.out.println(order.getId());
//			}
//			List<Order> MyOrderList = null;
//			for (Order order : OrdersList)
//			{
//				if(order.getClientid() == CurrUser)
//				{
//					MyOrderList.add(order);
//					System.out.println("ggggggggggggggggg");
//				}
//			}
			client.sendToClient(new Message("#MyOrdersList", OrdersList));

		} else if (msgString.equals("#CancelOrder")) {
			System.out.println("ana hoooooooooooooon");
			session = sessionFactory.openSession();
			session.beginTransaction();
			Message msg1 = ((Message) msg);
			Order order = (Order) msg1.getObject();
			System.out.println(order.getStatus());
			order.setStatus("Canceled");
			Registration User = (Registration) msg1.getObject2();
			String CurrUserID = order.getClientid();

//            System.out.println(order.getTotalprice());
//            System.out.println(order.getRecievetime());
//            System.out.println(order.getRecievedate());
//            System.out.println(CurrUserID);

			List<Registration> regList = getAll(Registration.class);
			for (Registration buyer : regList) {
				if (buyer.getClient_ID().equalsIgnoreCase(CurrUserID)) {
					System.out.println("ana bal if");
					System.out.println(buyer.getClient_ID());
					String Date = order.getRecievedate();
					System.out.println(Date);
					String Time = order.getRecievetime();
					RefundCheck time = new RefundCheck();
					int temp = time.Refund(Date, Time);
					System.out.println(temp);
					String RefundRespond = String.valueOf(User.getCreditCard());
					RefundRespond = RefundRespond.substring(RefundRespond.length() - 4);
//                    Confirmation Respond;
					Confirmation Respond;

					if (temp == 3) {
						Respond = new Confirmation("accordingly to our refunding policy" +
								" , you will get a full refund of your order that is : "
								+ order.getTotalprice() +
								" to your Credit Card that ends with the digits : ***" + RefundRespond);
						buyer.setRefund(Double.parseDouble(order.getTotalprice()));

						//refund accordingly to 3 hours or more before getting the package
					} else if (temp == 1) {
						Respond = new Confirmation("accordingly to our refunding policy ," +
								" you will get a 50% refund of your order that is : "
								+ (Double.parseDouble(order.getTotalprice()) / 2) +
								"to your Credit Card that ends with the digits :"
								+ RefundRespond);
						//refund accordingly to 3 hours or more before getting the package
						buyer.setRefund(Double.parseDouble(order.getTotalprice()) / 2);
					} else {
						Respond = new Confirmation("accordingly to our refund policy , you wont get a refund");
					}

					session.update(buyer);
					session.update(order);
					session.flush();
					session.getTransaction().commit();

					System.out.println(Respond.getMessage());

					try {
						client.sendToClient(new Message("#OrderCanceled", Respond));
					} catch (IOException e) {

					}

					//Send Email With the information of the respond *_*
					return;

//						}
//					}

				}
			}
		}
		else if(msgString.equals("#LogOut"))
		{
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


	}
	private static SessionFactory getSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(Catalog.class);
		configuration.addAnnotatedClass(Item.class);
		configuration.addAnnotatedClass((Message.class));
		configuration.addAnnotatedClass((Registration.class));
		configuration.addAnnotatedClass((SystemManagers_Messages.class));
		configuration.addAnnotatedClass((Report.class));
		configuration.addAnnotatedClass((ShoppingCart.class));
		configuration.addAnnotatedClass((Order.class));
		configuration.addAnnotatedClass((Complain.class));
		configuration.addAnnotatedClass((Confirmation.class));


		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
	private static void initializeData()
	{
		Catalog temp =new Catalog();
//		Order order2 = new Order();
//		session.save(order2);
		Item it1 =new Item("Spray carnations","Carnations","Pink","https://www.florikenblooms.co.ke/wp-content/uploads/2020/10/spraycarnations.png",50);
		Item it2 =new Item("Delphinium" , "Plant " , "Blue" ,  "https://hgtvhome.sndimg.com/content/dam/images/hgtv/fullset/2020/3/6/2/CI_Walters-Gardens_Cobalt-Dreams-delphinium.jpg.rend.hgtvcom.1280.1024.suffix/1583520085507.jpeg" ,20);
		Item it3 =new Item("Zamia Coconut L" , "Plant" , "Green" , "https://www.nurseryvilla.com/wp-content/uploads/2019/05/Zamia-Palm-Plant-504x504.png" ,50);
		Item it4 =new Item("Sensivaria medium" , "Plant" , "Green" , "https://www.studioplant.com/media/catalog/product/cache/76402474333a4497cbc043f2fd2ee788/f/a/faff1976-1.jpg" , 35);
		Item it5 =new Item("Bridal bouquet" , "White peonies" , "White" , "https://cdn11.bigcommerce.com/s-0023c/images/stencil/1280x1280/products/1890/4811/babys_breath_and_white_roses__11891.1643427610.jpg?c=2" , 200);
		Item it6 =new Item("Blue Roses" , "Roses" , "Blue" , "https://bulacanflowershop.com/images/detailed/8/lg_331-blue-roses-bouquet.jpg" , 80);
		Item it7 =new Item("Red Roses" , "Roses" , "Red" , "https://fyf.tac-cdn.net/images/products/large/F-224.jpg?auto=webp&quality=60&width=690" , 90);
		Item it8 =new Item("Single Rose" , "Rose" , "Red" , "https://www.giftstolebanon.com/3088-thickbox_default/single-rose.jpg" , 10);
		Item it9 =new Item("Posy Bouquet" , "Bouquet" , "Pink and White" , "https://cdn.shopify.com/s/files/1/0254/1512/2990/products/posybouquet_1024x1024.jpg?v=1630006473" , 70);
		Item it10 =new Item("Basket Bouquet" , "Bouquet" , "Pink and White" , "https://www.cassidysflowersandgifts.com/image/cache/data/Sympathy/FTD-S47-4553-300x300.jpg" ,80 );
		Item it11 =new Item("Fan Bouquet" , "Bouquet" , "White" , "https://cdn.shopify.com/s/files/1/0290/0636/4777/products/CGYD_LOL_preset_ftd-hero-lv-new_600x.jpg?v=1618958911" ,55 );
		Item it12 =new Item("Fiesta Bouquet" , "Bouquet" , "Red" , "https://italflorist.imgix.net/images/itemVariation/FiestaBouquetDeluxe-21090750624.jpg?auto=format&w=375&h=450&fit=crop" ,110 );
		Item it13 =new Item("Peony Bouquet" , "Bouquet" , "Pink and White" , "https://flowersofbath.co.uk/wp-content/uploads/2021/06/image4-1.jpeg" , 80);
		Item it14 =new Item("Hello Sunshine" , "Sunflower" , "Yellow" , "https://cdn.shopify.com/s/files/1/0290/0636/4777/products/CGYD_LOL_preset_ftd-hero-lv-new_600x.jpg?v=1618958911" ,80 );
		Item it15 =new Item("Rainbow Roses" , "Roses" , "Rainbow" , "https://i2-prod.dailyrecord.co.uk/incoming/article6728570.ece/ALTERNATES/s1200c/Rainbow-Roses-Bouquet.jpg" , 200);
		Item it16 =new Item("Yellow Roses" , "Roses" , "Yellow" , "https://asset.bloomnation.com/c_pad,d_vendor:global:catalog:product:image.png,f_auto,fl_preserve_transparency,q_auto/v1655349200/vendor/6279/catalog/product/1/e/1e4c46bb8f175398fbb5ced8c31fbeb1_48.jpg" ,70 );
		Item it17 =new Item("White Roses" , "Roses" , "White" , "https://cdn.shopify.com/s/files/1/0507/3754/5401/t/1/assets/E5435D_LOL_preset_proflowers-mx-tile-wide-lv-new.jpeg?v=1647430391" , 80);
		Item it18 =new Item("SunFlower Bouquet" , "Bouquet" , "Yellow" , "https://res.cloudinary.com/dizexseir/image/upload/v1648724575/ProImages/syaxrui2tdjfa4w6ahex.jpg" ,  90);
		Item it19 =new Item("Friendship Bouquet" , "Bouquet" , "White" , "https://cdn.shopify.com/s/files/1/0507/3754/5401/t/1/assets/S9-4979D_LOL_preset_proflowers-mx-tile-wide-lv-new.jpeg?v=1647421834" ,90 );
		Item it20 =new Item("Plant" , "Plant" , "White" , "https://www.ikea.com/om/en/images/products/fejka-artificial-potted-plant-with-pot-in-outdoor-succulent__0614211_pe686835_s5.jpg?f=s" , 30);
		Registration client1 = new Registration("Kareen", "Ghattas", "123456789", "kareen@gmail.com", "0505123456", "Client1", "1234", "Client", "2233445566", "1/1/2023", "Store Account",0);
		Registration client2 = new Registration("Natalie", "Nakkara", "234789456", "Natalie@gmail.com", "0524789000", "NatalieNK", "22nN90999", "Client", "1234561299", "5/8/2024", "Chain Account",0);
		Registration CEO = new Registration("Rashil", "Mbariky", "4443336661", "", "", "Rashi", "rashi", "CEO", "", "", "",0);
		Registration NetworkMarketingWorker = new Registration("Eissa", "Wahesh", "", "", "", "Eissa", "11111", "NetworkMarketingWorker", "", "", "",0);
		Registration customerservice=new Registration("nunu","nunu","","","","nunu","nunu","CustomerService","","","",0);
		Registration SystemManger = new Registration("Elias", "Dow", "", "", "", "Elisa", "lampon", "SystemManager", "", "", "",0);
		Registration BranchManger = new Registration("Saher", "Daoud", "", "", "", "Saher", "123456", "BranchManager", "", "", "",0);
		session.save(temp);
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
		session.save(CEO);
		session.save(NetworkMarketingWorker);
		session.save(SystemManger);
		session.save(BranchManger);
		session.save(customerservice);
		temp.addIteam(it1);
		temp.addIteam(it2);
		temp.addIteam(it3);
		temp.addIteam(it4);
		temp.addIteam(it5);
		temp.addIteam(it6);
		temp.addIteam(it7);
		temp.addIteam(it8);
		temp.addIteam(it9);
		temp.addIteam(it10);
		temp.addIteam(it11);
		temp.addIteam(it12);
		temp.addIteam(it13);
		temp.addIteam(it14);
		temp.addIteam(it15);
		temp.addIteam(it16);
		temp.addIteam(it17);
		temp.addIteam(it18);
		temp.addIteam(it19);
		temp.addIteam(it20);
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