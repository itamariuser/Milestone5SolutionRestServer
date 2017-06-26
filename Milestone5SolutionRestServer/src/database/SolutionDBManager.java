package database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import commons.ServerPlan;

public class SolutionDBManager {

	private static SolutionDBManager instance ;
	private static SessionFactory factory;

	public static SolutionDBManager getInstance() {
		if(instance==null)
		{
			instance=new SolutionDBManager();
		}
		
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			factory = configuration.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public List<ServerPlan> getPlanForLevelName(String levelName) throws Exception
	{
		Session session = null;
		ArrayList<ServerPlan> plans=new ArrayList<ServerPlan>();
		try {
			session = factory.openSession();
			Query<ServerPlan> query=session.createQuery("FROM ServerPlans p WHERE p.LevelName = :name", ServerPlan.class);
			query.setParameter("name", levelName);
			plans.addAll(query.list());
		}
		catch (HibernateException ex) {
			throw new Exception("Couldn't get plans from DB");
		}
		finally {
			if (session != null)
				session.close();
		}
		return plans;
	}
	
	public void addServerPlan(ServerPlan plan) throws Exception  {
		Session session = null;
		Transaction tx = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.save(plan);
			tx.commit();			
		}
		catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			throw new Exception("Couldn't add plan to DB");
		}
		finally {
			if (session != null)
				session.close();
		}		
	}
}
