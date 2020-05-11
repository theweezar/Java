package app.query;
import org.hibernate.*;
import app.entity.*;
import java.util.*;

public class PlayListQuery {

	private SessionFactory ftr;
	
	public PlayListQuery(SessionFactory ftr){
		this.ftr = ftr;
	}
	
	public List<PlayList> getPlayList(int userId, int isLater){
		Session session = ftr.getCurrentSession();
		String hql = "FROM PlayList pl WHERE pl.userId LIKE :userId AND pl.isLater LIKE :isLater";
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		query.setParameter("isLater", isLater);
		return query.list();
	}
	
	public void addSong(PlayListDetail item){
		Session session = ftr.openSession();
		Transaction t = session.beginTransaction();
		try{
			session.save(item);
			t.commit();
		}
		catch(Exception e){
			t.rollback();
		}
		finally{
			session.close();
		}
	}
	
	public PlayListDetail getDetail(int plId, int songId){
		Session session = ftr.getCurrentSession();
		String hql = "FROM PlayListDetail dt WHERE dt.plId LIKE :plId AND dt.songId LIKE :songId";
		Query query = session.createQuery(hql);
		query.setParameter("plId", plId);
		query.setParameter("songId", songId);
		return (PlayListDetail)query.uniqueResult();
	}
}
