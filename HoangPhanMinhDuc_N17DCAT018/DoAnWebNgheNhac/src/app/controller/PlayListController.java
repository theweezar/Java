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
	@RequestMapping(value="/addtopl", method=RequestMethod.POST)
	public void handle(HttpServletRequest req ,HttpServletResponse res) throws IOException{
		String songId = req.getParameter("songId");
		PlayListQuery plQuery = new PlayListQuery(ftr);
		PlayListDetail item = new PlayListDetail();
		
		item.setPlId((int)req.getSession().getAttribute("lovePlId"));
		item.setSongId(Integer.parseInt(songId));
		item.setAdd_at(new Date());
		
		plQuery.addSong(item);
		
		PrintWriter out = res.getWriter();
		out.print(songId);
	}
}
