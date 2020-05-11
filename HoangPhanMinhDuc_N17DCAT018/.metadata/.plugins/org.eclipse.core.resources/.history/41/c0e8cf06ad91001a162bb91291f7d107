package pizza.query;

import java.util.List;

import org.hibernate.*;

import pizza.entity.PizzaEntity;

public class QueryT {
	
	private SessionFactory ftr;
	
	public QueryT(SessionFactory ftr) {
		this.ftr = ftr;
	}
	
	public void add(Object obj) {
		Session session = ftr.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(obj);
			t.commit();
		}
		catch(Exception e) {
			t.rollback();
		}
		finally {
			session.close();
		}
	}
	
	public List<Object> select(String entity){
		Session session = ftr.getCurrentSession();
		String hql = "FROM " + entity;
		Query query = session.createQuery(hql);
		return query.list();
	}
	
	public List<Object> select(String entity, String patterns[]){
//		patterns = ["username=admin", email=admin@gmail.com"]
		Session session = ftr.getCurrentSession();
		String hql = "FROM " + entity + " e WHERE";
		for(int i = 0; i < patterns.length; i++) {
			String p = patterns[i];
			String[] col = p.split("=");
//			"FROM Entity e WHERE e.col[i] LIKE :vi"
			hql = hql + " e." + col[0] + " LIKE :" + "v" + i;
			if (i < patterns.length - 1) hql = hql + " AND";
		}
		System.out.println(hql);
		Query query = session.createQuery(hql);
		for(int i = 0; i < patterns.length; i++) {
			String p = patterns[i];
			String[] col = p.split("=");
			if (col[1].equals("false")) query.setParameter("v"+i, false);
			else if (col[1].equals("true")) query.setParameter("v"+i, true);
			else query.setParameter("v"+i, col[1]);
		}
		return query.list();
	}
}
