package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

//import javax.imageio.spi.ServiceRegistry;
import javax.persistence.*;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;



import java.io.IOException;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
import il.cshaifasweng.OCSFMediatorExample.entities.Catalog;
import il.cshaifasweng.OCSFMediatorExample.entities.Item;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
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

			session = sessionFactory.openSession();
			List<Item> itemList=getAll(Item.class);
			System.out.format(itemList.get(0).getColor() + " ana hooooon    ");
			client.sendToClient(new Message("#SendLists1", itemList.get(0)));
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
					System.out.format("Price changed successfully! ");
					break;
				}
			}

			session.close();

		}
		else if(msgString.startsWith("#openmanagercatalog"))
		{
			session = sessionFactory.openSession();
			List<Item> itemList=getAll(Item.class);
			client.sendToClient(new Message("#SendLists", itemList.get(0)));
			session.close();
		}
		else if(msgString.startsWith("#openspray"))
		{
			System.out.format("saher1");
			try {
				session = sessionFactory.openSession();
				List<Item> Itemlist=getAll(Item.class);
				client.sendToClient(new Message("#openspray1" , Itemlist.get(0)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.format("handling in server : open spray! \n");
			session.close();
		}else if(msgString.startsWith("#1openspray3"))
		{
			System.out.format("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			try {
				System.out.format("aaaaaaaaaaaaaaaaaa77777777777777777777777777777777a");
				session = sessionFactory.openSession();
				List<Item> Itemlist=getAll(Item.class);
				System.out.format("hhhhhhhhhhhhhhhhh" + Itemlist.get(0).getName()+ " hhhhhhhhhhhhhhhhhhhhh");
				client.sendToClient(new Message("#1openspray4" , Itemlist.get(0)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.format("handling in server : open spray! \n");
			session.close();
		}
		else if(msgString.startsWith("#opendelphunim"))
		{

			try {
				session = sessionFactory.openSession();
				List<Item> Itemlist=getAll(Item.class);
				client.sendToClient(new Message("#opendelphunim1" , Itemlist.get(1)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.format("handling in server : open spray! \n");
			session.close();
		}else if(msgString.startsWith("#1opendelphunim3"))
		{

			try {
				session = sessionFactory.openSession();
				List<Item> Itemlist=getAll(Item.class);
				client.sendToClient(new Message("#1opendelphunim4" , Itemlist.get(1)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.format("handling in server : open spray! \n");
			session.close();
		}
		else if(msgString.startsWith("#openbridal"))
		{

			try {
				session = sessionFactory.openSession();
				List<Item> Itemlist=getAll(Item.class);
				client.sendToClient(new Message("#openbridal1" , Itemlist.get(4)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.format("handling in server : open spray! \n");
			session.close();
		}else if(msgString.startsWith("#1openbridal3"))
		{

			try {
				session = sessionFactory.openSession();
				List<Item> Itemlist=getAll(Item.class);
				client.sendToClient(new Message("#1openbridal4" , Itemlist.get(4)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.format("handling in server : open spray! \n");
			session.close();
		}
		else if(msgString.startsWith("#opensensivaria"))
		{

			try {
				session = sessionFactory.openSession();
				List<Item> Itemlist=getAll(Item.class);
				client.sendToClient(new Message("#opensensivaria1" , Itemlist.get(3)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.format("handling in server : open spray! \n");
			session.close();
		}else if(msgString.startsWith("#1opensensivaria3"))
		{

			try {
				session = sessionFactory.openSession();
				List<Item> Itemlist=getAll(Item.class);
				client.sendToClient(new Message("#1opensensivaria4" , Itemlist.get(3)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.format("handling in server : open spray! \n");
			session.close();
		}
		else if(msgString.startsWith("#openzamia"))
		{

			try {
				session = sessionFactory.openSession();
				List<Item> Itemlist=getAll(Item.class);
				client.sendToClient(new Message("#openzamia1" , Itemlist.get(2)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.format("handling in server : open spray! \n");
			session.close();
		}else if(msgString.startsWith("#1openzamia3"))
		{

			try {
				session = sessionFactory.openSession();
				List<Item> Itemlist=getAll(Item.class);
				client.sendToClient(new Message("#1openzamia4" , Itemlist.get(2)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.format("handling in server : open spray! \n");
			session.close();
		}

	}
	private static SessionFactory getSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(Catalog.class);
		configuration.addAnnotatedClass(Item.class);
		configuration.addAnnotatedClass((Message.class));

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}
	private static void initializeData()
	{
		Catalog temp =new Catalog();
		Item it1 =new Item("Spray carnations","Carnations","Pink","pic",50);
		Item it2 =new Item("The delphinium" , "Delphinium " , "Blue" ,  "pic" ,200);
		Item it3 =new Item("Zamia Coconut L" , "Zamia Coconut" , "Green" , "pic" ,100);
		Item it4 =new Item("Sensivaria medium" , "Sensivaria" , "Green" , "pic" , 55);
		Item it5 =new Item("Bridal bouquet" , "White peonies" , "White" , "pic" , 300);
		session.save(temp);
		session.save(it1);
		session.save(it2);
		session.save(it3);
		session.save(it4);
		session.save(it5);
		temp.addIteam(it1);
		temp.addIteam(it2);
		temp.addIteam(it3);
		temp.addIteam(it4);
		temp.addIteam(it5);
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