
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @since JavaSE-1.8
 */
public class UtilDB {
   static SessionFactory sessionFactory = null;

   public static SessionFactory getSessionFactory() {
      if (sessionFactory != null) {
         return sessionFactory;
      }
      Configuration configuration = new Configuration().configure();
      StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
      sessionFactory = configuration.buildSessionFactory(builder.build());
      return sessionFactory;
   }

   public static List<Event> listEvent() {
      List<Event> resultList = new ArrayList<Event>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;  // each process needs transaction and commit the changes in DB.

      try {
         tx = session.beginTransaction();
         List<?> events = session.createQuery("FROM Event").list();
         for (Iterator<?> iterator = events.iterator(); iterator.hasNext();) {
        	 Event event = (Event) iterator.next();
            resultList.add(event);
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static List<Event> listEvent(String keyword) {
      List<Event> resultList = new ArrayList<Event>();

      Session session = getSessionFactory().openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         System.out.println((Event)session.get(Event.class, 1)); // use "get" to fetch data
        // Query q = session.createQuery("FROM Employee");
         List<?> events = session.createQuery("FROM Event").list();
         for (Iterator<?> iterator = events.iterator(); iterator.hasNext();) {
        	 Event event = (Event) iterator.next();
            if (event.getName().startsWith(keyword)) {
               resultList.add(event);
            }
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
      return resultList;
   }

   public static void createEvent(String user, String start, String end, String date, String name) {
      Session session = getSessionFactory().openSession();
      Transaction tx = null;
      try {
         tx = session.beginTransaction();
         session.save(new Event(user, start, end, date, name));
         tx.commit();
      } catch (HibernateException e) {
         if (tx != null)
            tx.rollback();
         e.printStackTrace();
      } finally {
         session.close();
      }
   }
   
   public static void deleteEvent(Integer id) {
	   Session session = getSessionFactory().openSession();
	   Transaction tx = null;
	   try {
		   tx = session.beginTransaction();
		   List<Event> list = listEvent();
		   Event deleteMe = null;
		   for ( Event event : list ) {
			   if (event.getId() == id) {
				   deleteMe = event;
				   break;
			   }
		   }
		   
		   if (deleteMe != null) {
			   session.delete(deleteMe);
		   }
		   tx.commit();
	   } catch (HibernateException e) {
		   if (tx != null)
			   tx.rollback();
	       e.printStackTrace();
	   } finally {
		   session.close();
	   }
   }
}
