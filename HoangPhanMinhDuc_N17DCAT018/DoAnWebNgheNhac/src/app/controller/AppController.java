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

import javax.servlet.http.*;

import app.entity.*;
import app.render.*;

@Controller
public class AppController {
	@Autowired
	SessionFactory ftr;
	
	@Transactional
	@RequestMapping("home")
	public String home(ModelMap model, HttpServletRequest req, HttpServletResponse res){
		Render r = new Render(model);
		boolean logged = false;
		
		Session session = ftr.getCurrentSession();
		String hql = "FROM Song";
		Query query = session.createQuery(hql);
		List<User> songList = query.list();
//		set các biến boolean để làm điều kiện xử lý ở file .jsp
		r.setModelAttr("songList", songList);
		r.setModelAttr("logged", false);
		
		HttpSession httpss = req.getSession();
		try{
			logged = (boolean)httpss.getAttribute("logged");
		}
		catch(NullPointerException e){
			
		}
		if (logged){
			r.setModelAttr("logged", true);
			r.setModelAttr("currUsername", httpss.getAttribute("username"));
			return r.render("mainLayout", "home");
		}
		
		return r.render("mainLayout", "home");
	}
	
	
}
