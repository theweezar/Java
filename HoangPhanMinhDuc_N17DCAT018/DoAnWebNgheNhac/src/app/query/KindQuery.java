package app.query;
import org.hibernate.*;
import app.entity.*;
import java.util.*;

public class KindQuery {
	private SessionFactory ftr;
	
	public KindQuery(SessionFactory ftr){
		this.ftr = ftr;
	}
	
	public List<Kind> get(){
		Session session = ftr.getCurrentSession();
		String hql = "FROM Kind";
		Query query = session.createQuery(hql);
		return query.list();
	}
}
