package app.query;
import org.hibernate.*;
import app.entity.*;
import java.util.*;

public class SongQuery {
	
	private SessionFactory ftr;
	
	public SongQuery(SessionFactory ftr){
		this.ftr = ftr;
	}
	
	public List<Song> getAll(){
		Session session = ftr.getCurrentSession();
		String hql = "FROM Song";
		return session.createQuery(hql).list();
	}
	
	public void add(Song song){
		Session session = ftr.openSession();
		Transaction t = session.beginTransaction();
		try{
			session.save(song);
			t.commit();
		}
		catch(Exception e){
			t.rollback();
		}
		finally{
			session.close();
		}
	}
	
	
}
