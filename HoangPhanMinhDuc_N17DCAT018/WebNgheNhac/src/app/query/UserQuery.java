package app.query;
import org.hibernate.*;
import app.entity.*;
import java.util.*;


public class UserQuery {
	
	private SessionFactory ftr;
	
	public UserQuery(SessionFactory ftr){
		this.ftr = ftr;
	}
	
	public void add(User user){
		Session session = ftr.openSession();
		Transaction t = session.beginTransaction();
		try{
			session.save(user);
			t.commit();
		}
		catch(Exception e){
			t.rollback();
		}
		finally{
			session.close();
		}
	}
	
	public User get(String pattern){
		Session session = ftr.getCurrentSession();
		String[] row = pattern.split("=");
		String hql = "FROM User u WHERE u." + row[0] +" LIKE :data";
		Query query = session.createQuery(hql);
		query.setParameter("data", row[1]);
		return (User)query.uniqueResult();
	}
}
