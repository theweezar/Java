package app.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import app.entity.*;
import app.query.PlayListQuery;


@WebServlet("/AddToPlayList")
public class AddToPlayList extends HttpServlet{

	@Autowired
	SessionFactory ftr;
	private static final long serialVersionUID = 1L;
	@Transactional
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
//		String songId = req.getParameter("songId");
//		System.out.print(songId);
//		PlayListQuery query = new PlayListQuery(ftr);
//		PlayListDetail item = new PlayListDetail();
//		(int)req.getSession().getAttribute("userId")
//		List<PlayList> pl = ;
//		System.out.print(query.getPlayList(1, 1));
//		item.setPlId(pl.getId());
//		item.setSongId(Integer.parseInt(songId));
//		item.setAdd_at(new Date());
//		query.addSong(item);
		PrintWriter out = res.getWriter();
		out.print("ina");
	}
}
