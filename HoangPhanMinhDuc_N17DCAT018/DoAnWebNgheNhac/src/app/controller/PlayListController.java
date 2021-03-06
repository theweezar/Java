package app.controller;
//import org.hibernate.Query;
//import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.sun.xml.internal.bind.CycleRecoverable.Context;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.*;

import app.entity.*;
import app.query.*;
import app.render.Render;


@Controller
public class PlayListController {
	@Autowired
	SessionFactory ftr;
	
	@Transactional
	@RequestMapping(value="/addtolovepl", method=RequestMethod.POST)
	public void handle(HttpServletRequest req ,HttpServletResponse res) throws IOException{
		String songId = req.getParameter("songId");
		PlayListQuery plQuery = new PlayListQuery(ftr);
		PlayList cPl = plQuery.getPlayList((int)req.getSession().getAttribute("userId"), 1).get(0);
		System.out.println(cPl.getId());
		PlayListDetail detail = plQuery.getDetail(cPl.getId(), Integer.parseInt(songId));
		if (detail == null){
			PlayListDetail item = new PlayListDetail();
			item.setPlaylist(cPl);
			item.setSongId(Integer.parseInt(songId));
			item.setAdd_at(new Date());
			plQuery.addSong(item);
			res.getWriter().println(true);
		}
		else{
			plQuery.deleleDetail(detail);
			res.getWriter().println(false);
		}
	}
	
	@Transactional
	@RequestMapping("playlist")
	public String playlist(ModelMap model, HttpServletRequest req, HttpServletResponse res) throws IOException{
		Render r = new Render(model);
		if (req.getSession().getAttribute("logged") != null){
			PlayListQuery plQuery = new PlayListQuery(ftr);
			List<PlayList> pl = plQuery.getPlayList((int)req.getSession().getAttribute("userId"), 0);
			if (pl.size() > 0){
				r.setModelAttr("playList", pl);
			}
		}
		else res.sendRedirect("./account.htm?m=login");
		return r.render("mainLayout", "song/playlist");
	}
	
	@Transactional
	@RequestMapping(value="/newplaylist",method=RequestMethod.POST)
	public void newplaylist(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String plName = req.getParameter("plName");
		PlayList pl = new PlayList();
		pl.setPlName(plName);
		pl.setUser(new UserQuery(ftr).get("username="+req.getSession().getAttribute("username")));
		pl.setLater(0);
		PlayListQuery plQuery = new PlayListQuery(ftr);
		plQuery.addPlayList(pl);
		res.getWriter().print(plName+";"+pl.getId());
	}
	
	@Transactional
	@RequestMapping(value="/delplaylist",method=RequestMethod.POST)
	public void delplaylist(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String plId = req.getParameter("plId");
		PlayListQuery plQuery = new PlayListQuery(ftr);
		plQuery.delPlayList(Integer.parseInt(plId));
		res.getWriter().print("done");
	}
}
