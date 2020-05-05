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
import java.io.File;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.*;

import app.entity.*;
import app.query.*;
import app.render.Render;

@Controller
public class SongController {
	@Autowired
	SessionFactory ftr;
	
	@Transactional
	@ModelAttribute("kind")
	public List<Kind> getKind(){
		return new KindQuery(ftr).get();
	}
	
	@Transactional
	@RequestMapping(value="upload",method=RequestMethod.GET)
	public String uploadPage(ModelMap model, HttpServletRequest req, HttpServletResponse res ) throws Exception{
		Render r = new Render(model);
		HttpSession httpss = req.getSession();
		if (httpss.getAttribute("logged") == null) res.sendRedirect("./home.htm");
		r.setModelAttr("listKind", new KindQuery(ftr).get());
		return r.render("mainLayout", "upload");
	}
	
	@RequestMapping(value="upload",method=RequestMethod.POST)
	public void upload(@RequestParam("uplSong") MultipartFile song, 
			HttpServletRequest req, HttpServletResponse res) throws IOException{
		Song s = new Song();
		SongQuery query = new SongQuery(ftr);
		String songName = req.getParameter("songName");
		String singerName = req.getParameter("singerName");
		String musicianName = req.getParameter("musicianName");
		String kindId = req.getParameter("kind");
		if (!song.isEmpty()){
			s.setLink(song.getOriginalFilename());
			s.setUserId((int)req.getSession().getAttribute("userId"));
			s.setUploadAt(new Date());
			// Generate random name for songName
			s.setSongName(songName);
			s.setSingerName(singerName);
			s.setMusicianName(musicianName);
			s.setKindId(Integer.parseInt(kindId));
			s.setView(0);
			song.transferTo(new File(req.getServletContext().getRealPath("/music_src/" + song.getOriginalFilename())));
			query.add(s);
//			D:\programming\Java\HoangPhanMinhDuc_N17DCAT018\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\DoAnWebNgheNhac\music_src\WYS - Snowman.mp3			
			res.sendRedirect("./upload.htm");
		}
	}
}
