package app.controller;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;
import app.entity.*;
import app.query.*;
import app.render.*;

@Controller
public class TestController {
	@Autowired
	SessionFactory ftr;
	
	@RequestMapping("index")
	public String home(){
		return "index";
	}
	
	@RequestMapping("testlayout")
	public String testLayout(ModelMap model){
		return new Render(model).render("mainLayout","testLayout");
	}
	
	@Transactional
	@RequestMapping("userlist")
	public String test(ModelMap model){
		QueryT query = new QueryT(ftr);
		model.addAttribute("list", query.select("User"));
		return "userlist";
	}
	
//	@Transactional
//	@RequestMapping("playlist")
//	public void plTest(){
//		PlayListQuery query = new PlayListQuery(ftr);
//		System.out.print(query.getPlayList(1, 1));
//	}
//	
//	@Transactional
//	@RequestMapping("songtest")
//	public void songtest(){
//		SongQuery sQuery = new SongQuery(ftr);
//		for(Song s: sQuery.getAll()){
//			System.out.printf(s.getSongName());
//		}
//		UserQuery uQuery = new UserQuery(ftr);
//		System.out.print("\n"+uQuery.get("username=admin").get(0).getSongs());
//	}
}
