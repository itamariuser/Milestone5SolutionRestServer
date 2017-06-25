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
	
	private SolutionDBManager() {
	}
	
	public List<ServerPlan> getPlanForLevelName(String levelName)
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
			System.out.println(ex.getMessage());
		}
		finally {
			if (session != null)
				session.close();
		}
		return plans;
	}
	
	public void addServerPlan(ServerPlan plan)  {
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
			System.out.println(ex.getMessage());
		}
		finally {
			if (session != null)
				session.close();
		}		
	}
}
