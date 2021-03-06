package app.query;
import org.hibernate.*;
import app.entity.*;
import java.util.*;

public class PlayListQuery {

	private SessionFactory ftr;
	
	public PlayListQuery(SessionFactory ftr){
		this.ftr = ftr;
	}
	
	public void addPlayList(PlayList playlist){
		Session session = ftr.openSession();
		Transaction t = session.beginTransaction();
		try{
			session.save(playlist);
			t.commit();
		}
		catch(Exception e){
			t.rollback();
		}
		finally{
			session.close();
		}
	}
	
	public void updatePlayList(PlayList playlist){
		Session session = ftr.openSession();
		Transaction t = session.beginTransaction();
		try{
			session.update(playlist);
			t.commit();
		}
		catch(Exception e){
			t.rollback();
		}
		finally{
			session.close();
		}
	}
	
	public void delPlayList(int plId){
		Session session = ftr.openSession();
		Transaction t = session.beginTransaction();
		try{
			session.delete(session.get(PlayList.class, plId));
			t.commit();
		}
		catch(Exception e){
			t.rollback();
			throw e;
		}
		finally{
			session.close();
		}
	}
	
	public List<PlayList> getPlayList(int userId, int isLater){
		Session session = ftr.getCurrentSession();
		String hql = "FROM PlayList pl WHERE pl.user.id LIKE :userId AND pl.isLater LIKE :isLater";
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		query.setParameter("isLater", isLater);
		return query.list();
	}
	
	public PlayList getPlayList(int plId){
		Session session = ftr.getCurrentSession();
		String hql = "FROM PlayList pl WHERE pl.id LIKE :plId";
		Query query = session.createQuery(hql);
		query.setParameter("plId", plId);
		return (PlayList)query.uniqueResult();
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
		String hql = "FROM PlayListDetail dt WHERE dt.playlist.id LIKE :plId AND dt.song.id LIKE :songId";
		Query query = session.createQuery(hql);
		query.setParameter("plId", plId);
		query.setParameter("songId", songId);
		return (PlayListDetail)query.uniqueResult();
	}
	
	public void deleleDetail(PlayListDetail detail){
		Session session = ftr.openSession();
		Transaction t = session.beginTransaction();
		try{
			session.delete(detail);
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
