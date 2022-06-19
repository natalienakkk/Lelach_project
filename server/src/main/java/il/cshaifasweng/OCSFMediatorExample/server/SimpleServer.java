package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
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
		else if(msgString.startsWith("#update_price"))
		{

			Message msg7 = ((Message) msg);
			double price10 = (double) msg7.getObject();
			long id10 = ((long) msg7.getObject2());
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Item> itemList=getAll(Item.class);
			int i = 0;
			for(i=0 ; i < itemList.size() ; i++)
			{
				if(itemList.get(i).getId() == id10 )
				{
					itemList.get(i).setPrice(price10);
					session.save(itemList.get(i));
					session.flush();
					session.getTransaction().commit();
					break;
				}
			}
			session.close();
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
			System.out.println("nayakat1");
			Message msg1 = ((Message) msg);
			Item item = (Item) msg1.getObject();
			double amount = (double) msg1.getObject2();
			ShoppingCart cart1 = (ShoppingCart) msg1.getObject3();
			System.out.println("nayakat1");
			if (cart1.getItems().equals(null))
			System.out.println("null");
			else System.out.println("nayakaaaaaaaaaaaaaaaaaaaaaaaaat5");
			System.out.println("nayakat2");
			session = sessionFactory.openSession();
			session.beginTransaction();
			if(cart1 == null) System.out.println("null!!\n");
			cart1.AddtoCart(item);
			cart1.Addamount(amount);
			session.save(cart1);
			session.flush();
			session.getTransaction().commit();
			session.close();
			System.out.println("nayakat3");
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
//							registration.setRegistered(true);
						Warning new_warning = new Warning("Dear Client,you are already Signed up.\n Please go to Login.");
						client.sendToClient(new Message("#SignUpWarning", new_warning));
						return;
					} else {
						session.beginTransaction();
						session.save(newSignUp);
						session.flush();
						session.getTransaction().commit();
						Warning newWarning = new Warning("Dear " + newSignUp.getUserName() + " welcome to Lilach. you have been signed up successfully");
						client.sendToClient(new Message("#MemberSignedUpSucces", newWarning));
						return;
					}
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (msgString.startsWith("#LoginRequest")) {
			Message msg1 = ((Message) msg);
			User newLogin = (User) msg1.getObject();
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Item> itemList=getAll(Item.class);
			String UserName = newLogin.getUserName();
			String Password = newLogin.getPassword();
			try{
				List<Registration> clients = getAll(Registration.class);
				for (Registration registration : clients){
					if(registration.getUserName().equalsIgnoreCase(UserName)){
						if(registration.getPassword().equalsIgnoreCase(Password)){
							if(registration.getStatus().equalsIgnoreCase("blocked client")){
								Warning new_warning = new Warning("You're account have been blocked. Please contact customer service");
								client.sendToClient(new Message("#BlockedAccount", new_warning));
								return;
							}
							else{
								client.sendToClient(new Message("#LogInSucess", registration , itemList));
								return;
							}
						}
						else{
							Warning new_warning = new Warning("You have entered invalid input. Please try again ");
							client.sendToClient(new Message("#LoginWarning", new_warning));
							return;
						}
					}

				}

			}catch (Exception e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			String discount_per = ((String) msgdiscount.getObject());
			double discount = Double.parseDouble(discount_per);
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
		else if(msgString.startsWith("#block account"))
		{
			Message msgblock = ((Message) msg);
			String username = ((String) msgblock.getObject());
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Registration> usernameList=getAll(Registration.class);
			for(int i=0 ; i < usernameList.size() ; i++)
			{
				if (usernameList.get(i).getUserName().equals(username))
				{
					usernameList.get(i).setStatus("blocked client");
					session.save(usernameList.get(i));
				}
			}
			session.getTransaction().commit();
			session.flush();
			session.close();
		}



		else if (msgString.equals("#send message")) {
			Message msgclient = ((Message) msg);
			String username = ((String) msgclient.getObject());
			String client_msg = ((String) msgclient.getObject2());
			System.out.format(client_msg+"\n");
			System.out.format(username);
			SystemManagers_Messages message=new SystemManagers_Messages(client_msg,username);
			session = sessionFactory.openSession();
			session.beginTransaction();
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
			int i = 0;
			for(i=0 ; i < usernameList.size() ; i++)
			{
				if(usernameList.get(i).getUserName().equals(username))
				{
					usernameList.get(i).setStatus(worker_status);
					session.save(usernameList.get(i));
					break;
				}
			}
			session.flush();
			session.getTransaction().commit();
			session.close();
		}
		else if(msgString.equals("#show Report"))
		{
			Message msg_report = ((Message) msg);
			String Type  = ((String) msg_report.getObject());
			String start=((String) msg_report.getObject2());
			String end=((String) msg_report.getObject3());
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Report> report_list=getAll(Report.class);
			try {
				client.sendToClient(new Message("#list of report sent",Type,start,end,report_list));
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.close();
		}
		else if(msgString.equals("#show Report to compare"))
		{
			Message msg_report = ((Message) msg);
			String Type  = ((String) msg_report.getObject());
			String first_start=((String) msg_report.getObject2());
			String first_end=((String) msg_report.getObject3());
			String second_start=((String) msg_report.getObject4());
			String second_end=((String) msg_report.getObject5());
			System.out.println("server "+Type+first_start+first_end+second_start+second_end);
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Report> report_list=getAll(Report.class);
			try {
				client.sendToClient(new Message("#list of report sent to compare",Type,first_start,first_end,second_start,second_end,report_list));
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.close();
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

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
	private static void initializeData()
	{
		Catalog temp =new Catalog();
		Item it1 =new Item("Spray carnations","Carnations","Pink","@../../../../images/Spray carnations.jpg",50);
		Item it2 =new Item("Delphinium" , "Plant " , "Blue" ,  "@../../../../images/Delphinium.jpg" ,20);
		Item it3 =new Item("Zamia Coconut L" , "Plant" , "Green" , "@../../../../images/Zamia Coconut L.jpg" ,100);
		Item it4 =new Item("Sensivaria medium" , "Plant" , "Green" , "@../../../../images/Sensivaria medium.jpg" , 55);
		Item it5 =new Item("Bridal bouquet" , "White peonies" , "White" , "@../../../../images/Bridal bouquet.jpg" , 300);
		Item it6 =new Item("Blue Roses" , "Roses" , "Blue" , "@../../../../images/Blue Roses.jpg" , 80);
		Item it7 =new Item("Red Roses" , "Roses" , "Red" , "@../../../../images/Red Roses.jpg" , 90);
		Item it8 =new Item("Single Rose" , "Rose" , "Red" , "@../../../../images/Single Rose.jpg" , 10);
		Item it9 =new Item("Posy Bouquet" , "Bouquet" , "Pink and White" , "@../../../../images/Posy Bouquet.jpg" , 70);
		Item it10 =new Item("Basket Bouquet" , "Bouquet" , "Pink and White" , "@../../../../images/Basket Bouquet.jpg" ,130 );
		Item it11 =new Item("Fan Bouquet" , "Bouquet" , "White" , "@../../../../images/Fan Bouquet.jpg" ,55 );
		Item it12 =new Item("Fiesta Bouquet" , "Bouquet" , "Red and Purple" , "@../../../../images/Fiesta Bouquet.jpg" ,110 );
		Item it13 =new Item("Peony Bouquet" , "Bouquet" , "Pink and White" , "@../../../../images/Peony Bouquet.jpg" , 100);
		Item it14 =new Item("Hello Sunshine" , "Sunflower" , "Yellow and White" , "@../../../../images/Hello Sunshine.jpg" ,80 );
		Item it15 =new Item("Rainbow Roses" , "Roses" , "Rainbow" , "@../../../../images/Rainbow Roses.jpg" , 250);
		Item it16 =new Item("Yellow Roses" , "Roses" , "Yellow" , "@../../../../images/Yellow Roses.jpg" ,70 );
		Item it17 =new Item("White Roses" , "Roses" , "White" , "@../../../../images/White Roses.jpg" , 80);
		Item it18 =new Item("SunFlower Bouquet" , "Bouquet" , "Yellow" , "@../../../../images/SunFlower Bouquet.jpg" ,  100);
		Item it19 =new Item("Friendship Bouquet" , "Bouquet" , "White" , "@../../../../images/Friendship Bouquet.jpg" ,140 );
		Item it20 =new Item("Plant" , "Plant" , "White" , "@../../../../images/Plant.jpg" , 30);
		Registration client1 = new Registration("Kareen", "Ghattas", "123456789", "kareen@gmail.com", "0505123456", "Client1", "1234", "Client", "2233445566", "1/1/2023", "Store Account");
		Registration client2 = new Registration("Natalie", "Nakkara", "234789456", "Natalie@gmail.com", "0524789000", "NatalieNK", "22nN90999", "Client", "1234561299", "5/8/2024", "Chain Account");
		Registration CEO = new Registration("Rashil", "Mbariky", "4443336661", "", "", "Rashi", "rashi", "CEO", "", "", "");
		Registration NetworkMarketingWorker = new Registration("Eissa", "Wahesh", "", "", "", "Eissa", "11111", "NetworkMarketingWorker", "", "", "");
		Registration SystemManger = new Registration("Elias", "Dow", "", "", "", "Elisa", "lampon", "SystemManager", "", "", "");
		Registration BranchManger = new Registration("Saher", "Daoud", "", "", "", "Saher", "123456", "BranchManager", "", "", "");
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