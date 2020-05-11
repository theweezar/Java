package app.query;
import org.hibernate.*;
import app.entity.*;
import java.util.*;

public class KindQuery {
	private SessionFactory ftr;
	
	public KindQuery(SessionFactory ftr){
		this.ftr = ftr;
	}
	
	public Kind get(Integer id){
		Session session = ftr.getCurrentSession();
		String hql = "FROM Kind k WHERE k.id LIKE :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		return (Kind)query.uniqueResult();
	}
	
	public List<Kind> get(){
		Session session = ftr.getCurrentSession();
		String hql = "FROM Kind";
		Query query = session.createQuery(hql);
		return query.list();
	}
}
