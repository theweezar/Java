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
import app.render.*;

@Controller
public class AppController {
	@Autowired
	SessionFactory ftr;
	
	@Transactional
	@RequestMapping("home")
	public String home(ModelMap model){
		Session session = ftr.getCurrentSession();
		String hql = "FROM Song";
		Query query = session.createQuery(hql);
		List<User> songList = query.list();
		model.addAttribute("songList", songList);
		return new Render().render(model, "mainLayout", "home");
	}
}
