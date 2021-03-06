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

@Transactional
@Controller
public class PlayListController {
	@Autowired
	SessionFactory ftr;
	
	@ModelAttribute("userName")
	public String showUsername(HttpServletRequest req){
		if (req.getSession().getAttribute("logged") != null) return req.getSession().getAttribute("username").toString();
		else return "";
	}
	
	@ModelAttribute("lovePl")
	public Collection<PlayListDetail> getsongfromlovepl(HttpServletRequest req){
		if (req.getSession().getAttribute("logged") != null){
			PlayListQuery plQuery = new PlayListQuery(ftr);
			return plQuery.getPlayList((int)req.getSession().getAttribute("userId"), 1).get(0).getPlDetail();
		}
		else return null;
	}
	
	@RequestMapping(value="/addtolovepl", method=RequestMethod.POST)
	public void addtolovepl(HttpServletRequest req ,HttpServletResponse res) throws IOException{
		String songId = req.getParameter("songId");
		PlayListQuery plQuery = new PlayListQuery(ftr);
		PlayList cPl = plQuery.getPlayList((int)req.getSession().getAttribute("userId"), 1).get(0);
		System.out.println(cPl.getId());
		PlayListDetail detail = plQuery.getDetail(cPl.getId(), Integer.parseInt(songId));
		if (detail == null){
			PlayListDetail item = new PlayListDetail();
			item.setPlaylist(cPl);
			item.setSong((Song)ftr.getCurrentSession().get(Song.class, Integer.parseInt(songId)));
			item.setAdd_at(new Date());
			plQuery.addSong(item);
			res.getWriter().println(true);
		}
		else{
			plQuery.deleleDetail(detail);
			res.getWriter().println(false);
		}
	}
	
	
	@RequestMapping("playlist")
	public String playlist(ModelMap model, HttpServletRequest req, HttpServletResponse res) throws IOException{
		Render r = new Render(model);
		PlayListQuery plQuery = new PlayListQuery(ftr);
		String plId = req.getParameter("plId");
		System.out.println(plId);
		if (req.getSession().getAttribute("logged") == null){
			res.sendRedirect("./account.htm?m=login");
		}
//		./playlist.htm?plId=...
		if (plId == null){
			List<PlayList> pl = plQuery.getPlayList((int)req.getSession().getAttribute("userId"), 0);
			List<PlayList> likePl = plQuery.getPlayList((int)req.getSession().getAttribute("userId"), 1);
			pl.add(0, likePl.get(0));
			r.setModelAttr("playList", pl);
			return r.render("mainLayout", "song/playlist");
		}
		else {
			PlayList playList = plQuery.getPlayList(Integer.parseInt(plId));
			r.setModelAttr("plName", playList.getPlName());
			r.setModelAttr("plId", playList.getId());
			r.setModelAttr("plIsLater", playList.isLater());
			if (playList.getPlDetail().size() > 0){
				r.setModelAttr("emp", false);
				r.setModelAttr("songList", playList.getPlDetail());
			}
			else r.setModelAttr("emp", true);
			return r.render("mainLayout", "song/listsong");
		}
	}
	
	
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
	
	
	@RequestMapping(value="/changeplaylistname",method=RequestMethod.POST)
	public void changeplaylistname(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String newName = req.getParameter("newName");
		String plId = req.getParameter("plId");
		PlayListQuery plQuery = new PlayListQuery(ftr);
		PlayList pl = plQuery.getPlayList(Integer.parseInt(plId));
		pl.setPlName(newName);
		plQuery.updatePlayList(pl);
		res.getWriter().print(newName);
	}
	
	
	@RequestMapping(value="/delplaylist",method=RequestMethod.POST)
	public void delplaylist(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String plId = req.getParameter("plId");
		PlayListQuery plQuery = new PlayListQuery(ftr);
		PlayList pl = plQuery.getPlayList(Integer.parseInt(plId));
		for(PlayListDetail d:pl.getPlDetail()){
			plQuery.deleleDetail((PlayListDetail)ftr.getCurrentSession().get(PlayListDetail.class, d.getId()));
		}
		plQuery.delPlayList(Integer.parseInt(plId));
		res.getWriter().print("done");
	}
	
	
	@RequestMapping(value="/openboxpl",method=RequestMethod.POST)
	public void openboxpl(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String csId = req.getParameter("songId");
		PlayListQuery plQuery = new PlayListQuery(ftr);
		List<PlayList> playList = plQuery.getPlayList((int)req.getSession().getAttribute("userId"), 0);
		String str = "";
//		System.out.println(playList);
		for(int i = 0; i < playList.size(); i++){
//			System.out.println(plQuery.getDetail(playList.get(i).getId(), Integer.parseInt(csId)));
			if (plQuery.getDetail(playList.get(i).getId(), Integer.parseInt(csId)) != null){
				playList.remove(i);
				i -= 1;
			}
			else str += playList.get(i).getId()+":"+playList.get(i).getPlName()+";";
		}
		res.getWriter().print(str);
	}
	
	
	@RequestMapping(value="/addtopl",method=RequestMethod.POST)
	public void addtopl(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String csId = req.getParameter("songId").trim();
		String plId = req.getParameter("plId").trim();
		if (!csId.equals("") && !plId.equals("")){
			PlayListQuery plQuery = new PlayListQuery(ftr);
			PlayListDetail detail = new PlayListDetail();
			detail.setPlaylist((PlayList)ftr.getCurrentSession().get(PlayList.class, Integer.parseInt(plId)));
			detail.setSong((Song)ftr.getCurrentSession().get(Song.class, Integer.parseInt(csId)));
			detail.setAdd_at(new Date());
			plQuery.addSong(detail);
			res.getWriter().print("songId: "+csId+"| playlistId: "+plId);
		}
		else res.getWriter().print("stop");
	}
	
	
	@RequestMapping(value="/delsongfrompl",method=RequestMethod.POST)
	public void delsongfrompl(HttpServletRequest req, HttpServletResponse res) throws IOException{
		String songId = req.getParameter("songId");
		String plId = req.getParameter("plId");
		
		PlayListQuery plQuery = new PlayListQuery(ftr);
		plQuery.deleleDetail(plQuery.getDetail(Integer.parseInt(plId), Integer.parseInt(songId)));
		
		res.getWriter().print(true);
	}
}
